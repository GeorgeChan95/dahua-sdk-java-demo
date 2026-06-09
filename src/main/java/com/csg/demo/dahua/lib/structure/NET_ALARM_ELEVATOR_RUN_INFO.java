package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 事件类型 DH_ALARM_ELEVATOR_RUN_INFO (上报电梯运行数据事件) 对应的数据块描述信息
*/
public class NET_ALARM_ELEVATOR_RUN_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 通道号
    */
    public int              nChannelID;
    /**
     * 0:脉冲 1:开始 2:停止
    */
    public int              nAction;
    /**
     * 事件公共扩展字段结构体,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_EVENT_INFO_EXTEND}
    */
    public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND();
    /**
     * 事件发生的时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME_EX}
    */
    public NetSDKLib.NET_TIME_EX stuUTC = new NetSDKLib.NET_TIME_EX();
    /**
     * 电梯运行时长，只要速度不为0就是运行，单位秒
    */
    public int              nRunTimeTotal;
    /**
     * 相对事件时间戳,单位毫秒
    */
    public double           dbPTS;
    /**
     * 事件名称
    */
    public byte[]           szName = new byte[128];
    /**
     * 事件触发的预置点号，从1开始没有该字段，表示预置点未知
    */
    public int              nPresetID;
    /**
     * 事件ID
    */
    public int              nEventID;
    /**
     * 电梯开始运行的楼层
    */
    public byte[]           szBeginFloor = new byte[32];
    /**
     * 电梯停止运行的楼层
    */
    public byte[]           szEndFloor = new byte[32];
    /**
     * 电梯运行开始时间，带时区偏差的UTC时间，单位秒,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuBeginTime = new NetSDKLib.NET_TIME();
    /**
     * 电梯运行结束时间，带时区偏差的UTC时间，单位秒,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuEndTime = new NetSDKLib.NET_TIME();
    /**
     * 电梯运行总里程数,从电梯运行开始为准，单位cm
    */
    public int              nRunMileageTotal;
    /**
     * 进入总人数
    */
    public int              nEnteredNumber;
    /**
     * 离开总人数
    */
    public int              nExitedNumber;
    /**
     * 电梯运行方向改变的次数
    */
    public int              nDirectionChangeNum;
    /**
     * 电梯开门次数
    */
    public int              nOpenDoorNum;
    /**
     * 电梯运行次数，从运行到停止算一次运行
    */
    public int              nMoveNum;
    /**
     * 电梯单次运行的距离，单次运行上报时使用
    */
    public double           dbDistance;
    /**
     * 电梯运行的层数变化，电梯统计一段时间后上报使用，1-10楼变化9，统计此数据
    */
    public int              nFloorChangeNum;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1020];

    public NET_ALARM_ELEVATOR_RUN_INFO() {
    }
}

