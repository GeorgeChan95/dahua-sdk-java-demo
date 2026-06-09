package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_GetDevCaps 接口 NET_STORAGE_CAPS命令入参
*/
public class NET_IN_STORAGE_CAPS extends NetSDKLib.SdkStructure
{
    public int              dwSize;

    public NET_IN_STORAGE_CAPS() {
        this.dwSize = this.size();
    }
}

