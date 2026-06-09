package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_GetCollectDeviceBodyInfo 接口出参
*/
public class NET_OUT_GET_COLLECT_DEVICE_CAPS extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 支持的挂机数量
    */
    public int              nBodyNum;
    /**
     * 采集站类型，0：壁挂式 1：桌面式
    */
    public int              nType;

    public NET_OUT_GET_COLLECT_DEVICE_CAPS() {
        this.dwSize = this.size();
    }
}

