package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_GetStoragePortInfo 入参
*/
public class NET_IN_GET_PORTINFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_IN_GET_PORTINFO() {
        this.dwSize = this.size();
    }
}

