package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * NET_IOTBOX_COMM_DEVICES 过滤器数组信息
*/
public class NET_IOTBOX_COMM_BY_COND_DEVICES extends NetSDKLib.SdkStructure
{
    /**
     * 传感器名称
    */
    public byte[]           szDeviceName = new byte[64];
    /**
     * 传感器点位数组，表征某一个传感器的全部测点,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_IOTBOX_COMM_BY_COND_TAGS}
    */
    public NET_IOTBOX_COMM_BY_COND_TAGS[] stuTags = new NET_IOTBOX_COMM_BY_COND_TAGS[32];
    /**
     * 传感器点位数组数量
    */
    public int              nTagsNum;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[196];

    public NET_IOTBOX_COMM_BY_COND_DEVICES() {
        for(int i = 0; i < stuTags.length; i++){
            stuTags[i] = new NET_IOTBOX_COMM_BY_COND_TAGS();
        }
    }
}

