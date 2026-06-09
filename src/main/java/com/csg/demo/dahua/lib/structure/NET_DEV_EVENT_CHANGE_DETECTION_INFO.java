package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 事件类型 EVENT_IVS_CHANGE_DETECTION (差异检测事件) 对应的数据块描述信息
*/
public class NET_DEV_EVENT_CHANGE_DETECTION_INFO extends NetSDKLib.SdkStructure
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
     * 时间戳(单位是毫秒)
    */
    public double           dbPTS;
    /**
     * 事件发生的时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME_EX}
    */
    public NetSDKLib.NET_TIME_EX stuUTC = new NetSDKLib.NET_TIME_EX();
    /**
     * 事件时间毫秒数
    */
    public int              nUTCMS;
    /**
     * 事件组ID，同一物体抓拍过程内GroupID相同
    */
    public int              nGroupID;
    /**
     * 一个事件组内的抓拍张数
    */
    public int              nCountInGroup;
    /**
     * 一个事件组内的抓拍序号, 从1开始
    */
    public int              nIndexInGroup;
    /**
     * 事件ID
    */
    public int              nEventID;
    /**
     * 检测到的多个物体信息,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_MSG_OBJECT}
    */
    public Pointer          pstObjects;
    /**
     * 检测到的多个物体个数
    */
    public int              nObjectNum;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1020];

    public NET_DEV_EVENT_CHANGE_DETECTION_INFO() {
    }
}

