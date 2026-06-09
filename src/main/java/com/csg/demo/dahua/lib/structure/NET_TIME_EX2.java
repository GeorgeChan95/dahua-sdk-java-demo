package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 时间信息
*/
public class NET_TIME_EX2 extends NetSDKLib.SdkStructure
{
    /**
     * 年
    */
    public int              dwYear;
    /**
     * 月
    */
    public int              dwMonth;
    /**
     * 日
    */
    public int              dwDay;
    /**
     * 时
    */
    public int              dwHour;
    /**
     * 分
    */
    public int              dwMinute;
    /**
     * 秒
    */
    public int              dwSecond;
    /**
     * 毫秒
    */
    public int              dwMillisecond;
    /**
     * 预留字段
    */
    public int[]            dwReserved = new int[1];
    /**
     * utc时间(获取时0表示无效，非0有效   下发无效)
    */
    public NetSDKLib.LLong  dwUTC;

    public NET_TIME_EX2() {
    }
}

