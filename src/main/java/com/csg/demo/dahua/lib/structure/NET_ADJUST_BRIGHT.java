package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 亮度调节参数
*/
public class NET_ADJUST_BRIGHT extends NetSDKLib.SdkStructure
{
    /**
     * 亮度值：0-15 为手动固定亮度；170 表示按时段调节
    */
    public int              nBrightnessValue;
    /**
     * 预留字段
    */
    public byte[]           szReserved = new byte[2044];

    public NET_ADJUST_BRIGHT() {
    }
}

