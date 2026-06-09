package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 水质检测上报事件 对应 DH_ALARM_WATER_QUALITY_DETECTION
*/
public class ALARM_WATER_QUALITY_DETECTION_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 通道号
    */
    public int              nChannelID;
    /**
     * 事件类型 0:脉冲,1:开始, 2:停止
    */
    public int              nAction;
    /**
     * 事件名称
    */
    public byte[]           szName = new byte[128];
    /**
     * 智能事件所属大类
    */
    public byte[]           szClass = new byte[16];
    /**
     * 事件发生的时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME_EX}
    */
    public NetSDKLib.NET_TIME_EX stuUTC = new NetSDKLib.NET_TIME_EX();
    /**
     * 水质检测上报数据信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_WATER_DETECTION_UPLOAD_INFO}
    */
    public NET_WATER_DETECTION_UPLOAD_INFO stuUploadInfo = new NET_WATER_DETECTION_UPLOAD_INFO();
    /**
     * 事件报警类型,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.EM_WATER_DETECTION_ALARM_TYPE}
    */
    public int[]            emAlarmType = new int[32];
    /**
     * 事件报警类型个数
    */
    public int              nAlarmTypeNum;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public ALARM_WATER_QUALITY_DETECTION_INFO() {
    }
}

