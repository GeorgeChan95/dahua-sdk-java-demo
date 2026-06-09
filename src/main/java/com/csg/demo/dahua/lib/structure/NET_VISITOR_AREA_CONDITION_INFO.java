package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 通过被访人查询访客区域，TransMethod=8时有效
*/
public class NET_VISITOR_AREA_CONDITION_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 被访人id
    */
    public byte[]           szIntervieweeID = new byte[64];
    /**
     * 区域名称模糊搜索
    */
    public byte[]           szAreaName = new byte[128];
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[256];

    public NET_VISITOR_AREA_CONDITION_INFO() {
    }
}

