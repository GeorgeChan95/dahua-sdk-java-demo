package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_GetDevCaps 接口 NET_VIDEOIN_RAWFRAME_CAPS 命令出参
*/
public class NET_IN_VIDEOIN_RAWFRAME_CAPS extends NetSDKLib.SdkStructure
{
    public int              dwSize;

    public NET_IN_VIDEOIN_RAWFRAME_CAPS() {
        this.dwSize = this.size();
    }
}

