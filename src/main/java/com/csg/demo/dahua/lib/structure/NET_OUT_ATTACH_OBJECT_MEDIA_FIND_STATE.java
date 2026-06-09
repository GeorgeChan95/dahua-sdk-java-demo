package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_AttachObjectMediaFindState 输出参数
*/
public class NET_OUT_ATTACH_OBJECT_MEDIA_FIND_STATE extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;

    public NET_OUT_ATTACH_OBJECT_MEDIA_FIND_STATE() {
        this.dwSize = this.size();
    }
}

