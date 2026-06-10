package com.csg.demo.dahua.support;

import com.csg.demo.dahua.lib.NetSDKLib;
import com.csg.demo.dahua.lib.enumeration.EM_AUDIO_SOURCE_FLAG;
import com.csg.demo.dahua.lib.structure.NET_AUDIO_DATA_CB_INFO;
import com.sun.jna.Pointer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 语音对讲会话注册表，维护 WebSocket 与 SDK 对讲句柄的关系。
 */
public final class TalkSessionRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger(TalkSessionRegistry.class);
    private static final NetSDKLib NET_SDK = NetSDKLib.NETSDK_INSTANCE;
    private static final ConcurrentMap<String, TalkSession> SESSIONS = new ConcurrentHashMap<>();

    private TalkSessionRegistry() {
    }

    /**
     * 创建语音对讲会话。
     *
     * @param webSocketSession WebSocket 会话
     * @param encoding 编码格式
     * @return 对讲会话
     */
    public static TalkSession create(WebSocketSession webSocketSession, TalkEncoding encoding) {
        TalkSession session = new TalkSession(UUID.randomUUID().toString(), webSocketSession, encoding);
        SESSIONS.put(session.getSessionId(), session);
        return session;
    }

    /**
     * 查询语音对讲会话。
     *
     * @param sessionId 会话 ID
     * @return 对讲会话；不存在时返回 null
     */
    public static TalkSession get(String sessionId) {
        return SESSIONS.get(sessionId);
    }

    /** 移除语音对讲会话。 */
    public static void remove(String sessionId) {
        if (sessionId != null) {
            SESSIONS.remove(sessionId);
        }
    }

    /** 语音对讲编码。 */
    public enum TalkEncoding {
        G711A(NetSDKLib.NET_TALK_CODING_TYPE.NET_TALK_G711a, "G711A"),
        G711U(NetSDKLib.NET_TALK_CODING_TYPE.NET_TALK_G711u, "G711U");

        private final int sdkValue;
        private final String name;

        TalkEncoding(int sdkValue, String name) {
            this.sdkValue = sdkValue;
            this.name = name;
        }

        public int getSdkValue() {
            return sdkValue;
        }

        public String getName() {
            return name;
        }

        public static TalkEncoding fromName(String value) {
            if (value == null) {
                return null;
            }
            for (TalkEncoding encoding : values()) {
                if (encoding.name.equalsIgnoreCase(value.trim())) {
                    return encoding;
                }
            }
            return null;
        }
    }

    /** 单个语音对讲会话。 */
    public static final class TalkSession {
        private final String sessionId;
        private final WebSocketSession webSocketSession;
        private final TalkEncoding encoding;
        private final Object sendLock = new Object();
        private final NetSDKLib.fAudioDataCallBackEx audioCallback;

        private volatile NetSDKLib.LLong talkHandle = new NetSDKLib.LLong(0);
        private volatile boolean closed;

        private TalkSession(String sessionId, WebSocketSession webSocketSession, TalkEncoding encoding) {
            this.sessionId = sessionId;
            this.webSocketSession = webSocketSession;
            this.encoding = encoding;
            this.audioCallback = this::onAudioData;
        }

        public String getSessionId() {
            return sessionId;
        }

        public TalkEncoding getEncoding() {
            return encoding;
        }

        public NetSDKLib.fAudioDataCallBackEx getAudioCallback() {
            return audioCallback;
        }

        public NetSDKLib.LLong getTalkHandle() {
            return talkHandle;
        }

        /** 标记 SDK 对讲句柄。 */
        public void start(NetSDKLib.LLong handle) {
            this.talkHandle = handle;
        }

        /** 判断会话是否已关闭。 */
        public boolean isClosed() {
            return closed;
        }

        /** 将设备端回传音频发送给 WebSocket 客户端。 */
        private void onAudioData(NetSDKLib.LLong handle, NET_AUDIO_DATA_CB_INFO audioInfo, int audioFlag, Pointer user) {
            if (closed || handle.longValue() != talkHandle.longValue()
                    || audioFlag != EM_AUDIO_SOURCE_FLAG.EM_AUDIO_SOURCE_FLAG_REMOTE.getValue()) {
                return;
            }

            Pointer buffer = audioInfo.pRawBuf != null ? audioInfo.pRawBuf : audioInfo.pBuf;
            int size = audioInfo.pRawBuf != null ? audioInfo.dwRawBufSize : audioInfo.dwBufSize;
            if (buffer == null || size <= 0) {
                return;
            }

            byte[] audioBytes = buffer.getByteArray(0, size);
            sendToWebSocket(audioBytes);
        }

        private void sendToWebSocket(byte[] audioBytes) {
            synchronized (sendLock) {
                if (closed || !webSocketSession.isOpen()) {
                    return;
                }
                try {
                    webSocketSession.sendMessage(new BinaryMessage(audioBytes));
                } catch (IOException ex) {
                    LOGGER.warn("发送设备端语音数据到 WebSocket 失败，talkSessionId: {}", sessionId, ex);
                    close();
                }
            }
        }

        /** 停止 SDK 对讲并移除注册表会话。 */
        public void close() {
            if (closed) {
                return;
            }
            closed = true;
            NetSDKLib.LLong handle = talkHandle;
            if (handle != null && handle.longValue() != 0) {
                if (!NET_SDK.CLIENT_StopTalkEx(handle)) {
                    LOGGER.warn("停止大华语音对讲失败，talkSessionId: {}, talkHandle: {}, error: 0x{}",
                            sessionId, handle.longValue(), Integer.toHexString(NET_SDK.CLIENT_GetLastError()));
                }
                talkHandle = new NetSDKLib.LLong(0);
            }
            TalkSessionRegistry.remove(sessionId);
        }
    }
}
