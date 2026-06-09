package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_RemoveMultiAccessPasswordComm接口输出参数
*/
public class NET_OUT_REMOVE_MULTI_ACCESS_PASSWORD_COMM extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;

    public NET_OUT_REMOVE_MULTI_ACCESS_PASSWORD_COMM() {
        this.dwSize = this.size();
    }
}

