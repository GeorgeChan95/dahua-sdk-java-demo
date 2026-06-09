package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 大致日出/日落时间
*/
public class NET_SUN_TIME extends NetSDKLib.SdkStructure
{
    /**
     * 时
    */
    public int              nHour;
    /**
     * 分
    */
    public int              nMinute;
    /**
     * 秒
    */
    public int              nSecond;

    public NET_SUN_TIME() {
    }
}

