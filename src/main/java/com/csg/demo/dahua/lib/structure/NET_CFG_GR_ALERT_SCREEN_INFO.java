package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 格瑞普光电显示屏信息
*/
public class NET_CFG_GR_ALERT_SCREEN_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 显示屏请求操作类型;197:显示屏校对时间;201:显示屏亮度调节;218:显示屏显示数据;222:显示屏语音播报
    */
    public int              nDisplayOpType;
    /**
     * 显示屏服务器IP地址
    */
    public byte[]           szDisplayIpAddress = new byte[32];
    /**
     * 显示屏ID
    */
    public int              nDisplayID;
    /**
     * 道路安全预警的预警周期，单位毫秒
    */
    public int              nTrafficRoadAlertPeriod;
    /**
     * 是否启用
    */
    public int              bEnable;
    /**
     * 速度限制,单位:km/h
    */
    public int              nSpeedLimit;
    /**
     * 预警事件类型
    */
    public byte[]           szAlertEventType = new byte[32];
    /**
     * 预警屏类型
    */
    public byte[]           szAlertScreenType = new byte[32];
    /**
     * 显示更新请求相关配置(218),参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_DISPLAY_SHOW_DATA}
    */
    public NET_DISPLAY_SHOW_DATA stuDisplayShowData = new NET_DISPLAY_SHOW_DATA();
    /**
     * 屏幕语音参数设置(222),参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_SMALL_DISPLAY_VOICE_MES}
    */
    public NET_SMALL_DISPLAY_VOICE_MES stuSmallDisplayVoiceMes = new NET_SMALL_DISPLAY_VOICE_MES();
    /**
     * 是否下发 DisplayShowData 字段
    */
    public int              bUseDisplayShowData;
    /**
     * 是否下发 SmallDisplayVoiceMes 字段
    */
    public int              bUseSmallDisplayVoiceMes;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1020];

    public NET_CFG_GR_ALERT_SCREEN_INFO() {
    }
}

