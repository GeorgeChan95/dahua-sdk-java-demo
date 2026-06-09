package com.csg.demo.dahua.support;

import com.csg.demo.config.DahuaPlatform;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName DhHeartbeatService
 * @Description 定时检测设备连接状态，离线时自动重连
 * @Author George
 * @Date 2026/6/9 15:43
 */
@Slf4j
@Component
public class DhHeartbeatService {

    private final PlatformDetector platformDetector;
    private final LinuxDeviceSessionManager linuxSessionManager;
    private final WindowsDeviceSessionManager windowsSessionManager;

    public DhHeartbeatService(PlatformDetector platformDetector,
                              LinuxDeviceSessionManager linuxSessionManager,
                              WindowsDeviceSessionManager windowsSessionManager) {
        this.platformDetector = platformDetector;
        this.linuxSessionManager = linuxSessionManager;
        this.windowsSessionManager = windowsSessionManager;
    }

    /** 每 60 秒检测当前平台所有已登录设备，离线则触发重连。 */
    @Scheduled(fixedDelay = 60000)
    public void checkHeartbeat() {
        DahuaPlatform platform = platformDetector.detect();
        if (platform == DahuaPlatform.LINUX64) {
            linuxSessionManager.getAllSessions().forEach(this::checkSession);
        } else if (platform == DahuaPlatform.WINDOWS64) {
            windowsSessionManager.getAllSessions().forEach(this::checkSession);
        }
    }

    /** 检测单个会话，离线时重连；异常仅记录日志，不影响其他设备。 */
    private void checkSession(AbstractDhDeviceSession session) {
        try {
            if (!session.isOnline()) {
                // 心跳只做兜底重连，SDK 自带的自动重连回调仍然保留。
                log.warn("大华设备心跳异常，开始重连，session: {}", session.getSessionKey());
                session.relogin();
            }
        } catch (Exception ex) {
            log.warn("大华设备心跳检测异常，session: {}", session.getSessionKey(), ex);
        }
    }
}
