package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 访客类型列表
*/
public class NET_VISITOR_TYPE_LIST_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 访客类型名
    */
    public byte[]           szTypeName = new byte[128];
    /**
     * 访客类型ID
    */
    public byte[]           szTypeID = new byte[64];
    /**
     * 访客类型名扩展字段字符串长度（等价于strlen），最大值为255
    */
    public int              nTypeNameExLen;
    /**
     * 访客类型名扩展字段，由用户申请和释放内存
    */
    public Pointer          szTypeNameEx;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[252-NetSDKLib.POINTERSIZE];

    public NET_VISITOR_TYPE_LIST_INFO() {
    }
}

