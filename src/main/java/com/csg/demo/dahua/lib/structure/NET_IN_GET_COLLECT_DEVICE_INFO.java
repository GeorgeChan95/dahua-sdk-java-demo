package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_GetCollectDeviceInfo 接口入参
*/
public class NET_IN_GET_COLLECT_DEVICE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 挂机索引个数
    */
    public int              nSlotBodyNum;
    /**
     * 槽位ID数组,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_COLLECT_DEVICE_SLOT_ID_INFO}
    */
    public NET_COLLECT_DEVICE_SLOT_ID_INFO[] stuSlotID = new NET_COLLECT_DEVICE_SLOT_ID_INFO[8];

    public NET_IN_GET_COLLECT_DEVICE_INFO() {
        this.dwSize = this.size();
        for(int i = 0; i < stuSlotID.length; i++){
            stuSlotID[i] = new NET_COLLECT_DEVICE_SLOT_ID_INFO();
        }
    }
}

