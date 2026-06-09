package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 接口 CLIENT_AttachCollectDevice 的输出参数
*/
public class NET_OUT_ATTACH_COLLECT_DEVICE_MANAGER extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;

    public NET_OUT_ATTACH_COLLECT_DEVICE_MANAGER() {
        this.dwSize = this.size();
    }
}

