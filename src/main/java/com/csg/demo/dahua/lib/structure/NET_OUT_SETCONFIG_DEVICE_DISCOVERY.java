package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_SetConfigDeviceDiscovery 接口输出参数
*/
public class NET_OUT_SETCONFIG_DEVICE_DISCOVERY extends NetSDKLib.SdkStructure
{
    public int              dwSize;

    public NET_OUT_SETCONFIG_DEVICE_DISCOVERY() {
        this.dwSize = this.size();
    }
}

