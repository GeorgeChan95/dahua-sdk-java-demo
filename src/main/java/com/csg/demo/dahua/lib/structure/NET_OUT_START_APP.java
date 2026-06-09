package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_StartApp 接口输出参数
*/
public class NET_OUT_START_APP extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_OUT_START_APP() {
        this.dwSize = this.size();
    }
}

