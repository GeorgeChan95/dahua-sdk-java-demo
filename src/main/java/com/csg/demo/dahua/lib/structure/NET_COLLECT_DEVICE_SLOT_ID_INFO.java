package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 槽位ID信息
*/
public class NET_COLLECT_DEVICE_SLOT_ID_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 槽位所在挂机号
    */
    public int              nSlotBodyIndex;
    /**
     * 槽位号
    */
    public int              nSlotIndex;
    /**
     * 保留字节
    */
    public byte[]           szResvered = new byte[128];

    public NET_COLLECT_DEVICE_SLOT_ID_INFO() {
    }
}

