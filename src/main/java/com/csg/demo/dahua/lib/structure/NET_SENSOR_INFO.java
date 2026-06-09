package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 传感器数据信息
*/
public class NET_SENSOR_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 传感器类型
    */
    public byte[]           szSensorMethod = new byte[32];
    /**
     * 传感器数值单位
    */
    public byte[]           szUnit = new byte[32];
    /**
     * 传感器数值
    */
    public double           dbSensorValue;
    /**
     * 报警类型的附带信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_SENSOR_ALARM_INFO}
    */
    public NET_SENSOR_ALARM_INFO stuAlarmInfo = new NET_SENSOR_ALARM_INFO();
    /**
     * 0:正常,用于定时上报,1:低报警,2:高报警,3:超量程,4:传感器故障,5:系统故障
    */
    public int              nAlarmType;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1020];

    public NET_SENSOR_INFO() {
    }
}

