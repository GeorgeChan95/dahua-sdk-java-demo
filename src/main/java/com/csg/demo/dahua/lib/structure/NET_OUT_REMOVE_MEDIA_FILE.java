package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_RemoveMediaFile接口输出参数
*/
public class NET_OUT_REMOVE_MEDIA_FILE extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;

    public NET_OUT_REMOVE_MEDIA_FILE() {
        this.dwSize = this.size();
    }
}

