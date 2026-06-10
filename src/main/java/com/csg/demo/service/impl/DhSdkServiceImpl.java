package com.csg.demo.service.impl;

import com.csg.demo.config.DahuaPlatform;
import com.csg.demo.dahua.support.*;
import com.csg.demo.service.DhSdkService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @ClassName LinuxDhServiceImpl
 * @Description 大华设备登录/注销服务，按当前平台路由到对应会话管理器
 * @Author George
 * @Date 2026/6/9 15:49
 */
@Slf4j
@Service
public class DhSdkServiceImpl implements DhSdkService {

    private final PlatformDetector platformDetector;
    private final LinuxDeviceSessionManager linuxSessionManager;
    private final WindowsDeviceSessionManager windowsSessionManager;

    /** 注入平台检测器和两个平台的会话管理器。 */
    public DhSdkServiceImpl(PlatformDetector platformDetector,
                            LinuxDeviceSessionManager linuxSessionManager,
                            WindowsDeviceSessionManager windowsSessionManager) {
        this.platformDetector = platformDetector;
        this.linuxSessionManager = linuxSessionManager;
        this.windowsSessionManager = windowsSessionManager;
    }

    /** 登录设备：先校验入参，再按当前平台分支调用对应管理器。 */
    @Override
    public boolean login(String ip, int port, String username, String password) {
        if (StringUtils.isBlank(ip) || !isValidPort(port)
                || StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            log.warn("大华设备登录参数非法，ip: {}, port: {}", ip, port);
            return false;
        }

        DahuaPlatform platform = platformDetector.detect();
        if (platform == DahuaPlatform.LINUX64) {
            return linuxSessionManager.login(ip, port, username, password);
        } else if (platform == DahuaPlatform.WINDOWS64) {
            return windowsSessionManager.login(ip, port, username, password);
        }

        log.warn("\n当前平台不支持大华设备登录: {}", platformDetector.currentPlatformDescription());
        return false;
    }

    /** 注销设备：先校验入参，再按当前平台分支调用对应管理器。 */
    @Override
    public boolean logout(String ip, int port) {
        if (StringUtils.isBlank(ip) || !isValidPort(port)) {
            log.warn("\n大华设备注销参数非法，ip: {}, port: {}", ip, port);
            return false;
        }

        DahuaPlatform platform = platformDetector.detect();
        if (platform == DahuaPlatform.LINUX64) {
            return linuxSessionManager.logout(ip, port);
        } else if (platform == DahuaPlatform.WINDOWS64) {
            return windowsSessionManager.logout(ip, port);
        }

        log.warn("\n当前平台不支持大华设备注销: {}", platformDetector.currentPlatformDescription());
        return false;
    }




    /** 校验参数并按当前平台执行云台控制。 */
    @Override
    public boolean control(String ip, int port, String username, String password, int channelId, int command,
                           int param1, int param2, int param3, boolean stop) {
        // 参数校验
        if (!isValidRequest(ip, port, channelId, command, param1, param2, param3)) {
            log.warn("\n大华云台控制参数非法，ip: {}, port: {}, channelId: {}, command: {}",
                    ip, port, channelId, command);
            return false;
        }

        DahuaPlatform platform = platformDetector.detect();
        if (platform == DahuaPlatform.LINUX64) {
            LinuxDhDeviceSession session = linuxSessionManager.getSession(ip, port, username, password);
            return session != null && session.ptzControl(channelId, command, param1, param2, param3, stop);
        } else if (platform == DahuaPlatform.WINDOWS64) {
            WindowsDhDeviceSession session = windowsSessionManager.getSession(ip, port, username, password);
            return session != null && session.ptzControl(channelId, command, param1, param2, param3, stop);
        }

        log.warn("\n当前平台不支持大华云台控制: {}", platformDetector.currentPlatformDescription());
        return false;
    }

    /** 校验云台控制请求参数。 */
    private boolean isValidRequest(String ip, int port, int channelId, int command,
                                   int param1, int param2, int param3) {
        return StringUtils.isNotBlank(ip)
                && port > 0
                && port <= 65535
                && channelId >= 0
                && command >= 0
                && param1 >= 0
                && param2 >= 0
                && param3 >= 0;
    }

    /** 校验端口是否在合法范围内（大华常用端口如 37777 超出 short 范围，故用 int）。 */
    private boolean isValidPort(int port) {
        return port > 0 && port <= 65535;
    }
}
