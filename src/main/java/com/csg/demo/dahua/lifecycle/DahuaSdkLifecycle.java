package com.csg.demo.dahua.lifecycle;

import com.csg.demo.config.DahuaPlatform;
import com.csg.demo.config.DahuaSdkProperties;
import com.csg.demo.dahua.support.NativeLibraryExtractor;
import com.csg.demo.dahua.support.PlatformDetector;
import com.csg.demo.dahua.lib.LibraryLoad;
import com.csg.demo.dahua.lib.NetSDKLib;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * 大华 SDK 生命周期管理
 * 负责大华 SDK 的初始化、释放、重连等操作
 * 支持Windows64、Linux64、UNSUPPORTED
 *  SmartLifecycle: 智能生命周期接口,用于管理大华 SDK 的生命周期,实现start、stop、isRunning、isAutoStartup、getPhase方法
 *  start: 初始化大华 SDK
 *  stop: 释放大华 SDK 资源
 *  isRunning: 判断大华 SDK 是否正在运行
 *  isAutoStartup: 判断大华 SDK 是否自动启动
 * @author George
 * @version 1.0
 * @since 2026-06-08
 */
@Slf4j
@Component
public class DahuaSdkLifecycle implements SmartLifecycle {

    // 平台检测器,用于检测当前平台
    private final PlatformDetector platformDetector;
    // 大华 SDK 配置,用于配置大华 SDK 的参数
    private final DahuaSdkProperties properties;
    // 原生库提取器,用于提取原生库,用于将原生库提取到临时目录
    private final NativeLibraryExtractor nativeLibraryExtractor;

    private volatile boolean running; // 是否正在运行
    private volatile boolean initialized; // 是否已初始化
    private NetSDKLib netSdk; // 大华 SDK 实例
    private Object disconnectCallback; // 断开连接回调,用于处理断开连接事件
    private Object reconnectCallback; // 重连回调,用于处理重连事件

    public DahuaSdkLifecycle(PlatformDetector platformDetector,
                             DahuaSdkProperties properties,
                             NativeLibraryExtractor nativeLibraryExtractor) {
        this.platformDetector = platformDetector;
        this.properties = properties;
        this.nativeLibraryExtractor = nativeLibraryExtractor;
    }


    /**
     * 初始化大华 SDK
     * 1. 检查是否正在运行
     * 2. 检查平台是否支持
     * 3. 准备原生库
     * 4. 初始化大华 SDK
     * 5. 设置断开连接回调
     * 6. 设置重连回调
     * 7. 设置连接时间
     * 8. 设置网络参数
     */
    @Override
    public void start() {
        if (running) { // 如果正在运行,则直接返回
            return;
        }
        running = true; // 设置为正在运行

        // 根据 JVM 系统属性判断平台,如果平台不支持,则直接返回
        DahuaPlatform platform = platformDetector.detect();
        if (!platform.isSupported()) {
            log.warn("当前平台不支持大华 SDK 自动初始化: {}", platformDetector.currentPlatformDescription());
            return;
        }

        try {
            // 准备原生库,将原生库提取到临时目录
            prepareNativeLibraries(platform);
            // 初始化大华 SDK
            initializeSdk();
            // 设置为已初始化
            initialized = true;
            log.info("大华 SDK 初始化成功，平台: {}", platform.getConfigKey());
        } catch (Exception ex) {
            running = false;
            throw new IllegalStateException("大华 SDK 初始化失败", ex);
        }
    }

    /**
     * 在 Spring 容器关闭时释放大华 SDK 占用的原生资源，并重置组件内部状态
     */
    @Override
    public void stop() {
        if (initialized && netSdk != null) { // 仅在 initialized == true 且 netSdk != null 时执行
            try {
                netSdk.CLIENT_Cleanup();
                log.info("大华 SDK 资源已释放");
            } catch (Exception ex) {
                log.warn("大华 SDK 资源释放失败", ex);
            }
        }
        initialized = false;
        running = false;
        netSdk = null;
        disconnectCallback = null;
        reconnectCallback = null;
    }

    /**
     * pring 关闭时可能调用这个重载，用于通知「该 Lifecycle 已停止」
     */
    @Override
    public void stop(Runnable callback) {
        try {
            stop();
        } finally {
            callback.run();
        }
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    /**
     * Spring 容器启动时自动调用 `start()`
     * @return
     */
    @Override
    public boolean isAutoStartup() {
        return true;
    }

    /**
     * 尽量早执行，确保 SDK 初始化在其他业务组件使用 SDK 前完成
     * @return
     */
    @Override
    public int getPhase() {
        return Integer.MIN_VALUE;
    }

    /**
     * 准备原生库
     * 1. 获取原生库列表
     * 2. 提取原生库
     * 3. 设置原生库路径
     * 4. 打印日志
     * @param platform
     * @throws IOException
     */
    private void prepareNativeLibraries(DahuaPlatform platform) throws IOException {
        // 读取从yml配置文件中，加载的库文件路径集合
        List<String> libraries = properties.getLibraries(platform).getLibraries();
        // 将yml中的原生库文件路径，从resources文件夹下，复制到临时目录中
        Path libraryDirectory = nativeLibraryExtractor.extract(platform, libraries);
        // 设置原生库路径,用于加载原生库
        // windows下：C:\Users\xxx\AppData\Local\Temp\dahua-sdk-windows64-123456789\
        // linux下：/tmp/dahua-sdk-linux64-123456789/
        LibraryLoad.setExtractPath(libraryDirectory.toString());
        // 打印日志,提示原生库已准备完成
        log.info("大华 SDK 原生库已准备完成，平台: {}, 目录: {}", platform.getConfigKey(), libraryDirectory);
    }

    private void initializeSdk() {
        // 设置断开连接回调，当设备连接断开时，会调用该回调函数
        NetSDKLib.fDisConnect disconnect = (loginId, deviceIp, devicePort, user) ->
                log.warn("大华设备连接断开，loginId: {}, device: {}:{}", loginId.longValue(), deviceIp, devicePort);
        // 设置重连回调，当设备重连成功时，会调用该回调函数
        NetSDKLib.fHaveReConnect reconnect = (loginId, deviceIp, devicePort, user) ->
                log.info("大华设备重连成功，loginId: {}, device: {}:{}", loginId.longValue(), deviceIp, devicePort);

        disconnectCallback = disconnect;
        reconnectCallback = reconnect;
        // 获取大华 SDK 实例，单例模式，保证全局唯一
        netSdk = NetSDKLib.NETSDK_INSTANCE;

        // 初始化大华 SDK
        boolean initSuccess = netSdk.CLIENT_Init(disconnect, null);
        if (!initSuccess) {
            throw new IllegalStateException(String.format("大华SDK初始化异常，错误码为: 0x%x", netSdk.CLIENT_GetLastError()));
        }

        // 设置重连回调
        netSdk.CLIENT_SetAutoReconnect(reconnect, null);
        // 设置连接时间
        netSdk.CLIENT_SetConnectTime(5000, 1);
        // 设置网络参数
        NetSDKLib.NET_PARAM netParam = new NetSDKLib.NET_PARAM();
        netParam.nConnectTime = 10000; // 登录时尝试建立链接的超时时间
        netParam.nGetConnInfoTime = 3000; // 设置子连接的超时时间
        netParam.nGetDevInfoTime = 3000; // 获取设备信息超时时间，为0默认1000ms
        netSdk.CLIENT_SetNetworkParam(netParam);
    }
}
