package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 事件类型 DH_ALARM_ALERT_SCREEN_INFO (设备端测速屏配置界面可实时显示测速屏上的内容，屏接上后，自动定期发送)对应的数据块描述信息
*/
public class ALARM_ALERT_SCREEN_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 通道号
    */
    public int              nChannelID;
    /**
     * 事件动作,0表示脉冲事件,1表示事件开始,2表示事件结束;
    */
    public int              nAction;
    /**
     * 事件公共扩展字段结构体,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_EVENT_INFO_EXTEND}
    */
    public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND();
    /**
     * 事件发生的时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME_EX}
    */
    public NetSDKLib.NET_TIME_EX stuUTC = new NetSDKLib.NET_TIME_EX();
    /**
     * 显示屏请求操作类型, 201: 显示屏亮度调节 218: 显示屏显示数据
    */
    public int              nDisplayOpType;
    /**
     * 屏幕类型, "LX": 灵信视觉显示屏 "GR": 格瑞普光电显示屏 "XY": 园区测速屏（芯源显示屏）
    */
    public byte[]           szScreenType = new byte[32];
    /**
     * 屏的 IP 地址
    */
    public byte[]           szScreenIPAddress = new byte[16];
    /**
     * 屏幕亮度参数设置,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_ADJUST_BRIGHT}
    */
    public NET_ADJUST_BRIGHT stuAdjustBright = new NET_ADJUST_BRIGHT();
    /**
     * 显示更新请求相关,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_DISPLAY_SHOW_DATA_INFO}
    */
    public NET_DISPLAY_SHOW_DATA_INFO stuDisplayShowData = new NET_DISPLAY_SHOW_DATA_INFO();
    /**
     * 显示屏 ID
    */
    public int              nDisplayID;
    /**
     * 预留字段
    */
    public byte[]           szReserved = new byte[1020];

    public ALARM_ALERT_SCREEN_INFO() {
    }
}

