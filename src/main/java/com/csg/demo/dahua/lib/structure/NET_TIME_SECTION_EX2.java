package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 配置时间部分
*/
public class NET_TIME_SECTION_EX2 extends NetSDKLib.SdkStructure
{
    /**
     * 时
    */
    public int              nStartHour;
    /**
     * 分
    */
    public int              nStartMinute;
    /**
     * 秒
    */
    public int              nStartSecond;
    /**
     * 时
    */
    public int              nStopHour;
    /**
     * 分
    */
    public int              nStopMinute;
    /**
     * 秒
    */
    public int              nStopSecond;
    /**
     * 灯光方案名称
    */
    public byte[]           szLightingMode = new byte[16];

    public NET_TIME_SECTION_EX2() {
    }
}

