package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 事件类型 EVENT_IVS_CONVEYOR_NTH_TO_STH_DETECTION (传送带载体从无到有检测报警事件)对应的数据块描述信息
*/
public class NET_DEV_EVENT_CONVEYOR_NTH_TO_STH_DETECTION_INFO extends NetSDKLib.SdkStructure
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
     * 扩展协议字段,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_EVENT_INFO_EXTEND}
    */
    public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND();
    /**
     * 事件名称
    */
    public byte[]           szName = new byte[128];
    /**
     * 智能事件所属大类
    */
    public byte[]           szClass = new byte[16];
    /**
     * 事件ID
    */
    public int              nEventID;
    /**
     * 事件发生的时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME_EX}
    */
    public NetSDKLib.NET_TIME_EX stuUTC = new NetSDKLib.NET_TIME_EX();
    /**
     * 相对事件时间戳,(单位是毫秒)
    */
    public double           dbPTS;
    /**
     * 智能事件规则编号
    */
    public int              nRuleID;
    /**
     * 检测的动物个数
    */
    public int              nObjectCount;
    /**
     * 检测的动物信息,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_MSG_OBJECT}
    */
    public NetSDKLib.NET_MSG_OBJECT[] stuObjects = new NetSDKLib.NET_MSG_OBJECT[32];
    /**
     * 全景广角图,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_OBJECT_IMAGE_INFO}
    */
    public NET_OBJECT_IMAGE_INFO stuSceneImage = new NET_OBJECT_IMAGE_INFO();
    /**
     * 检测区,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_POINT}
    */
    public NetSDKLib.NET_POINT[] stuDetectRegion = new NetSDKLib.NET_POINT[20];
    /**
     * 检测区个数
    */
    public int              nDetectRegionCount;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1020];

    public NET_DEV_EVENT_CONVEYOR_NTH_TO_STH_DETECTION_INFO() {
        for(int i = 0; i < stuObjects.length; i++){
            stuObjects[i] = new NetSDKLib.NET_MSG_OBJECT();
        }
        for(int i = 0; i < stuDetectRegion.length; i++){
            stuDetectRegion[i] = new NetSDKLib.NET_POINT();
        }
    }
}

