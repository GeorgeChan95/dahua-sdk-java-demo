package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 绝缘子检测结果
*/
public class NET_INSULATOR_DETECT_EX extends NetSDKLib.SdkStructure
{
    /**
     * 目标置信度，0~100，越大表示越高
    */
    public int              nConfidence;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1020];

    public NET_INSULATOR_DETECT_EX() {
    }
}

