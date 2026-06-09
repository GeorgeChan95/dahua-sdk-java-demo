package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 来访事由
*/
public class NET_VISITOR_DEFAULT_AREAS_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 默认访客区域ID
    */
    public byte[]           szAreaID = new byte[64];
    /**
     * 默认访客区域名称
    */
    public byte[]           szAreaName = new byte[64];
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[128];

    public NET_VISITOR_DEFAULT_AREAS_INFO() {
    }
}

