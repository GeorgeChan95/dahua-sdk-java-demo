package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * DEV_EVENT_MANNUM_DETECTION_INFO字段扩展结构体
*/
public class NET_EVENT_MANNUM_DETECTION_INFO_EX extends NetSDKLib.SdkStructure
{
    /**
     * 多通道关联数组
    */
    public int[]            nRelatedChannels = new int[256];
    /**
     * 多通道关联数组有效元素个数
    */
    public int              nRelatedChannelsNum;
    /**
     * 预留字节
    */
    public byte[]           szReversed = new byte[4092];

    public NET_EVENT_MANNUM_DETECTION_INFO_EX() {
    }
}

