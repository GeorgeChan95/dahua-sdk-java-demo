package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 访客温度信息
*/
public class NET_VISITOR_TEMPERATURE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 访客体温
    */
    public float            fCurrentTemperature;
    /**
     * 温度单位(0未知 1摄氏度 2华氏度 3开尔文)
    */
    public int              nTemperatureUnit;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[128];

    public NET_VISITOR_TEMPERATURE_INFO() {
    }
}

