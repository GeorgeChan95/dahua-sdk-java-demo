package com.csg.demo.dahua.enums;

import com.csg.demo.dahua.lib.NetSDKLib;

/**
 * 云台控制命令枚举，统一映射官方 SDK 的基础云台命令。
 * 接口传参可使用 {@link #getSdkCommand()} 返回值（int），与 NET_PTZ_ControlType / NET_EXTPTZ_ControlType 一致。
 */
public enum PtzCommand {

    /** 向上，SDK 命令值 0 */
    UP(NetSDKLib.NET_PTZ_ControlType.NET_PTZ_UP_CONTROL),

    /** 向下，SDK 命令值 1 */
    DOWN(NetSDKLib.NET_PTZ_ControlType.NET_PTZ_DOWN_CONTROL),

    /** 向左，SDK 命令值 2 */
    LEFT(NetSDKLib.NET_PTZ_ControlType.NET_PTZ_LEFT_CONTROL),

    /** 向右，SDK 命令值 3 */
    RIGHT(NetSDKLib.NET_PTZ_ControlType.NET_PTZ_RIGHT_CONTROL),

    /** 向左上，SDK 命令值 0x20（32） */
    LEFT_UP(NetSDKLib.NET_EXTPTZ_ControlType.NET_EXTPTZ_LEFTTOP),

    /** 向右上，SDK 命令值 0x21（33） */
    RIGHT_UP(NetSDKLib.NET_EXTPTZ_ControlType.NET_EXTPTZ_RIGHTTOP),

    /** 向左下，SDK 命令值 0x22（34） */
    LEFT_DOWN(NetSDKLib.NET_EXTPTZ_ControlType.NET_EXTPTZ_LEFTDOWN),

    /** 向右下，SDK 命令值 0x23（35） */
    RIGHT_DOWN(NetSDKLib.NET_EXTPTZ_ControlType.NET_EXTPTZ_RIGHTDOWN),

    /** 变倍放大，SDK 命令值 4 */
    ZOOM_ADD(NetSDKLib.NET_PTZ_ControlType.NET_PTZ_ZOOM_ADD_CONTROL),

    /** 变倍缩小，SDK 命令值 5 */
    ZOOM_DEC(NetSDKLib.NET_PTZ_ControlType.NET_PTZ_ZOOM_DEC_CONTROL),

    /** 变焦拉近，SDK 命令值 6 */
    FOCUS_ADD(NetSDKLib.NET_PTZ_ControlType.NET_PTZ_FOCUS_ADD_CONTROL),

    /** 变焦拉远，SDK 命令值 7 */
    FOCUS_DEC(NetSDKLib.NET_PTZ_ControlType.NET_PTZ_FOCUS_DEC_CONTROL),

    /** 光圈放大，SDK 命令值 8 */
    IRIS_ADD(NetSDKLib.NET_PTZ_ControlType.NET_PTZ_APERTURE_ADD_CONTROL),

    /** 光圈缩小，SDK 命令值 9 */
    IRIS_DEC(NetSDKLib.NET_PTZ_ControlType.NET_PTZ_APERTURE_DEC_CONTROL);

    /** SDK 云台命令原始值，可直接传给 CLIENT_DHPTZControlEx 的 dwPTZCommand 参数 */
    private final int sdkCommand;

    /** 保存当前枚举对应的 SDK 云台命令值。 */
    PtzCommand(int sdkCommand) {
        this.sdkCommand = sdkCommand;
    }

    /** 返回 SDK 可识别的云台命令值。 */
    public int getSdkCommand() {
        return sdkCommand;
    }
}
