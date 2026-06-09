package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_GetCollectDeviceCaps 接口入参
*/
public class NET_IN_GET_COLLECT_DEVICE_CAPS extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_IN_GET_COLLECT_DEVICE_CAPS() {
        this.dwSize = this.size();
    }
}

