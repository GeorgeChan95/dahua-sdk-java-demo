package com.csg.demo.dahua.support;

import com.csg.demo.dahua.lib.NetSDKLib;
import com.csg.demo.dahua.lib.ToolKits;
import com.csg.demo.dto.PtzPresetInfoDTO;
import com.sun.jna.Memory;
import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
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
        NetSDKLib.NET_PTZ_PRESET[] presetArray = new NetSDKLib.NET_PTZ_PRESET[MAX_PRESET_COUNT];
        for (int i = 0; i < presetArray.length; i++) {
            presetArray[i] = new NetSDKLib.NET_PTZ_PRESET();
        }
        return presetArray;
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

    /** 将字符串写入 SDK 固定长度字节数组，预留末尾 1 字节作为 C 字符串结束符。 */
    private static void copyString(String value, byte[] target) {
        byte[] source = value.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(source, 0, target, 0, Math.min(source.length, target.length - 1));
    }
}
