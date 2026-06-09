package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 挂机信息
*/
public class NET_COLLECT_DEVICE_BODY_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 挂机是否存在
    */
    public int              bExist;
    /**
     * 挂机支持的槽位号数量
    */
    public int              nSlotNum;
    /**
     * 保留字节
    */
    public byte[]           szResvered = new byte[512];

    public NET_COLLECT_DEVICE_BODY_INFO() {
    }
}

