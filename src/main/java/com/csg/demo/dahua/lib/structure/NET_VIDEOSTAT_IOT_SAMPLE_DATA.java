package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 前端传感器采集数据实时上报信息
*/
public class NET_VIDEOSTAT_IOT_SAMPLE_DATA extends NetSDKLib.SdkStructure
{
    /**
     * 统计通道号
    */
    public int              nChannel;
    /**
     * 振动能量值，单位分贝
    */
    public float            fEnergyValue;
    /**
     * 温度值，单位摄氏度
    */
    public float            fTemperature;
    /**
     * 采集时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME_EX}
    */
    public NetSDKLib.NET_TIME_EX stuUTC = new NetSDKLib.NET_TIME_EX();
    /**
     * 加速度，单位m/s^2
    */
    public float            fAcceleration;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1020];

    public NET_VIDEOSTAT_IOT_SAMPLE_DATA() {
    }
}

