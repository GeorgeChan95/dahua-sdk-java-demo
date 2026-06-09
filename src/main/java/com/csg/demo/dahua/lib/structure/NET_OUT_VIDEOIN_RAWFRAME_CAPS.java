package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_GetDevCaps 接口 NET_VIDEOIN_RAWFRAME_CAPS 命令出参
*/
public class NET_OUT_VIDEOIN_RAWFRAME_CAPS extends NetSDKLib.SdkStructure
{
    public int              dwSize;
    /**
     * 参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_RAWFRAMETYPE_DATA}
    */
    public NET_RAWFRAMETYPE_DATA stuFrameData = new NET_RAWFRAMETYPE_DATA();

    public NET_OUT_VIDEOIN_RAWFRAME_CAPS() {
        this.dwSize = this.size();
    }
}

