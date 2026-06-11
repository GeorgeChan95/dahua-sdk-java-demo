package com.csg.demo.dahua.support;

import com.csg.demo.dahua.lib.NetSDKLib;
import com.csg.demo.dahua.lib.ToolKits;
import com.csg.demo.dahua.lib.structure.NET_IN_START_TALK_INFO;
import com.csg.demo.dahua.lib.structure.NET_IN_TALK_SEND_DATA_STREAM;
import com.csg.demo.dahua.lib.structure.NET_OUT_START_TALK_INFO;
import com.csg.demo.dahua.lib.structure.NET_OUT_TALK_SEND_DATA_STREAM;
import com.csg.demo.dto.PtzLocationInfoDTO;
import com.csg.demo.dto.PtzPresetInfoDTO;
import com.csg.demo.dto.RecordDownloadTaskDTO;
import com.csg.demo.dto.RecordFileInfoDTO;
import com.csg.demo.dto.TalkFormatDTO;
import com.sun.jna.Memory;
import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 大华设备会话基础实现，封装跨平台一致的 SDK 登录、注销、在线检测逻辑。
 * 对外方法均加锁，保证同一设备的登录、注销、重连串行执行，避免并发操作同一个 SDK 句柄。
 */
abstract class AbstractDhDeviceSession {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDhDeviceSession.class);
    private static final int QUERY_TIMEOUT_MS = 3000; // 超时3秒
    private static final int RECORD_QUERY_TIMEOUT_MS = 5000; // 录像查询超时5秒
    private static final int SNAP_TIMEOUT_SECONDS = 5; // 抓图回调等待超时5秒
    private static final int MAX_PRESET_COUNT = 256; // 预置点列表最大读取数量
    private static final byte[] EMPTY_PICTURE = new byte[0];
    private static final NetSDKLib NET_SDK = NetSDKLib.NETSDK_INSTANCE;

    private final String platformName;
    private final String ip;
    private final int port;
    private final String username;
    private final String password;

    /** 登录句柄，0 表示未登录；使用 SDK 的 LLong 避免 64 位句柄被截断。 */
    private NetSDKLib.LLong loginHandle = new NetSDKLib.LLong(0);

    /** 构建设备会话，保存平台、设备地址和登录凭据。 */
    AbstractDhDeviceSession(String platformName, String ip, int port, String username, String password) {
        this.platformName = platformName;
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    /** 登录设备；若已登录则直接返回成功（幂等）。 */
    public synchronized boolean login() {
        if (isLoggedIn()) {
            return true;
        }

        NetSDKLib.NET_IN_LOGIN_WITH_HIGHLEVEL_SECURITY inParam =
                new NetSDKLib.NET_IN_LOGIN_WITH_HIGHLEVEL_SECURITY();
        NetSDKLib.NET_OUT_LOGIN_WITH_HIGHLEVEL_SECURITY outParam =
                new NetSDKLib.NET_OUT_LOGIN_WITH_HIGHLEVEL_SECURITY();
        outParam.stuDeviceInfo = new NetSDKLib.NET_DEVICEINFO_Ex();

        copyString(ip, inParam.szIP);
        copyString(username, inParam.szUserName);
        copyString(password, inParam.szPassword);
        inParam.nPort = port;

        // 使用高安全等级登录接口，避免旧登录接口在新设备上兼容性不足。
        NetSDKLib.LLong handle = NET_SDK.CLIENT_LoginWithHighLevelSecurity(inParam, outParam);
        if (handle.longValue() == 0) {
            LOGGER.warn("\n{} 大华设备登录失败，device: {}, error: 0x{}",
                    platformName, getSessionKey(), Integer.toHexString(NET_SDK.CLIENT_GetLastError()));
            return false;
        }

        loginHandle = handle;
        LOGGER.info("\n{} 大华设备登录成功，device: {}, loginId: {}", platformName, getSessionKey(), handle.longValue());
        return true;
    }

    /** 重连设备：先注销旧句柄再重新登录，用于心跳检测到离线时的兜底恢复。 */
    public synchronized boolean relogin() {
        logout();
        return login();
    }

    /** 注销设备；已注销成功或本未登录返回 true，仅 SDK 注销失败时返回 false。 */
    public synchronized boolean logout() {
        if (!isLoggedIn()) {
            return true;
        }

        NetSDKLib.LLong oldHandle = loginHandle;
        if (!NET_SDK.CLIENT_Logout(oldHandle)) {
            LOGGER.warn("{} 大华设备注销失败，device: {}, loginId: {}, error: 0x{}",
                    platformName, getSessionKey(), oldHandle.longValue(),
                    Integer.toHexString(NET_SDK.CLIENT_GetLastError()));
            return false;
        }

        // 句柄清零后，心跳线程不会再拿旧句柄做在线检查。
        loginHandle = new NetSDKLib.LLong(0);
        LOGGER.info("\n{} 大华设备注销成功，device: {}, loginId: {}", platformName, getSessionKey(), oldHandle.longValue());
        return true;
    }

    /** 查询设备在线状态，未登录或查询失败均视为离线。 */
    public synchronized boolean isOnline() {
        if (!isLoggedIn()) {
            return false;
        }

        // SDK 通过 4 字节 DWORD 返回在线状态：【 1 在线，0 离线 】。
        // DWORD 是 32 位无符号整数，占 4 字节，所以这里分配 Memory(4)。
        Memory onlineState = new Memory(4);
        // 输出缓冲区，SDK 把结果写进来
        onlineState.setInt(0, 0);
        // 查询设备状态(pBuf内存由用户申请释放)
        boolean success = NET_SDK.CLIENT_QueryDevState(loginHandle, NetSDKLib.NET_DEVSTATE_ONLINE,
                onlineState, (int) onlineState.size(), new IntByReference(0), QUERY_TIMEOUT_MS);
        if (!success) {
            LOGGER.warn("\n{} 大华设备在线状态查询失败，device: {}, loginId: {}, 错误码: 0x{}",
                    platformName, getSessionKey(), loginHandle.longValue(),
                    Integer.toHexString(NET_SDK.CLIENT_GetLastError()));
            return false;
        }
        return onlineState.getInt(0) == 1;
    }

    /** 执行云台控制命令；与登录、注销、重连共用锁，避免并发操作同一个登录句柄。 */
    public synchronized boolean ptzControl(int channelId, int command,
                                           int param1, int param2, int param3, boolean stop) {
        if (!isLoggedIn()) {
            LOGGER.warn("\n{} 大华设备未登录，无法执行云台控制，device: {}", platformName, getSessionKey());
            return false;
        }

        boolean success = NET_SDK.CLIENT_DHPTZControlEx(loginHandle, channelId, command,
                param1, param2, param3, stop ? 1 : 0);
        if (!success) {
            LOGGER.warn("\n{} 大华设备云台控制失败，device: {}, channelId: {}, command: {}, 错误码: 0x{}",
                    platformName, getSessionKey(), channelId, command,
                    Integer.toHexString(NET_SDK.CLIENT_GetLastError()));
        }
        return success;
    }

    /**
     * 查询当前通道的云台位置信息。
     *
     * @param channelId 通道号，从 0 开始
     * @return 云台位置信息；未登录、设备不支持或查询失败时返回空对象
     */
    public synchronized PtzLocationInfoDTO getPtzLocation(int channelId) {
        PtzLocationInfoDTO emptyInfo = new PtzLocationInfoDTO();
        emptyInfo.setChannelId(channelId);
        if (!isLoggedIn()) {
            LOGGER.warn("\n{} 大华设备未登录，无法查询云台位置，device: {}", platformName, getSessionKey());
            return emptyInfo;
        }

        NetSDKLib.NET_PTZ_LOCATION_INFO locationInfo = new NetSDKLib.NET_PTZ_LOCATION_INFO();
        locationInfo.nChannelID = channelId;
        locationInfo.write();
        boolean success = NET_SDK.CLIENT_QueryDevState(loginHandle, NetSDKLib.NET_DEVSTATE_PTZ_LOCATION,
                locationInfo.getPointer(), locationInfo.size(), new IntByReference(0), QUERY_TIMEOUT_MS);
        if (!success) {
            LOGGER.warn("\n{} 查询大华设备云台位置失败，device: {}, channelId: {}, 错误码: 0x{}",
                    platformName, getSessionKey(), channelId, Integer.toHexString(NET_SDK.CLIENT_GetLastError()));
            return emptyInfo;
        }

        locationInfo.read();
        return toPtzLocationInfo(locationInfo);
    }

    /**
     * 远程抓取当前通道的一张图片。
     *
     * @param channelId 通道号，从 0 开始
     * @return JPEG 图片字节；未登录、SDK 调用失败或等待超时时返回空数组
     */
    public synchronized byte[] capturePicture(int channelId) {
        if (!isLoggedIn()) {
            LOGGER.warn("\n{} 大华设备未登录，无法抓图，device: {}", platformName, getSessionKey());
            return EMPTY_PICTURE;
        }

        SnapPictureCallbackRegistry.PendingSnapRequest request =
                SnapPictureCallbackRegistry.register(loginHandle.longValue());
        NetSDKLib.SNAP_PARAMS snapParams = new NetSDKLib.SNAP_PARAMS();
        snapParams.Channel = channelId;
        snapParams.Quality = 3; // 3表示画质，10%、30%、50%、60%、80%、100%
        snapParams.mode = 0; // 请求一帧，远程抓图
        snapParams.InterSnap = 0; // 0表示不定时抓图，1表示定时抓图，单位为秒
        snapParams.CmdSerial = request.getCmdSerial();

        boolean success = NET_SDK.CLIENT_SnapPictureEx(loginHandle, snapParams, new IntByReference(0));
        if (!success) {
            SnapPictureCallbackRegistry.remove(request);
            LOGGER.warn("\n{} 大华设备抓图命令下发失败，device: {}, channelId: {}, cmdSerial: {}, 错误码: 0x{}",
                    platformName, getSessionKey(), channelId, snapParams.CmdSerial,
                    Integer.toHexString(NET_SDK.CLIENT_GetLastError()));
            return EMPTY_PICTURE;
        }

        try {
            return request.getFuture().get(SNAP_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            SnapPictureCallbackRegistry.remove(request);
            LOGGER.warn("\n{} 大华设备抓图等待被中断，device: {}, channelId: {}, cmdSerial: {}",
                    platformName, getSessionKey(), channelId, snapParams.CmdSerial);
            return EMPTY_PICTURE;
        } catch (ExecutionException | TimeoutException ex) {
            SnapPictureCallbackRegistry.remove(request);
            LOGGER.warn("\n{} 大华设备抓图回调等待失败，device: {}, channelId: {}, cmdSerial: {}",
                    platformName, getSessionKey(), channelId, snapParams.CmdSerial, ex);
            return EMPTY_PICTURE;
        }
    }

    /**
     * 查询指定时间段内的录像文件列表。
     *
     * @param channelId 通道号，从 0 开始
     * @param startTime 查询开始时间
     * @param endTime 查询结束时间
     * @param maxCount 最大返回条数
     * @return 录像文件列表；未登录或查询失败时返回空列表
     */
    public synchronized List<RecordFileInfoDTO> listRecordFiles(int channelId, LocalDateTime startTime,
                                                                LocalDateTime endTime, int maxCount) {
        List<RecordFileInfoDTO> records = new ArrayList<>();
        if (!isLoggedIn()) {
            LOGGER.warn("\n{} 大华设备未登录，无法查询录像文件，device: {}", platformName, getSessionKey());
            return records;
        }

        NetSDKLib.NET_RECORDFILE_INFO[] recordArray = createRecordFileArray(maxCount);
        IntByReference fileCount = new IntByReference(0);
        boolean success = NET_SDK.CLIENT_QueryRecordFile(loginHandle, channelId,
                NetSDKLib.EM_QUERY_RECORD_TYPE.EM_RECORD_TYPE_ALL, toNetTime(startTime), toNetTime(endTime),
                null, recordArray, recordArray.length * recordArray[0].size(), fileCount,
                RECORD_QUERY_TIMEOUT_MS, false);
        if (!success) {
            LOGGER.warn("\n{} 查询大华设备录像文件失败，device: {}, channelId: {}, 错误码: 0x{}",
                    platformName, getSessionKey(), channelId, Integer.toHexString(NET_SDK.CLIENT_GetLastError()));
            return records;
        }

        int retCount = Math.min(fileCount.getValue(), recordArray.length);
        for (int i = 0; i < retCount; i++) {
            records.add(toRecordFileInfo(recordArray[i]));
        }
        return records;
    }

    /**
     * 异步下载指定时间段内的录像文件。
     *
     * @param channelId 通道号，从 0 开始
     * @param startTime 下载开始时间
     * @param endTime 下载结束时间
     * @param recordFileType 录像类型，0 表示所有录像
     * @param fileName 本地保存文件名，可为空
     * @return 下载任务状态；未登录时返回 null
     */
    public synchronized RecordDownloadTaskDTO downloadRecordFileAsync(int channelId, LocalDateTime startTime,
                                                                      LocalDateTime endTime, int recordFileType,
                                                                      String fileName) {
        if (!isLoggedIn()) {
            LOGGER.warn("\n{} 大华设备未登录，无法下载录像文件，device: {}", platformName, getSessionKey());
            return null;
        }

        String filePath = buildRecordFilePath(channelId, startTime, endTime, fileName);
        RecordDownloadTaskRegistry.RecordDownloadTask task = RecordDownloadTaskRegistry.createTask(filePath);
        RecordDownloadTaskRegistry.schedule(task,
                () -> startDownloadRecordFile(task, channelId, startTime, endTime, recordFileType));
        return task.toDTO();
    }

    /**
     * 查询设备支持的语音对讲编码格式。
     *
     * @return 支持的语音编码列表；未登录或查询失败时返回空列表
     */
    public synchronized List<TalkFormatDTO> listTalkFormats() {
        List<TalkFormatDTO> formats = new ArrayList<>();
        if (!isLoggedIn()) {
            LOGGER.warn("\n{} 大华设备未登录，无法查询语音对讲格式，device: {}", platformName, getSessionKey());
            return formats;
        }

        NetSDKLib.NETDEV_TALKFORMAT_LIST formatList = new NetSDKLib.NETDEV_TALKFORMAT_LIST();
        formatList.write();
        boolean success = NET_SDK.CLIENT_QueryDevState(loginHandle, NetSDKLib.NET_DEVSTATE_TALK_ECTYPE,
                formatList.getPointer(), formatList.size(), new IntByReference(0), QUERY_TIMEOUT_MS);
        if (!success) {
            LOGGER.warn("\n{} 查询大华设备语音对讲格式失败，device: {}, 错误码: 0x{}",
                    platformName, getSessionKey(), Integer.toHexString(NET_SDK.CLIENT_GetLastError()));
            return formats;
        }

        formatList.read();
        int count = Math.min(formatList.nSupportNum, formatList.type.length);
        for (int i = 0; i < count; i++) {
            TalkFormatDTO dto = toTalkFormat(formatList.type[i]);
            if (dto != null) {
                formats.add(dto);
            }
        }
        return formats;
    }

    /**
     * 开启语音对讲。
     *
     * @param channelId 通道号，从 0 开始
     * @param talkSession 对讲会话
     * @return 是否开启成功
     */
    public synchronized boolean startTalk(int channelId, TalkSessionRegistry.TalkSession talkSession) {
        if (!isLoggedIn()) {
            LOGGER.warn("\n{} 大华设备未登录，无法开启语音对讲，device: {}", platformName, getSessionKey());
            return false;
        }
        if (talkSession == null || talkSession.getEncoding() == null) {
            LOGGER.warn("\n{} 语音对讲会话参数非法，device: {}", platformName, getSessionKey());
            return false;
        }

        NetSDKLib.NETDEV_TALKDECODE_INFO talkEncode = buildTalkDecodeInfo(talkSession.getEncoding());
        if (!setTalkEncodeType(talkEncode)) {
            return false;
        }
        if (!setTalkSpeakParam()) { // 设置语音对讲编码格式
            return false;
        }
        if (channelId > 0 && !setTalkChannel(channelId)) { // 设置 NVR 等多通道设备的对讲转发通道。
            return false;
        }

        NET_IN_START_TALK_INFO inParam = new NET_IN_START_TALK_INFO();
        NET_OUT_START_TALK_INFO outParam = new NET_OUT_START_TALK_INFO();
        inParam.pfAudioDataCallBackEx = talkSession.getAudioCallback();
        inParam.dwUser = null;
        inParam.write();
        outParam.write();
        NetSDKLib.LLong talkHandle = NET_SDK.CLIENT_StartTalkByDataType(loginHandle,
                inParam.getPointer(), outParam.getPointer(), RECORD_QUERY_TIMEOUT_MS);
        if (talkHandle.longValue() == 0) {
            LOGGER.warn("\n{} 大华设备语音对讲启动失败，device: {}, channelId: {}, encoding: {}, 错误码: 0x{}",
                    platformName, getSessionKey(), channelId, talkSession.getEncoding().getName(),
                    Integer.toHexString(NET_SDK.CLIENT_GetLastError()));
            return false;
        }

        talkSession.start(talkHandle);
        LOGGER.info("\n{} 大华设备语音对讲已启动，device: {}, channelId: {}, encoding: {}, talkHandle: {}",
                platformName, getSessionKey(), channelId, talkSession.getEncoding().getName(), talkHandle.longValue());
        return true;
    }

    /**
     * 发送客户端上行裸 G711 音频帧到设备。
     *
     * @param talkSession 对讲会话
     * @param audioBytes 裸音频帧
     * @return 是否发送成功
     */
    public synchronized boolean sendTalkAudio(TalkSessionRegistry.TalkSession talkSession, byte[] audioBytes) {
        if (talkSession == null || talkSession.isClosed()
                || talkSession.getTalkHandle() == null || talkSession.getTalkHandle().longValue() == 0
                || audioBytes == null || audioBytes.length == 0) {
            return false;
        }

        Memory audioBuffer = new Memory(audioBytes.length);
        try {
            audioBuffer.write(0, audioBytes, 0, audioBytes.length);
            NET_IN_TALK_SEND_DATA_STREAM inParam = new NET_IN_TALK_SEND_DATA_STREAM();
            NET_OUT_TALK_SEND_DATA_STREAM outParam = new NET_OUT_TALK_SEND_DATA_STREAM();
            inParam.pBuf = audioBuffer;
            inParam.dwBufSize = audioBytes.length;
            inParam.bNeedHead = 1;
            inParam.emEncodeType = talkSession.getEncoding().getSdkValue();
            inParam.nAudioBit = 8;
            inParam.dwSampleRate = 8000;
            inParam.write();
            outParam.write();

            NetSDKLib.LLong result = NET_SDK.CLIENT_TalkSendDataByStream(talkSession.getTalkHandle(),
                    inParam.getPointer(), outParam.getPointer());
            boolean success = result.longValue() != 0;
            if (!success) {
                LOGGER.warn("\n{} 发送大华语音对讲音频失败，device: {}, talkSessionId: {}, 错误码: 0x{}",
                        platformName, getSessionKey(), talkSession.getSessionId(),
                        Integer.toHexString(NET_SDK.CLIENT_GetLastError()));
            }
            return success;
        } finally {
            audioBuffer.close();
        }
    }

    /** 停止语音对讲。 */
    public synchronized void stopTalk(TalkSessionRegistry.TalkSession talkSession) {
        if (talkSession != null) {
            talkSession.close();
        }
    }

    /**
     * 查询当前通道的云台预置点列表。
     *
     * @param channelId 通道号，从 0 开始
     * @return 预置点列表；未登录或查询失败时返回空列表
     */
    public synchronized List<PtzPresetInfoDTO> listPresets(int channelId) {
        List<PtzPresetInfoDTO> presets = new ArrayList<>();
        if (!isLoggedIn()) {
            LOGGER.warn("\n{} 大华设备未登录，无法查询预置点列表，device: {}", platformName, getSessionKey());
            return presets;
        }

        NetSDKLib.NET_PTZ_PRESET[] presetArray = createPresetArray();
        NetSDKLib.NET_PTZ_PRESET_LIST presetList = new NetSDKLib.NET_PTZ_PRESET_LIST();
        presetList.dwMaxPresetNum = MAX_PRESET_COUNT;
        presetList.pstuPtzPorsetList = new Memory((long) presetArray[0].size() * MAX_PRESET_COUNT);
        ToolKits.SetStructArrToPointerData(presetArray, presetList.pstuPtzPorsetList);
        presetList.write();

        boolean success = NET_SDK.CLIENT_QueryRemotDevState(loginHandle, NetSDKLib.NET_DEVSTATE_PTZ_PRESET_LIST,
                channelId, presetList.getPointer(), presetList.size(), new IntByReference(0), QUERY_TIMEOUT_MS);
        if (!success) {
            LOGGER.warn("\n{} 查询大华设备预置点列表失败，device: {}, channelId: {}, 错误码: 0x{}",
                    platformName, getSessionKey(), channelId, Integer.toHexString(NET_SDK.CLIENT_GetLastError()));
            return presets;
        }

        presetList.read();
        ToolKits.GetPointerDataToStructArr(presetList.pstuPtzPorsetList, presetArray);
        int retCount = Math.min(presetList.dwRetPresetNum, MAX_PRESET_COUNT);
        for (int i = 0; i < retCount; i++) {
            presets.add(toPresetInfo(presetArray[i]));
        }
        return presets;
    }

    /**
     * 切换到指定云台预置点。
     *
     * @param channelId 通道号，从 0 开始
     * @param presetIndex 预置点编号，通常从 1 开始
     * @return 是否切换成功
     */
    public synchronized boolean gotoPreset(int channelId, int presetIndex) {
        if (!isLoggedIn()) {
            LOGGER.warn("\n{} 大华设备未登录，无法切换预置点，device: {}", platformName, getSessionKey());
            return false;
        }

        boolean success = NET_SDK.CLIENT_DHPTZControlEx(loginHandle, channelId,
                NetSDKLib.NET_PTZ_ControlType.NET_PTZ_POINT_MOVE_CONTROL, 0, presetIndex, 0, 0);
        if (!success) {
            LOGGER.warn("\n{} 大华设备切换预置点失败，device: {}, channelId: {}, presetIndex: {}, 错误码: 0x{}",
                    platformName, getSessionKey(), channelId, presetIndex,
                    Integer.toHexString(NET_SDK.CLIENT_GetLastError()));
        }
        return success;
    }

    /** 会话标识，格式为 ip:port，用于日志和缓存定位。 */
    public String getSessionKey() {
        return ip + ":" + port;
    }

    /** 判断当前是否持有有效登录句柄。 */
    private boolean isLoggedIn() {
        return loginHandle != null && loginHandle.longValue() != 0;
    }

    /** 返回当前登录句柄值，主要用于日志或后续业务排查。 */
    public long getLoginHandle() {
        return loginHandle != null ? loginHandle.longValue() : 0;
    }

    /**
     * 创建预置点结构数组，用于接收 SDK 返回的列表数据。
     *
     * @return 已初始化的预置点结构数组
     */
    private NetSDKLib.NET_PTZ_PRESET[] createPresetArray() {
        // 必须用 toArray 分配连续内存，JNA 才能正确按数组处理结构体。
        return (NetSDKLib.NET_PTZ_PRESET[]) new NetSDKLib.NET_PTZ_PRESET().toArray(MAX_PRESET_COUNT);
    }

    /**
     * 启动 SDK 录像下载任务。
     *
     * @param task 下载任务
     * @param channelId 通道号，从 0 开始
     * @param startTime 下载开始时间
     * @param endTime 下载结束时间
     * @param recordFileType 录像类型
     */
    private synchronized void startDownloadRecordFile(RecordDownloadTaskRegistry.RecordDownloadTask task,
                                                      int channelId, LocalDateTime startTime,
                                                      LocalDateTime endTime, int recordFileType) {
        if (!isLoggedIn()) {
            task.fail("设备未登录");
            return;
        }

        File file = new File(task.getFilePath());
        File parent = file.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            task.fail("创建录像下载目录失败: " + parent.getAbsolutePath());
            return;
        }

        NetSDKLib.NET_IN_DOWNLOAD_BY_DATA_TYPE inParam = new NetSDKLib.NET_IN_DOWNLOAD_BY_DATA_TYPE();
        inParam.nChannelID = channelId;
        inParam.emRecordType = recordFileType;
        inParam.stStartTime = toNetTime(startTime);
        inParam.stStopTime = toNetTime(endTime);
        inParam.szSavedFileName = task.getFilePath();
        inParam.cbDownLoadPos = task.getCallback();
        inParam.dwPosUser = null;
        inParam.fDownLoadDataCallBack = null;
        inParam.dwDataUser = null;
        inParam.emDataType = NetSDKLib.EM_REAL_DATA_TYPE.EM_REAL_DATA_TYPE_MP4;

        NetSDKLib.NET_OUT_DOWNLOAD_BY_DATA_TYPE outParam = new NetSDKLib.NET_OUT_DOWNLOAD_BY_DATA_TYPE();
        inParam.write();
        outParam.write();
        NetSDKLib.LLong downloadHandle = NET_SDK.CLIENT_DownloadByDataType(loginHandle,
                inParam.getPointer(), outParam.getPointer(), RECORD_QUERY_TIMEOUT_MS);
        if (downloadHandle.longValue() == 0) {
            String error = "MP4录像下载启动失败，错误码: 0x" + Integer.toHexString(NET_SDK.CLIENT_GetLastError());
            LOGGER.warn("\n{} {}, device: {}, channelId: {}, file: {}",
                    platformName, error, getSessionKey(), channelId, task.getFilePath());
            task.fail(error);
            return;
        }

        task.start(downloadHandle);
        LOGGER.info("\n{} 大华设备MP4录像下载已启动，device: {}, channelId: {}, taskId: {}, file: {}",
                platformName, getSessionKey(), channelId, task.getTaskId(), task.getFilePath());
    }

    /**
     * 创建录像文件结构数组。
     *
     * @param maxCount 最大返回条数
     * @return 已初始化的录像文件结构数组
     */
    private NetSDKLib.NET_RECORDFILE_INFO[] createRecordFileArray(int maxCount) {
        // CLIENT_QueryRecordFile 直接以结构体数组作为入参，元素内存必须连续，
        // 否则 JNA 会抛 "Structure array elements must use contiguous memory"，
        // 因此用 toArray 一次性分配整块连续内存。
        return (NetSDKLib.NET_RECORDFILE_INFO[]) new NetSDKLib.NET_RECORDFILE_INFO().toArray(maxCount);
    }

    /** 将 SDK 语音编码结构转换为接口返回对象。 */
    private TalkFormatDTO toTalkFormat(NetSDKLib.NETDEV_TALKDECODE_INFO decodeInfo) {
        String name = toTalkEncodingName(decodeInfo.encodeType);
        if (name == null) {
            return null;
        }

        TalkFormatDTO dto = new TalkFormatDTO();
        dto.setEncodeType(decodeInfo.encodeType);
        dto.setName(name);
        dto.setAudioBit(decodeInfo.nAudioBit);
        dto.setSampleRate(decodeInfo.dwSampleRate);
        dto.setPacketPeriod(decodeInfo.nPacketPeriod);
        return dto;
    }

    /** 构造 SDK 对讲编码参数，G711 通常使用 8kHz/8bit/25ms。 */
    private NetSDKLib.NETDEV_TALKDECODE_INFO buildTalkDecodeInfo(TalkSessionRegistry.TalkEncoding encoding) {
        NetSDKLib.NETDEV_TALKDECODE_INFO talkEncode = new NetSDKLib.NETDEV_TALKDECODE_INFO();
        talkEncode.encodeType = encoding.getSdkValue();
        talkEncode.dwSampleRate = 8000; // 8000Hz
        talkEncode.nAudioBit = 8; // 8 bit
        talkEncode.nPacketPeriod = 25; // 打包周期
        return talkEncode;
    }

    /** 设置语音对讲编码格式。 */
    private boolean setTalkEncodeType(NetSDKLib.NETDEV_TALKDECODE_INFO talkEncode) {
        talkEncode.write();
        boolean success = NET_SDK.CLIENT_SetDeviceMode(loginHandle,
                NetSDKLib.EM_USEDEV_MODE.NET_TALK_ENCODE_TYPE, talkEncode.getPointer());
        if (!success) {
            LOGGER.warn("\n{} 设置大华语音对讲编码失败，device: {}, encoding: {}, 错误码: 0x{}",
                    platformName, getSessionKey(), talkEncode.encodeType,
                    Integer.toHexString(NET_SDK.CLIENT_GetLastError()));
        }
        return success;
    }

    /** 设置语音对讲模式。 */
    private boolean setTalkSpeakParam() {
        NetSDKLib.NET_SPEAK_PARAM speakParam = new NetSDKLib.NET_SPEAK_PARAM();
        speakParam.nMode = 0;
        speakParam.bEnableWait = 0;
        speakParam.nSpeakerChannel = 0;
        speakParam.write();
        boolean success = NET_SDK.CLIENT_SetDeviceMode(loginHandle,
                NetSDKLib.EM_USEDEV_MODE.NET_TALK_SPEAK_PARAM, speakParam.getPointer());
        if (!success) {
            LOGGER.warn("\n{} 设置大华语音对讲模式失败，device: {}, 错误码: 0x{}",
                    platformName, getSessionKey(), Integer.toHexString(NET_SDK.CLIENT_GetLastError()));
        }
        return success;
    }

    /** 设置 NVR 等多通道设备的对讲转发通道。 */
    private boolean setTalkChannel(int channelId) {
        NetSDKLib.NET_TALK_TRANSFER_PARAM transferParam = new NetSDKLib.NET_TALK_TRANSFER_PARAM();
        transferParam.bTransfer = 1; // 开启语音对讲转发模式
        transferParam.write();
        boolean transferSuccess = NET_SDK.CLIENT_SetDeviceMode(loginHandle,
                NetSDKLib.EM_USEDEV_MODE.NET_TALK_TRANSFER_MODE, transferParam.getPointer());
        if (!transferSuccess) {
            LOGGER.warn("\n{} 设置大华语音对讲转发模式失败，device: {}, channelId: {}, 错误码: 0x{}",
                    platformName, getSessionKey(), channelId, Integer.toHexString(NET_SDK.CLIENT_GetLastError()));
            return false;
        }

        IntByReference channel = new IntByReference(channelId);
        boolean channelSuccess = NET_SDK.CLIENT_SetDeviceMode(loginHandle,
                NetSDKLib.EM_USEDEV_MODE.NET_TALK_TALK_CHANNEL, channel.getPointer());
        if (!channelSuccess) {
            LOGGER.warn("\n{} 设置大华语音对讲通道失败，device: {}, channelId: {}, 错误码: 0x{}",
                    platformName, getSessionKey(), channelId, Integer.toHexString(NET_SDK.CLIENT_GetLastError()));
        }
        return channelSuccess;
    }

    /**
     * 将 SDK 云台位置结构转换为接口返回对象。
     *
     * @param location SDK 返回的云台位置结构
     * @return 云台位置信息
     */
    private PtzLocationInfoDTO toPtzLocationInfo(NetSDKLib.NET_PTZ_LOCATION_INFO location) {
        PtzLocationInfoDTO dto = new PtzLocationInfoDTO();
        dto.setChannelId(location.nChannelID);
        dto.setPtzPan(location.nPTZPan);
        dto.setPanDegree(toRatio(location.nPTZPan, 10));
        dto.setPtzTilt(location.nPTZTilt);
        dto.setTiltDegree(toRatio(location.nPTZTilt, 10));
        dto.setPtzZoom(location.nPTZZoom);
        dto.setFocusPosition(location.fFocusPosition);
        dto.setZoomValue(location.nZoomValue);
        dto.setZoomValueRatio(toRatio(location.nZoomValue, 100));
        dto.setAbsPositionX(location.stuAbsPosition.nPosX);
        dto.setAbsPositionXDegree(toRatio(location.stuAbsPosition.nPosX, 100));
        dto.setAbsPositionY(location.stuAbsPosition.nPosY);
        dto.setAbsPositionYDegree(toRatio(location.stuAbsPosition.nPosY, 100));
        dto.setAbsPositionZoom(location.stuAbsPosition.nZoom);
        dto.setAbsPositionZoomRatio(toRatio(location.stuAbsPosition.nZoom, 100));
        dto.setFocusMapValue(location.nFocusMapValue);
        dto.setZoomMapValue(location.nZoomMapValue);
        dto.setState(Byte.toUnsignedInt(location.bState));
        dto.setFocusState(Byte.toUnsignedInt(location.bFocusState));
        dto.setZoomState(Byte.toUnsignedInt(location.bZoomState));
        return dto;
    }

    /**
     * 将 SDK 录像文件结构转换为接口返回对象。
     *
     * @param record SDK 返回的录像文件结构
     * @return 录像文件信息
     */
    private RecordFileInfoDTO toRecordFileInfo(NetSDKLib.NET_RECORDFILE_INFO record) {
        RecordFileInfoDTO dto = new RecordFileInfoDTO();
        dto.setChannelId(record.ch);
        dto.setFileName(decodeSdkString(record.filename));
        dto.setFileSize(record.size);
        dto.setFrameCount(record.framenum);
        dto.setRecordFileType(record.nRecordFileType & 0xFF);
        dto.setStreamType(record.bRecType & 0xFF);
        dto.setImportant(record.bImportantRecID != 0);
        dto.setStartTime(toDateTimeString(record.starttime));
        dto.setEndTime(toDateTimeString(record.endtime));
        return dto;
    }

    /**
     * 将 SDK 预置点结构转换为接口返回对象。
     *
     * @param preset SDK 返回的预置点结构
     * @return 预置点返回对象
     */
    private PtzPresetInfoDTO toPresetInfo(NetSDKLib.NET_PTZ_PRESET preset) {
        PtzPresetInfoDTO dto = new PtzPresetInfoDTO();
        dto.setIndex(preset.nIndex);
        dto.setName(resolvePresetName(preset));
        dto.setHorizontal(preset.nPosition[0]);
        dto.setVertical(preset.nPosition[1]);
        dto.setZoom(preset.nPosition[2]);
        return dto;
    }

    /**
     * 获取预置点名称，优先使用扩展名称字段。
     *
     * @param preset SDK 返回的预置点结构
     * @return 去除结尾空字符后的预置点名称
     */
    private String resolvePresetName(NetSDKLib.NET_PTZ_PRESET preset) {
        String nameEx = decodeSdkString(preset.szNameEx);
        return nameEx.isEmpty() ? decodeSdkString(preset.szName) : nameEx;
    }

    /**
     * 解析 SDK 字节数组字符串，遇到 C 字符串结束符 0 即停止。
     *
     * @param bytes SDK 返回的固定长度字节数组
     * @return UTF-8 解码后的字符串
     */
    private static String decodeSdkString(byte[] bytes) {
        int length = 0;
        while (length < bytes.length && bytes[length] != 0) {
            length++;
        }
        return new String(bytes, 0, length, StandardCharsets.UTF_8).trim();
    }

    /**
     * 将 Java 时间转换为 SDK 时间结构。
     *
     * @param dateTime Java 时间
     * @return SDK 时间结构
     */
    private static NetSDKLib.NET_TIME toNetTime(LocalDateTime dateTime) {
        NetSDKLib.NET_TIME netTime = new NetSDKLib.NET_TIME();
        netTime.setTime(dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth(),
                dateTime.getHour(), dateTime.getMinute(), dateTime.getSecond());
        return netTime;
    }

    /**
     * 将 SDK 时间结构格式化为接口时间字符串。
     *
     * @param netTime SDK 时间结构
     * @return yyyy-MM-dd HH:mm:ss 格式时间
     */
    private static String toDateTimeString(NetSDKLib.NET_TIME netTime) {
        return String.format("%04d-%02d-%02d %02d:%02d:%02d",
                netTime.dwYear, netTime.dwMonth, netTime.dwDay,
                netTime.dwHour, netTime.dwMinute, netTime.dwSecond);
    }

    /**
     * 构造录像下载文件路径。
     *
     * @param channelId 通道号
     * @param startTime 下载开始时间
     * @param endTime 下载结束时间
     * @param fileName 用户指定文件名
     * @return 本地录像文件绝对路径
     */
    private String buildRecordFilePath(int channelId, LocalDateTime startTime, LocalDateTime endTime, String fileName) {
        String safeFileName = isBlank(fileName)
                ? String.format("%s_%d_ch%d_%s_%s.mp4", ip.replace('.', '_'), port, channelId,
                compactTime(startTime), compactTime(endTime))
                : fileName.trim();
        safeFileName = safeFileName.replaceAll("[\\\\/:*?\"<>|]", "_");
        if (!safeFileName.toLowerCase().endsWith(".mp4")) {
            safeFileName = safeFileName + ".mp4";
        }
        return new File("./record/", safeFileName).getAbsolutePath();
    }

    private static String compactTime(LocalDateTime dateTime) {
        return String.format("%04d%02d%02d%02d%02d%02d",
                dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth(),
                dateTime.getHour(), dateTime.getMinute(), dateTime.getSecond());
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private static Double toRatio(int value, int scale) {
        return value / (double) scale;
    }

    private static String toTalkEncodingName(int encodeType) {
        if (encodeType == NetSDKLib.NET_TALK_CODING_TYPE.NET_TALK_G711a) {
            return "G711A";
        }
        if (encodeType == NetSDKLib.NET_TALK_CODING_TYPE.NET_TALK_G711u) {
            return "G711U";
        }
        return null;
    }

    /** 将字符串写入 SDK 固定长度字节数组，预留末尾 1 字节作为 C 字符串结束符。 */
    private static void copyString(String value, byte[] target) {
        byte[] source = value.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(source, 0, target, 0, Math.min(source.length, target.length - 1));
    }
}
