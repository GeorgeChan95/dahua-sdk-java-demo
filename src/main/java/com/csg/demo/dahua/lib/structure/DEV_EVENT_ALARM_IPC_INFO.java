package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 事件类型EVENT_IVS_ALARM_IPC(DVR/NVR设备上的IPC报警 )对应的数据块描述信息
*/
public class DEV_EVENT_ALARM_IPC_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 通道号
    */
    public int              nChannelID;
    /**
     * 事件名称
    */
    public byte[]           szName = new byte[128];
    /**
     * 字节对齐
    */
    public byte[]           bReserved1 = new byte[4];
    /**
     * 时间戳(单位是毫秒)
    */
    public double           PTS;
    /**
     * 事件发生的时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME_EX}
    */
    public NetSDKLib.NET_TIME_EX UTC = new NetSDKLib.NET_TIME_EX();
    /**
     * 事件ID
    */
    public int              nEventID;
    /**
     * 0:开始 1:停止
    */
    public int              nAction;
    /**
     * 保留字节
    */
    public byte[]           byReserved = new byte[1024];

    public DEV_EVENT_ALARM_IPC_INFO() {
    }
}

