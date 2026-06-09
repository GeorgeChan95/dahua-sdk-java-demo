package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * NET_IOTBOX_COMM_BY_COND_TAGS 传感器点位数组
*/
public class NET_IOTBOX_COMM_BY_COND_TAGS extends NetSDKLib.SdkStructure
{
    /**
     * 传感器点位
    */
    public byte[]           szTagName = new byte[64];
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[200];

    public NET_IOTBOX_COMM_BY_COND_TAGS() {
    }
}

