package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 呼吸器检测结果扩展数据
*/
public class NET_RESPIRATOR_DETECT_EX extends NetSDKLib.SdkStructure
{
    /**
     * 目标置信度，0~100，越大表示越高
    */
    public int              nConfidence;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1020];

    public NET_RESPIRATOR_DETECT_EX() {
    }
}

