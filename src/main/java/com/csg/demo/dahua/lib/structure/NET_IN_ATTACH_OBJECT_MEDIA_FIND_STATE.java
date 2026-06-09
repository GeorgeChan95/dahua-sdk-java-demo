package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * CLIENT_AttachObjectMediaFindState 输入参数
*/
public class NET_IN_ATTACH_OBJECT_MEDIA_FIND_STATE extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 字节对齐
    */
    public byte[]           szReserved = new byte[4];
    /**
     * 回调函数,参见回调函数定义 {@link com.csg.demo.dahua.lib.NetSDKLib.fNotifyObjectFindState}
    */
    public NetSDKLib.fNotifyObjectFindState cbNotifyObjectFindState;
    /**
     * 用户信息
    */
    public Pointer          dwUser;

    public NET_IN_ATTACH_OBJECT_MEDIA_FIND_STATE() {
        this.dwSize = this.size();
    }
}

