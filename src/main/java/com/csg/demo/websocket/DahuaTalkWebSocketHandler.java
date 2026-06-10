package com.csg.demo.websocket;

import com.csg.demo.dahua.support.TalkSessionRegistry;
import com.csg.demo.service.DhSdkService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 大华流式语音对讲 WebSocket 处理器。
 */
@Slf4j
@Component
public class DahuaTalkWebSocketHandler extends BinaryWebSocketHandler {
    private static final String ATTR_TALK_SESSION = "dahuaTalkSession";
    private static final String ATTR_TALK_REQUEST = "dahuaTalkRequest";

    private final DhSdkService dhSdkService;

    public DahuaTalkWebSocketHandler(DhSdkService dhSdkService) {
        this.dhSdkService = dhSdkService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        TalkConnectionRequest request = parseRequest(webSocketSession.getUri());
        if (!request.isValid()) {
            log.warn("大华语音对讲 WebSocket 参数非法，uri: {}", webSocketSession.getUri());
            webSocketSession.close(CloseStatus.BAD_DATA);
            return;
        }

        TalkSessionRegistry.TalkEncoding encoding = TalkSessionRegistry.TalkEncoding.fromName(request.encoding);
        TalkSessionRegistry.TalkSession talkSession = TalkSessionRegistry.create(webSocketSession, encoding);
        boolean success = dhSdkService.startTalk(request.ip, request.port, request.username, request.password,
                request.channelId, request.encoding, talkSession);
        if (!success) {
            talkSession.close();
            webSocketSession.close(CloseStatus.SERVER_ERROR);
            return;
        }

        webSocketSession.getAttributes().put(ATTR_TALK_SESSION, talkSession);
        webSocketSession.getAttributes().put(ATTR_TALK_REQUEST, request);
        log.info("大华语音对讲 WebSocket 已连接，sessionId: {}, talkSessionId: {}, device: {}:{}",
                webSocketSession.getId(), talkSession.getSessionId(), request.ip, request.port);
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession webSocketSession, BinaryMessage message) {
        TalkSessionRegistry.TalkSession talkSession = getTalkSession(webSocketSession);
        TalkConnectionRequest request = getTalkRequest(webSocketSession);
        if (talkSession == null || request == null) {
            closeQuietly(webSocketSession, CloseStatus.BAD_DATA);
            return;
        }

        ByteBuffer payload = message.getPayload();
        byte[] audioBytes = new byte[payload.remaining()];
        payload.get(audioBytes);
        boolean success = dhSdkService.sendTalkAudio(request.ip, request.port, request.username, request.password,
                talkSession, audioBytes);
        if (!success) {
            log.warn("大华语音对讲上行音频发送失败，sessionId: {}, talkSessionId: {}",
                    webSocketSession.getId(), talkSession.getSessionId());
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        closeQuietly(session, CloseStatus.NOT_ACCEPTABLE.withReason("Only binary G711 audio frames are supported"));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        log.warn("大华语音对讲 WebSocket 传输异常，sessionId: {}", session.getId(), exception);
        cleanup(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        cleanup(session);
        log.info("大华语音对讲 WebSocket 已关闭，sessionId: {}, status: {}", session.getId(), status);
    }

    private void cleanup(WebSocketSession session) {
        TalkSessionRegistry.TalkSession talkSession = getTalkSession(session);
        if (talkSession != null) {
            dhSdkService.stopTalk(talkSession);
        }
    }

    private TalkSessionRegistry.TalkSession getTalkSession(WebSocketSession session) {
        Object value = session.getAttributes().get(ATTR_TALK_SESSION);
        return value instanceof TalkSessionRegistry.TalkSession ? (TalkSessionRegistry.TalkSession) value : null;
    }

    private TalkConnectionRequest getTalkRequest(WebSocketSession session) {
        Object value = session.getAttributes().get(ATTR_TALK_REQUEST);
        return value instanceof TalkConnectionRequest ? (TalkConnectionRequest) value : null;
    }

    private void closeQuietly(WebSocketSession session, CloseStatus status) {
        try {
            if (session.isOpen()) {
                session.close(status);
            }
        } catch (Exception ex) {
            log.warn("关闭大华语音对讲 WebSocket 失败，sessionId: {}", session.getId(), ex);
        }
    }

    private TalkConnectionRequest parseRequest(URI uri) {
        Map<String, String> params = parseQuery(uri == null ? null : uri.getRawQuery());
        TalkConnectionRequest request = new TalkConnectionRequest();
        request.ip = params.get("ip");
        request.port = parseInt(params.get("port"), 0);
        request.username = params.get("username");
        request.password = params.get("password");
        request.channelId = parseInt(params.get("channelId"), -1);
        request.encoding = params.get("encoding");
        return request;
    }

    private Map<String, String> parseQuery(String rawQuery) {
        Map<String, String> params = new HashMap<>();
        if (StringUtils.isBlank(rawQuery)) {
            return params;
        }

        String[] pairs = rawQuery.split("&");
        for (String pair : pairs) {
            int splitIndex = pair.indexOf('=');
            if (splitIndex <= 0) {
                continue;
            }
            String key = decode(pair.substring(0, splitIndex));
            String value = decode(pair.substring(splitIndex + 1));
            params.put(key, value);
        }
        return params;
    }

    private String decode(String value) {
        try {
            return URLDecoder.decode(value, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException ex) {
            return value;
        }
    }

    private int parseInt(String value, int defaultValue) {
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException ex) {
            return defaultValue;
        }
    }

    private static final class TalkConnectionRequest {
        private String ip;
        private int port;
        private String username;
        private String password;
        private int channelId;
        private String encoding;

        private boolean isValid() {
            return StringUtils.isNotBlank(ip)
                    && port > 0
                    && port <= 65535
                    && StringUtils.isNotBlank(username)
                    && StringUtils.isNotBlank(password)
                    && channelId >= 0
                    && TalkSessionRegistry.TalkEncoding.fromName(encoding) != null;
        }
    }
}
