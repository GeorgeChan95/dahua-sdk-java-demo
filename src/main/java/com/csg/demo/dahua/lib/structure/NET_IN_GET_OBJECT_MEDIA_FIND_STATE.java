package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_GetObjectMediaFindState 接口输入参数
*/
public class NET_IN_GET_OBJECT_MEDIA_FIND_STATE extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;

    public NET_IN_GET_OBJECT_MEDIA_FIND_STATE() {
        this.dwSize = this.size();
    }
}

