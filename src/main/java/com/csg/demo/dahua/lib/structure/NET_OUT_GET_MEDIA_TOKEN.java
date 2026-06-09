package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_GetMediaToken接口输出参数
*/
public class NET_OUT_GET_MEDIA_TOKEN extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 请求token的客户端IP
    */
    public byte[]           szToken = new byte[68];

    public NET_OUT_GET_MEDIA_TOKEN() {
        this.dwSize = this.size();
    }
}

