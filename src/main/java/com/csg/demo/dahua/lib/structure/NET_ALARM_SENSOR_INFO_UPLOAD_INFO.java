package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 事件类型 DH_ALARM_SENSOR_INFO_UPLOAD (传感器信息推送平台事件)对应的数据块描述信息
*/
public class NET_ALARM_SENSOR_INFO_UPLOAD_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 通道号
    */
    public int              nChannelID;
    /**
     * 0:脉冲,1:开始, 2:停止
    */
    public int              nAction;
    /**
     * 扩展协议字段,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_EVENT_INFO_EXTEND}
    */
    public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND();
    /**
     * 事件发生的时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME_EX}
    */
    public NetSDKLib.NET_TIME_EX stuUTC = new NetSDKLib.NET_TIME_EX();
    /**
     * 传感器数据信息数组个数
    */
    public int              nSensorInfoNum;
    /**
     * 传感器数据信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_SENSOR_INFO}
    */
    public NET_SENSOR_INFO[] stuSensorInfo = new NET_SENSOR_INFO[10];
    /**
     * 设备相关信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_SENSOR_DEVICE_INFO}
    */
    public NET_SENSOR_DEVICE_INFO stuDeviceInfo = new NET_SENSOR_DEVICE_INFO();
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_ALARM_SENSOR_INFO_UPLOAD_INFO() {
        for(int i = 0; i < stuSensorInfo.length; i++){
            stuSensorInfo[i] = new NET_SENSOR_INFO();
        }
    }
}

