package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 目标类型信息
*/
public class NET_OBJECT_MEDIA_EVENT_OBJECT_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 目标类型 0 未知, 1 人, 2 车, 3 动物, 4 非机动车
    */
    public int              nObjectType;
    /**
     * 目标类型数量
    */
    public int              nTotalCount;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[256];

    public NET_OBJECT_MEDIA_EVENT_OBJECT_INFO() {
    }
}

