package com.csg.demo.service.impl;

import com.csg.demo.config.DahuaPlatform;
import com.csg.demo.dahua.lib.ToolKits;
import com.csg.demo.dahua.support.*;
import com.csg.demo.dto.PtzLocationInfoDTO;
import com.csg.demo.dto.PtzPresetInfoDTO;
import com.csg.demo.dto.RecordDownloadTaskDTO;
import com.csg.demo.dto.RecordFileInfoDTO;
import com.csg.demo.dto.TalkFormatDTO;
import com.csg.demo.service.DhSdkService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    private static final int DEFAULT_RECORD_MAX_COUNT = 100;
    private static final int MAX_RECORD_COUNT = 200;
    private static final int MAX_RECORD_HOURS = 6;
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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
     * 查询当前通道的云台位置信息。
     *
     * @param ip 设备 IP
     * @param port 设备端口
     * @param username 用户名，未登录时用于自动登录
     * @param password 密码，未登录时用于自动登录
     * @param channelId 通道号，从 0 开始
     * @return 云台位置信息；参数非法、平台不支持或设备不支持时返回空对象
     */
    @Override
    public PtzLocationInfoDTO getPtzLocation(String ip, int port, String username, String password, int channelId) {
        PtzLocationInfoDTO emptyInfo = new PtzLocationInfoDTO();
        emptyInfo.setChannelId(channelId);
        if (!isValidPresetListRequest(ip, port, username, password, channelId)) {
            log.warn("\n大华云台位置查询参数非法，ip: {}, port: {}, channelId: {}", ip, port, channelId);
            return emptyInfo;
        }

        DahuaPlatform platform = platformDetector.detect();
        if (platform == DahuaPlatform.LINUX64) {
            LinuxDhDeviceSession session = linuxSessionManager.getSession(ip, port, username, password);
            return session == null ? emptyInfo : session.getPtzLocation(channelId);
        } else if (platform == DahuaPlatform.WINDOWS64) {
            WindowsDhDeviceSession session = windowsSessionManager.getSession(ip, port, username, password);
            return session == null ? emptyInfo : session.getPtzLocation(channelId);
        }

        log.warn("\n当前平台不支持大华云台位置查询: {}", platformDetector.currentPlatformDescription());
        return emptyInfo;
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
     * 查询指定时间段内的录像文件列表。
     *
     * @param ip 设备 IP
     * @param port 设备端口
     * @param username 用户名，未登录时用于自动登录
     * @param password 密码，未登录时用于自动登录
     * @param channelId 通道号，从 0 开始
     * @param startTime 查询开始时间，格式：yyyy-MM-dd HH:mm:ss
     * @param endTime 查询结束时间，格式：yyyy-MM-dd HH:mm:ss
     * @param maxCount 最大返回条数
     * @return 录像文件列表；参数非法、平台不支持或 SDK 查询失败时返回空列表
     */
    @Override
    public List<RecordFileInfoDTO> listRecordFiles(String ip, int port, String username, String password,
                                                   int channelId, String startTime, String endTime, int maxCount) {
        LocalDateTime start = parseDateTime(startTime);
        LocalDateTime end = parseDateTime(endTime);
        if (!isValidRecordTimeRequest(ip, port, username, password, channelId, start, end)) {
            log.warn("\n大华录像文件查询参数非法，ip: {}, port: {}, channelId: {}, startTime: {}, endTime: {}",
                    ip, port, channelId, startTime, endTime);
            return Collections.emptyList();
        }

        int limit = normalizeRecordMaxCount(maxCount);
        DahuaPlatform platform = platformDetector.detect();
        if (platform == DahuaPlatform.LINUX64) {
            LinuxDhDeviceSession session = linuxSessionManager.getSession(ip, port, username, password);
            return session == null ? Collections.emptyList() : session.listRecordFiles(channelId, start, end, limit);
        } else if (platform == DahuaPlatform.WINDOWS64) {
            WindowsDhDeviceSession session = windowsSessionManager.getSession(ip, port, username, password);
            return session == null ? Collections.emptyList() : session.listRecordFiles(channelId, start, end, limit);
        }

        log.warn("\n当前平台不支持大华录像文件查询: {}", platformDetector.currentPlatformDescription());
        return Collections.emptyList();
    }

    /**
     * 异步下载指定时间段内的录像文件。
     *
     * @param ip 设备 IP
     * @param port 设备端口
     * @param username 用户名，未登录时用于自动登录
     * @param password 密码，未登录时用于自动登录
     * @param channelId 通道号，从 0 开始
     * @param startTime 下载开始时间，格式：yyyy-MM-dd HH:mm:ss
     * @param endTime 下载结束时间，格式：yyyy-MM-dd HH:mm:ss
     * @param recordFileType 录像类型，0 表示所有录像
     * @param fileName 本地保存文件名，可为空
     * @return 下载任务状态；参数非法、平台不支持或 SDK 启动失败时返回 null
     */
    @Override
    public RecordDownloadTaskDTO downloadRecordFile(String ip, int port, String username, String password,
                                                    int channelId, String startTime, String endTime,
                                                    int recordFileType, String fileName) {
        LocalDateTime start = parseDateTime(startTime);
        LocalDateTime end = parseDateTime(endTime);
        if (!isValidRecordTimeRequest(ip, port, username, password, channelId, start, end)
                || recordFileType < 0) {
            log.warn("\n大华录像下载参数非法，ip: {}, port: {}, channelId: {}, startTime: {}, endTime: {}, type: {}",
                    ip, port, channelId, startTime, endTime, recordFileType);
            return null;
        }

        DahuaPlatform platform = platformDetector.detect();
        if (platform == DahuaPlatform.LINUX64) {
            LinuxDhDeviceSession session = linuxSessionManager.getSession(ip, port, username, password);
            return session == null ? null : session.downloadRecordFileAsync(channelId, start, end, recordFileType, fileName);
        } else if (platform == DahuaPlatform.WINDOWS64) {
            WindowsDhDeviceSession session = windowsSessionManager.getSession(ip, port, username, password);
            return session == null ? null : session.downloadRecordFileAsync(channelId, start, end, recordFileType, fileName);
        }

        log.warn("\n当前平台不支持大华录像下载: {}", platformDetector.currentPlatformDescription());
        return null;
    }

    /**
     * 查询录像下载任务状态。
     *
     * @param taskId 下载任务 ID
     * @return 下载任务状态；不存在时返回 null
     */
    @Override
    public RecordDownloadTaskDTO getRecordDownloadTask(String taskId) {
        if (StringUtils.isBlank(taskId)) {
            return null;
        }
        return RecordDownloadTaskRegistry.getTask(taskId);
    }

    /**
     * 查询设备支持的语音对讲编码格式。
     *
     * @param ip 设备 IP
     * @param port 设备端口
     * @param username 用户名，未登录时用于自动登录
     * @param password 密码，未登录时用于自动登录
     * @return 支持的 G711A/G711U 编码格式列表
     */
    @Override
    public List<TalkFormatDTO> listTalkFormats(String ip, int port, String username, String password) {
        if (!isValidTalkBaseRequest(ip, port, username, password)) {
            log.warn("\n大华语音对讲格式查询参数非法，ip: {}, port: {}", ip, port);
            return Collections.emptyList();
        }

        DahuaPlatform platform = platformDetector.detect();
        if (platform == DahuaPlatform.LINUX64) {
            LinuxDhDeviceSession session = linuxSessionManager.getSession(ip, port, username, password);
            return session == null ? Collections.emptyList() : session.listTalkFormats();
        } else if (platform == DahuaPlatform.WINDOWS64) {
            WindowsDhDeviceSession session = windowsSessionManager.getSession(ip, port, username, password);
            return session == null ? Collections.emptyList() : session.listTalkFormats();
        }

        log.warn("\n当前平台不支持大华语音对讲格式查询: {}", platformDetector.currentPlatformDescription());
        return Collections.emptyList();
    }

    /**
     * 开启语音对讲。
     *
     * @param ip 设备 IP
     * @param port 设备端口
     * @param username 用户名，未登录时用于自动登录
     * @param password 密码，未登录时用于自动登录
     * @param channelId 通道号，从 0 开始
     * @param encoding 编码格式，支持 G711A、G711U
     * @param talkSession 对讲会话
     * @return 是否开启成功
     */
    @Override
    public boolean startTalk(String ip, int port, String username, String password, int channelId,
                             String encoding, TalkSessionRegistry.TalkSession talkSession) {
        if (!isValidTalkRequest(ip, port, username, password, channelId, encoding) || talkSession == null) {
            log.warn("\n大华语音对讲启动参数非法，ip: {}, port: {}, channelId: {}, encoding: {}",
                    ip, port, channelId, encoding);
            return false;
        }

        DahuaPlatform platform = platformDetector.detect();
        if (platform == DahuaPlatform.LINUX64) {
            LinuxDhDeviceSession session = linuxSessionManager.getSession(ip, port, username, password);
            return session != null && session.startTalk(channelId, talkSession);
        } else if (platform == DahuaPlatform.WINDOWS64) {
            WindowsDhDeviceSession session = windowsSessionManager.getSession(ip, port, username, password);
            return session != null && session.startTalk(channelId, talkSession);
        }

        log.warn("\n当前平台不支持大华语音对讲启动: {}", platformDetector.currentPlatformDescription());
        return false;
    }

    /**
     * 发送客户端上行裸 G711 音频帧到设备。
     *
     * @param ip 设备 IP
     * @param port 设备端口
     * @param username 用户名，未登录时用于自动登录
     * @param password 密码，未登录时用于自动登录
     * @param talkSession 对讲会话
     * @param audioBytes 裸音频帧
     * @return 是否发送成功
     */
    @Override
    public boolean sendTalkAudio(String ip, int port, String username, String password,
                                 TalkSessionRegistry.TalkSession talkSession, byte[] audioBytes) {
        if (!isValidTalkBaseRequest(ip, port, username, password)
                || talkSession == null || audioBytes == null || audioBytes.length == 0) {
            return false;
        }

        DahuaPlatform platform = platformDetector.detect();
        if (platform == DahuaPlatform.LINUX64) {
            LinuxDhDeviceSession session = linuxSessionManager.getSession(ip, port, username, password);
            return session != null && session.sendTalkAudio(talkSession, audioBytes);
        } else if (platform == DahuaPlatform.WINDOWS64) {
            WindowsDhDeviceSession session = windowsSessionManager.getSession(ip, port, username, password);
            return session != null && session.sendTalkAudio(talkSession, audioBytes);
        }

        log.warn("\n当前平台不支持大华语音对讲音频发送: {}", platformDetector.currentPlatformDescription());
        return false;
    }

    /**
     * 停止语音对讲。
     *
     * @param talkSession 对讲会话
     */
    @Override
    public void stopTalk(TalkSessionRegistry.TalkSession talkSession) {
        if (talkSession != null) {
            talkSession.close();
        }
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

    /**
     * 校验语音对讲基础参数。
     *
     * @param ip 设备 IP
     * @param port 设备端口
     * @param username 用户名，未登录时用于自动登录
     * @param password 密码，未登录时用于自动登录
     * @return 参数是否合法
     */
    private boolean isValidTalkBaseRequest(String ip, int port, String username, String password) {
        return StringUtils.isNotBlank(ip)
                && isValidPort(port)
                && StringUtils.isNotBlank(username)
                && StringUtils.isNotBlank(password);
    }

    /**
     * 校验语音对讲启动参数。
     *
     * @param ip 设备 IP
     * @param port 设备端口
     * @param username 用户名，未登录时用于自动登录
     * @param password 密码，未登录时用于自动登录
     * @param channelId 通道号，从 0 开始
     * @param encoding 编码格式
     * @return 参数是否合法
     */
    private boolean isValidTalkRequest(String ip, int port, String username, String password,
                                       int channelId, String encoding) {
        return isValidTalkBaseRequest(ip, port, username, password)
                && channelId >= 0
                && TalkSessionRegistry.TalkEncoding.fromName(encoding) != null;
    }

    /**
     * 校验录像查询/下载请求参数。
     *
     * @param ip 设备 IP
     * @param port 设备端口
     * @param username 用户名，未登录时用于自动登录
     * @param password 密码，未登录时用于自动登录
     * @param channelId 通道号，从 0 开始
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 参数是否合法
     */
    private boolean isValidRecordTimeRequest(String ip, int port, String username, String password,
                                             int channelId, LocalDateTime startTime, LocalDateTime endTime) {
        return isValidPresetListRequest(ip, port, username, password, channelId)
                && startTime != null
                && endTime != null
                && endTime.isAfter(startTime)
                && Duration.between(startTime, endTime).compareTo(Duration.ofHours(MAX_RECORD_HOURS)) <= 0;
    }

    /**
     * 解析接口时间字符串。
     *
     * @param value 时间字符串，格式：yyyy-MM-dd HH:mm:ss
     * @return 解析后的时间；格式错误时返回 null
     */
    private LocalDateTime parseDateTime(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        try {
            return LocalDateTime.parse(value, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    /**
     * 规范化录像查询最大返回条数。
     *
     * @param maxCount 请求条数
     * @return 限制后的条数
     */
    private int normalizeRecordMaxCount(int maxCount) {
        if (maxCount <= 0) {
            return DEFAULT_RECORD_MAX_COUNT;
        }
        return Math.min(maxCount, MAX_RECORD_COUNT);
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
