package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 袖章检测结果
*/
public class NET_ARMBAND_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 是否戴袖章, 0-未知 1-未戴袖章 2-戴了袖章
    */
    public int              nHasArmband;
    /**
     * 袖章检测结果 , 0-合规 1-不合规 2-未知
    */
    public int              nHasLegalArmband;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[24];

    public NET_ARMBAND_INFO() {
    }
}

