package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_GetCollectDeviceBodyInfo 接口入参
*/
public class NET_IN_GET_COLLECT_DEVICE_BODY_INFO extends NetSDKLib.SdkStructure
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
     * 挂机索引数组，存放挂机的逻辑序号
    */
    public int[]            nSlotBodyIndex = new int[4];

    public NET_IN_GET_COLLECT_DEVICE_BODY_INFO() {
        this.dwSize = this.size();
    }
}

