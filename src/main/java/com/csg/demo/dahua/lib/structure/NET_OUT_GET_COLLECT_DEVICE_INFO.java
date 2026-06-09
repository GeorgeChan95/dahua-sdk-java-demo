package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_GetCollectDeviceInfo 接口出参
*/
public class NET_OUT_GET_COLLECT_DEVICE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 挂机信息个数
    */
    public int              nSlotInfoCount;
    /**
     * 挂机信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_COLLECT_DEVICE_SLOT_INFO}
    */
    public NET_COLLECT_DEVICE_SLOT_INFO[] stuSlotInfo = new NET_COLLECT_DEVICE_SLOT_INFO[8];

    public NET_OUT_GET_COLLECT_DEVICE_INFO() {
        this.dwSize = this.size();
        for(int i = 0; i < stuSlotInfo.length; i++){
            stuSlotInfo[i] = new NET_COLLECT_DEVICE_SLOT_INFO();
        }
    }
}

