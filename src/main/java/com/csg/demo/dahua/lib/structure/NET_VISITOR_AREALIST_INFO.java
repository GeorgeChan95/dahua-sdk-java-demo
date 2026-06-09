package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 访客区域
*/
public class NET_VISITOR_AREALIST_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 区域名
    */
    public byte[]           szAreaName = new byte[128];
    /**
     * 区域ID
    */
    public byte[]           szAreaID = new byte[64];
    /**
     * nIsDefault字段是否生效
    */
    public int              bIsDefault;
    /**
     * 是否是默认区域1：默认区域，0：非默认区域。
    */
    public int              nIsDefault;
    /**
     * 区域名称扩展字段字符串长度（等价于strlen），最大值为255
    */
    public int              nAreaNameExLen;
    /**
     * 区域名称扩展字段，由用户申请和释放内存
    */
    public Pointer          szAreaNameEx;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[252-NetSDKLib.POINTERSIZE];

    public NET_VISITOR_AREALIST_INFO() {
    }
}

