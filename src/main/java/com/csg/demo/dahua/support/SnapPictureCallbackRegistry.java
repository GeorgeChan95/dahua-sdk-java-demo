package com.csg.demo.dahua.support;

import com.csg.demo.dahua.lib.NetSDKLib;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 大华远程抓图回调协调器。
 * SDK 抓图回调是全局回调，这里按登录句柄和请求序列号分发到对应请求。
 * 大华远程抓图是异步的，但你的 HTTP 接口需要同步返回 byte[]。
 * SDK 不会在你调用 CLIENT_SnapPictureEx 时立刻把图片给你，而是稍后在另一个线程里通过全局回调把图片数据送回来。
 * SnapPictureCallbackRegistry 就是在这两者之间做“桥梁”的。
 */
public final class SnapPictureCallbackRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger(SnapPictureCallbackRegistry.class);
    // 大华 SDK 文档里 CmdSerial 有效范围是 0~65535，超出会被截断，所以回调里也要做同样处理。
    private static final int MAX_CMD_SERIAL = 65535;
    // 自增序列号生成器，给每次抓图分配唯一 CmdSerial（1 → 65535 → 再回到 1）。
    private static final AtomicInteger SERIAL_GENERATOR = new AtomicInteger(1);
    private static final ConcurrentMap<SnapKey, CompletableFuture<byte[]>> PENDING_REQUESTS =
            new ConcurrentHashMap<>();

    /**
     * 全局抓图回调对象，必须保持强引用，避免 JNA 弱引用被 GC 回收。
     * @param loginId 哪台设备（登录句柄）
     * @param buffer 图片数据的内存指针（JNA Pointer）
     * @param length 图片字节长度
     * @param encodeType 编码类型，10 表示 JPEG
     * @param cmdSerial 请求序列号，用来区分同设备多次抓图
     * @param user 用户数据
     */
    public static final NetSDKLib.fSnapRev CALLBACK = (loginId, buffer, length, encodeType, cmdSerial, user) -> {
        // 根据登录句柄和请求序列号，获取对应的请求，这同时也是图片会写响应的唯一标识
        SnapKey key = new SnapKey(loginId.longValue(), normalizeSerial(cmdSerial));
        // 从 pending requests 中移除请求，如果请求不存在，则返回 null, 如果请求存在，则返回对应的请求
        // 用 remove 是为了处理完就清掉，避免内存泄漏
        CompletableFuture<byte[]> future = PENDING_REQUESTS.remove(key);
        if (future == null) {
            LOGGER.debug("未找到匹配的抓图请求，loginId: {}, cmdSerial: {}", key.loginHandle, key.cmdSerial);
            return;
        }

        if (buffer == null || length <= 0) {
            future.completeExceptionally(new IllegalStateException("抓图回调未返回有效图片数据"));
            return;
        }

        // 从 buffer 立刻复制出 byte[],SDK 说这块内存由 SDK 内部管理，回调结束后可能失效，不能长期持有 Pointer
        byte[] pictureBytes = buffer.getByteArray(0, length);
        // 正在 get() 等待的业务线程被唤醒，拿到图片
        future.complete(pictureBytes);
        LOGGER.debug("收到大华抓图回调，loginId: {}, cmdSerial: {}, encodeType: {}, size: {}",
                key.loginHandle, key.cmdSerial, encodeType, length);
    };

    private SnapPictureCallbackRegistry() {
    }

    /**
     * 注册一次待完成的抓图请求。
     * 必须先 register，再调 CLIENT_SnapPictureEx，否则回调先到会找不到等待者。
     * @param loginHandle 登录句柄
     * @return 待完成请求，包含 SDK 请求序列号和图片 Future
     */
    public static PendingSnapRequest register(long loginHandle) {
        int cmdSerial = nextSerial();
        SnapKey key = new SnapKey(loginHandle, cmdSerial);
        CompletableFuture<byte[]> future = new CompletableFuture<>();
        PENDING_REQUESTS.put(key, future);
        return new PendingSnapRequest(key, future);
    }

    /** 移除尚未完成的请求，用于 SDK 调用失败或等待超时后的清理。 */
    public static void remove(PendingSnapRequest request) {
        if (request != null) {
            PENDING_REQUESTS.remove(request.key);
        }
    }

    private static int nextSerial() {
        return SERIAL_GENERATOR.updateAndGet(value -> value >= MAX_CMD_SERIAL ? 1 : value + 1);
    }

    private static int normalizeSerial(int cmdSerial) {
        return cmdSerial & MAX_CMD_SERIAL;
    }

    /** 单次抓图请求上下文。 */
    public static final class PendingSnapRequest {
        private final SnapKey key;
        private final CompletableFuture<byte[]> future;

        private PendingSnapRequest(SnapKey key, CompletableFuture<byte[]> future) {
            this.key = key;
            this.future = future;
        }

        /** SDK 请求序列号。 */
        public int getCmdSerial() {
            return key.cmdSerial;
        }

        /** 等待抓图回调返回图片数据。 */
        public CompletableFuture<byte[]> getFuture() {
            return future;
        }
    }

    private static final class SnapKey {
        private final long loginHandle;
        private final int cmdSerial;

        private SnapKey(long loginHandle, int cmdSerial) {
            this.loginHandle = loginHandle;
            this.cmdSerial = cmdSerial;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SnapKey)) {
                return false;
            }
            SnapKey other = (SnapKey) obj;
            return loginHandle == other.loginHandle && cmdSerial == other.cmdSerial;
        }

        @Override
        public int hashCode() {
            return Objects.hash(loginHandle, cmdSerial);
        }
    }
}
