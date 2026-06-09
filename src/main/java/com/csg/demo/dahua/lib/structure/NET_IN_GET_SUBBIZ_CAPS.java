package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_GetSubBusinessCaps 接口输入参数
*/
public class NET_IN_GET_SUBBIZ_CAPS extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_IN_GET_SUBBIZ_CAPS() {
        this.dwSize = this.size();
    }
}

