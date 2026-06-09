package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 来访事由
*/
public class NET_VISITOR_VISIT_REASON_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 来访事由
    */
    public byte[]           szReason = new byte[256];
    /**
     * 来访事由序号, -1:未知
    */
    public int              nIndex;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[124];

    public NET_VISITOR_VISIT_REASON_INFO() {
    }
}

