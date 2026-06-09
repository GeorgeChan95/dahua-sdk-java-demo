package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 访客授权访问区域信息
*/
public class NET_VISITOR_AREA_ID_LIST_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 访客区域ID
    */
    public byte[]           szAreaID = new byte[64];
    /**
     * 区域名称
    */
    public byte[]           szAreaName = new byte[128];
    /**
     * 访问区域扩展字段，需访客业务支持超长的访客信息
    */
    public byte[]           szAreaNameEx = new byte[256];
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[768];

    public NET_VISITOR_AREA_ID_LIST_INFO() {
    }
}

