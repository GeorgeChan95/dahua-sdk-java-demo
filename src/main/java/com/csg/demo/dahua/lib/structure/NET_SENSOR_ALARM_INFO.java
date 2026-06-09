package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 报警类型的附带信息
*/
public class NET_SENSOR_ALARM_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 报警上限阈值
    */
    public double           dbUpperThreshold;
    /**
     * 报警下限阈值
    */
    public double           dbLowerThreshold;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_SENSOR_ALARM_INFO() {
    }
}

