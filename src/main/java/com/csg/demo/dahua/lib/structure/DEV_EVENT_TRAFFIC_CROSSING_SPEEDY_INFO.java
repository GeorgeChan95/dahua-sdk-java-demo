package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 事件类型 EVENT_IVS_TRAFFIC_CROSSING_SPEEDY (斑马线不减速事件)对应的数据块描述信息
*/
public class DEV_EVENT_TRAFFIC_CROSSING_SPEEDY_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 通道号
    */
    public int              nChannelID;
    /**
     * 0:脉冲,1:开始, 2:停止
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
     * GroupID事件组ID，同一物体抓拍过程内GroupID相同
    */
    public int              nGroupID;
    /**
     * CountInGroup一个事件组内的抓拍张数
    */
    public int              nCountInGroup;
    /**
     * IndexInGroup一个事件组内的抓拍序号，从1开始
    */
    public int              nIndexInGroup;
    /**
     * 事件时间毫秒数
    */
    public int              nUTCMS;
    /**
     * 相对事件时间戳,(单位是毫秒)
    */
    public double           dbPTS;
    /**
     * 事件发生的时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME_EX}
    */
    public NetSDKLib.NET_TIME_EX stuUTC = new NetSDKLib.NET_TIME_EX();
    /**
     * 事件ID
    */
    public int              nEventID;
    /**
     * 触发类型,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.EM_TRIGGER_TYPE}
    */
    public int              emTriggerType;
    /**
     * 用于标记抓拍帧
    */
    public int              nMark;
    /**
     * 视频分析的数据源地址
    */
    public int              nSource;
    /**
     * 视频分析帧序号
    */
    public int              nFrameSequence;
    /**
     * 对应车道号
    */
    public int              nLane;
    /**
     * 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
    */
    public int              nSequence;
    /**
     * 车速，单位km/h
    */
    public int              nSpeed;
    /**
     * 车牌信息,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_MSG_OBJECT}
    */
    public NetSDKLib.NET_MSG_OBJECT stuObject = new NetSDKLib.NET_MSG_OBJECT();
    /**
     * 车身信息,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_MSG_OBJECT}
    */
    public NetSDKLib.NET_MSG_OBJECT stuVehicle = new NetSDKLib.NET_MSG_OBJECT();
    /**
     * 交通车辆信息,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO}
    */
    public NetSDKLib.DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stuTrafficCar = new NetSDKLib.DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO();
    /**
     * 公共信息,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.EVENT_COMM_INFO}
    */
    public NetSDKLib.EVENT_COMM_INFO stuCommInfo = new NetSDKLib.EVENT_COMM_INFO();
    /**
     * 事件对应文件信息,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_EVENT_FILE_INFO}
    */
    public NetSDKLib.NET_EVENT_FILE_INFO stuFileInfo = new NetSDKLib.NET_EVENT_FILE_INFO();
    /**
     * 抓图标志(按位),具体见NET_RESERVED_COMMON
    */
    public int              dwSnapFlagMask;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public DEV_EVENT_TRAFFIC_CROSSING_SPEEDY_INFO() {
    }
}

