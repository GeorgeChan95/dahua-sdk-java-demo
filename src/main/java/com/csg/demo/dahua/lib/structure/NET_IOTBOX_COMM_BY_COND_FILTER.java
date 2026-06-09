package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * NET_IOTBOX_COMM_FILTER 过滤器数组信息
*/
public class NET_IOTBOX_COMM_BY_COND_FILTER extends NetSDKLib.SdkStructure
{
    /**
     * 传感器通道名称
    */
    public byte[]           szChannelName = new byte[64];
    /**
     * 传感器数组，表征某一个传感器通道下的全部传感器,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_IOTBOX_COMM_BY_COND_DEVICES}
    */
    public NET_IOTBOX_COMM_BY_COND_DEVICES[] stuDevices = new NET_IOTBOX_COMM_BY_COND_DEVICES[16];
    /**
     * 传感器数组数量
    */
    public int              nDevicesNum;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[196];

    public NET_IOTBOX_COMM_BY_COND_FILTER() {
        for(int i = 0; i < stuDevices.length; i++){
            stuDevices[i] = new NET_IOTBOX_COMM_BY_COND_DEVICES();
        }
    }
}

