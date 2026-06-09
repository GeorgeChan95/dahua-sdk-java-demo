package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 通道下所有传感器的信息
*/
public class NET_IOTBOX_BY_COND_CHANNEL_DEVICE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 传感器名称
    */
    public byte[]           szDeviceName = new byte[64];
    /**
     * 设备状态点名称
    */
    public byte[]           szDeviceStatusName = new byte[128];
    /**
     * 设备状态点的值
    */
    public double           dbDeviceStatusValue;
    /**
     * 设备控制点名称
    */
    public byte[]           szDeviceControlName = new byte[128];
    /**
     * 设备控制点的值
    */
    public double           dbDeviceControlValue;
    /**
     * 传感器所拥有的检测属性,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_IOTBOX_BY_COND_TAGS_INFO}
    */
    public NET_IOTBOX_BY_COND_TAGS_INFO[] stuTags = new NET_IOTBOX_BY_COND_TAGS_INFO[32];
    /**
     * 传感器所拥有的检测属性个数
    */
    public int              nTagsNum;
    /**
     * 保留字节
    */
    public byte[]           szResvered = new byte[68];

    public NET_IOTBOX_BY_COND_CHANNEL_DEVICE_INFO() {
        for(int i = 0; i < stuTags.length; i++){
            stuTags[i] = new NET_IOTBOX_BY_COND_TAGS_INFO();
        }
    }
}

