package com.csg.demo.service.impl;

import com.csg.demo.config.DahuaPlatform;
import com.csg.demo.dahua.lib.ToolKits;
import com.csg.demo.dahua.support.*;
import com.csg.demo.dto.PtzPresetInfoDTO;
import com.csg.demo.service.DhSdkService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName LinuxDhServiceImpl
 * @Description 大华设备登录/注销服务，按当前平台路由到对应会话管理器
 * @Author George
 * @Date 2026/6/9 15:49
 */
@Slf4j
@Service
public class DhSdkServiceImpl implements DhSdkService {

    private static final byte[] EMPTY_BYTES = new byte[0];

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

    /**
     * 远程抓取当前通道的一张图片。
     *
     * @param ip 设备 IP
     * @param port 设备端口
     * @param username 用户名，未登录时用于自动登录
     * @param password 密码，未登录时用于自动登录
     * @param channelId 通道号，从 0 开始
     * @return JPEG 图片字节；参数非法、平台不支持或 SDK 抓图失败时返回空数组
     */
    @Override
    public byte[] capturePicture(String ip, int port, String username, String password, int channelId) {
        if (!isValidCapturePictureRequest(ip, port, username, password, channelId)) {
            log.warn("\n大华设备抓图参数非法，ip: {}, port: {}, channelId: {}", ip, port, channelId);
            return EMPTY_BYTES;
        }

        DahuaPlatform platform = platformDetector.detect();
        byte[] pictureBytes = EMPTY_BYTES;
        if (platform == DahuaPlatform.LINUX64) {
            LinuxDhDeviceSession session = linuxSessionManager.getSession(ip, port, username, password);
            pictureBytes = session == null ? EMPTY_BYTES : session.capturePicture(channelId);
        } else if (platform == DahuaPlatform.WINDOWS64) {
            WindowsDhDeviceSession session = windowsSessionManager.getSession(ip, port, username, password);
            pictureBytes = session == null ? EMPTY_BYTES : session.capturePicture(channelId);
        } else {
            log.warn("\n当前平台不支持大华设备抓图: {}", platformDetector.currentPlatformDescription());
            return EMPTY_BYTES;
        }

        if (pictureBytes.length > 0) {
            // 图片保存到本地
            saveCapturePicture(pictureBytes, ip, port, channelId);
        }
        return pictureBytes;
    }

    /**
     * 查询云台预置点列表。
     *
     * @param ip 设备 IP
     * @param port 设备端口
     * @param username 用户名，未登录时用于自动登录
     * @param password 密码，未登录时用于自动登录
     * @param channelId 通道号，从 0 开始
     * @return 预置点列表；参数非法、平台不支持或 SDK 查询失败时返回空列表
     */
    @Override
    public List<PtzPresetInfoDTO> listPresets(String ip, int port, String username, String password, int channelId) {
        if (!isValidPresetListRequest(ip, port, username, password, channelId)) {
            log.warn("\n大华预置点列表查询参数非法，ip: {}, port: {}, channelId: {}", ip, port, channelId);
            return Collections.emptyList();
        }

        DahuaPlatform platform = platformDetector.detect();
        if (platform == DahuaPlatform.LINUX64) {
            LinuxDhDeviceSession session = linuxSessionManager.getSession(ip, port, username, password);
            return session == null ? Collections.emptyList() : session.listPresets(channelId);
        } else if (platform == DahuaPlatform.WINDOWS64) {
            WindowsDhDeviceSession session = windowsSessionManager.getSession(ip, port, username, password);
            return session == null ? Collections.emptyList() : session.listPresets(channelId);
        }

        log.warn("\n当前平台不支持大华预置点列表查询: {}", platformDetector.currentPlatformDescription());
        return Collections.emptyList();
    }

    /**
     * 切换到指定云台预置点。
     *
     * @param ip 设备 IP
     * @param port 设备端口
     * @param username 用户名，未登录时用于自动登录
     * @param password 密码，未登录时用于自动登录
     * @param channelId 通道号，从 0 开始
     * @param presetIndex 预置点编号，通常从 1 开始
     * @return 是否切换成功
     */
    @Override
    public boolean gotoPreset(String ip, int port, String username, String password, int channelId, int presetIndex) {
        if (!isValidGotoPresetRequest(ip, port, username, password, channelId, presetIndex)) {
            log.warn("\n大华预置点切换参数非法，ip: {}, port: {}, channelId: {}, presetIndex: {}",
                    ip, port, channelId, presetIndex);
            return false;
        }

        DahuaPlatform platform = platformDetector.detect();
        if (platform == DahuaPlatform.LINUX64) {
            LinuxDhDeviceSession session = linuxSessionManager.getSession(ip, port, username, password);
            return session != null && session.gotoPreset(channelId, presetIndex);
        } else if (platform == DahuaPlatform.WINDOWS64) {
            WindowsDhDeviceSession session = windowsSessionManager.getSession(ip, port, username, password);
            return session != null && session.gotoPreset(channelId, presetIndex);
        }

        log.warn("\n当前平台不支持大华预置点切换: {}", platformDetector.currentPlatformDescription());
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

    /**
     * 校验预置点列表查询参数。
     *
     * @param ip 设备 IP
     * @param port 设备端口
     * @param username 用户名，未登录时用于自动登录
     * @param password 密码，未登录时用于自动登录
     * @param channelId 通道号，从 0 开始
     * @return 参数是否合法
     */
    private boolean isValidPresetListRequest(String ip, int port, String username, String password, int channelId) {
        return StringUtils.isNotBlank(ip)
                && isValidPort(port)
                && StringUtils.isNotBlank(username)
                && StringUtils.isNotBlank(password)
                && channelId >= 0;
    }

    /**
     * 校验预置点切换参数。
     *
     * @param ip 设备 IP
     * @param port 设备端口
     * @param username 用户名，未登录时用于自动登录
     * @param password 密码，未登录时用于自动登录
     * @param channelId 通道号，从 0 开始
     * @param presetIndex 预置点编号，通常从 1 开始
     * @return 参数是否合法
     */
    private boolean isValidGotoPresetRequest(String ip, int port, String username, String password,
                                             int channelId, int presetIndex) {
        return isValidPresetListRequest(ip, port, username, password, channelId)
                && presetIndex > 0;
    }

    /**
     * 校验抓图请求参数。
     *
     * @param ip 设备 IP
     * @param port 设备端口
     * @param username 用户名，未登录时用于自动登录
     * @param password 密码，未登录时用于自动登录
     * @param channelId 通道号，从 0 开始
     * @return 参数是否合法
     */
    private boolean isValidCapturePictureRequest(String ip, int port, String username, String password, int channelId) {
        return isValidPresetListRequest(ip, port, username, password, channelId);
    }

    /** 校验端口是否在合法范围内（大华常用端口如 37777 超出 short 范围，故用 int）。 */
    private boolean isValidPort(int port) {
        return port > 0 && port <= 65535;
    }

    /**
     * 将抓图字节保存为本地 JPEG 文件。
     *
     * @param pictureBytes 图片字节
     * @param ip 设备 IP
     * @param port 设备端口
     * @param channelId 通道号
     */
    private void saveCapturePicture(byte[] pictureBytes, String ip, int port, int channelId) {
        try {
            File captureDir = new File("./capture/");
            if (!captureDir.exists() && !captureDir.mkdirs()) {
                log.warn("创建抓图目录失败，path: {}", captureDir.getAbsolutePath());
                return;
            }

            String fileName = String.format("%s_%d_ch%d_%s.jpg",
                    ip.replace('.', '_'), port, channelId, ToolKits.getDate());
            String filePath = new File(captureDir, fileName).getAbsolutePath();
            ToolKits.savePicture(pictureBytes, filePath);
            log.info("大华设备抓图已保存，file: {}", filePath);
        } catch (IOException ex) {
            log.warn("大华设备抓图保存失败，ip: {}, port: {}, channelId: {}", ip, port, channelId, ex);
        }
    }
}
