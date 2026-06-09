package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_AttachSniffer 接口出参
*/
public class NET_OUT_ATTACH_SNIFFER extends NetSDKLib.SdkStructure
{
    public int              dwSize;

    public NET_OUT_ATTACH_SNIFFER() {
        this.dwSize = this.size();
    }
}

