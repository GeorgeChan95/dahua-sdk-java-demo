package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_AttachIotboxCommByCond 接口出参
*/
public class NET_OUT_ATTACH_IOTBOX_COMM_BY_COND extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;

    public NET_OUT_ATTACH_IOTBOX_COMM_BY_COND() {
        this.dwSize = this.size();
    }
}

