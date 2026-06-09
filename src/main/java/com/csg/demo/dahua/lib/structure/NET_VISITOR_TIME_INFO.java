package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 时间相关信息
*/
public class NET_VISITOR_TIME_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 预约来访时间, 废弃, 使用nScheduleInTimeRealUTC
    */
    public byte[]           szScheduleInTime = new byte[64];
    /**
     * 实际来访时间, 废弃, 使用nRealInTimeRealUTC
    */
    public byte[]           szRealInTime = new byte[64];
    /**
     * 预约离访时间, 废弃, 使用nScheduleOutTimeRealUTC
    */
    public byte[]           szScheduleOutTime = new byte[64];
    /**
     * 实际离访时间, 废弃, 使用nRealOutTimeRealUTC
    */
    public byte[]           szRealOutTime = new byte[64];
    /**
     * 预约访问时长
    */
    public int              nDuration;
    /**
     * 预约来访时间，UTC时间
    */
    public int              nScheduleInTimeRealUTC;
    /**
     * 实际来访时间，UTC时间
    */
    public int              nRealInTimeRealUTC;
    /**
     * 预约离访时间，UTC时间
    */
    public int              nScheduleOutTimeRealUTC;
    /**
     * 实际离访时间，UTC时间
    */
    public int              nRealOutTimeRealUTC;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[492];

    public NET_VISITOR_TIME_INFO() {
    }
}

