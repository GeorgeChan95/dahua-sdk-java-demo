package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 回调的槽位信息
*/
public class NET_COLLECT_DEVICE_MANAGER_NOTIFY_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 槽位信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_COLLECT_DEVICE_SLOT_INFO}
    */
    public NET_COLLECT_DEVICE_SLOT_INFO stuSlotInfo = new NET_COLLECT_DEVICE_SLOT_INFO();
    /**
     * 保留字节
    */
    public byte[]           szResvered = new byte[1024];

    public NET_COLLECT_DEVICE_MANAGER_NOTIFY_INFO() {
    }
}

