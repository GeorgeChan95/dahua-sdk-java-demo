package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 头套相关属性状态信息
*/
public class NET_HOOD_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 头套检测结果 , 0:合规，1:不合规 ，2:未知
    */
    public int              nHasLegalHood;
    /**
     * 是否穿头套 , 0-未知  1-未穿头套 2-穿了头套
    */
    public int              nHasHood;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[24];

    public NET_HOOD_INFO() {
    }
}

