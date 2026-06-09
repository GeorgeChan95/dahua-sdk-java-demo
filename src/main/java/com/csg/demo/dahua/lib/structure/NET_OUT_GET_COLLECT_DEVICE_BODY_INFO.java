package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_GetCollectDeviceBodyInfo 接口出参
*/
public class NET_OUT_GET_COLLECT_DEVICE_BODY_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 挂机信息个数
    */
    public int              nBodyCount;
    /**
     * 挂机信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_COLLECT_DEVICE_BODY_INFO}
    */
    public NET_COLLECT_DEVICE_BODY_INFO[] stuBodyInfo = new NET_COLLECT_DEVICE_BODY_INFO[4];

    public NET_OUT_GET_COLLECT_DEVICE_BODY_INFO() {
        this.dwSize = this.size();
        for(int i = 0; i < stuBodyInfo.length; i++){
            stuBodyInfo[i] = new NET_COLLECT_DEVICE_BODY_INFO();
        }
    }
}

