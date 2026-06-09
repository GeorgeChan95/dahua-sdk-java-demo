package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 设备相关信息
*/
public class NET_SENSOR_DEVICE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 预置点名称
    */
    public byte[]           szPresetName = new byte[64];
    /**
     * 预置点ID
    */
    public int              nPresetID;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1020];

    public NET_SENSOR_DEVICE_INFO() {
    }
}

