package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_AttachTrainTask 接口出参
*/
public class NET_OUT_ATTACH_CENTRA_PROXY_ACTION_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;

    public NET_OUT_ATTACH_CENTRA_PROXY_ACTION_INFO() {
        this.dwSize = this.size();
    }
}

