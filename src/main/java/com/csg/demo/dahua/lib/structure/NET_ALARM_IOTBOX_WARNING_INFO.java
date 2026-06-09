package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 事件类型 DH_ALARM_IOTBOX_WARNING (传感器报警事件)对应的数据块描述信息
*/
public class NET_ALARM_IOTBOX_WARNING_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 通道号
    */
    public int              nChannelID;
    /**
     * 事件动作,0表示脉冲事件,1表示事件开始,2表示事件结束;
    */
    public int              nAction;
    /**
     * 事件公共扩展字段结构体,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_EVENT_INFO_EXTEND}
    */
    public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND();
    /**
     * 事件发生的时间,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_TIME_EX2}
    */
    public NET_TIME_EX2     stuUTC = new NET_TIME_EX2();
    /**
     * 报警的传感器名称
    */
    public byte[]           szDeviceName = new byte[128];
    /**
     * 报警的传感器通道名称
    */
    public byte[]           szChannelName = new byte[128];
    /**
     * 报警的点位名称
    */
    public byte[]           szTagName = new byte[128];
    /**
     * 报警名称
    */
    public byte[]           szAlarmName = new byte[256];
    /**
     * 第三方传感器检测属性点名称的描述
    */
    public byte[]           szDescribe = new byte[128];
    /**
     * 第三方传感器属性点名称的值
    */
    public byte[]           szValue = new byte[128];
    /**
     * 第三方传感器属性点名值的单位
    */
    public byte[]           szUnit = new byte[32];
    /**
     * 传感器的报警映射名称，鸿鹄设备对接了各式传感器的报警名称对外都是IotboxWarning，但这些事件之间如果要做进一步区分究竟是哪个点位，可使用此字段，此字段值命名：IotboxWarning_（传感器名称+传感器通道+传感器点位的md5值）
    */
    public byte[]           szVirtualCode = new byte[128];
    /**
     * 第三方传感器属性点名称的数值类型；0:"无效",1:"布尔型", 2:"浮点类型",3:"整型",4:"字符型
    */
    public int              nValueType;
    /**
     * 预留字段
    */
    public byte[]           szReserved = new byte[1020];

    public NET_ALARM_IOTBOX_WARNING_INFO() {
    }
}

