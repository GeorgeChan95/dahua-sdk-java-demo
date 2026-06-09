package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * CLIENT_AttachAISProc 输入参数
*/
public class NET_IN_ATTACH_AIS_PROC extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 回调函数,参见回调函数定义 {@link com.csg.demo.dahua.lib.NetSDKLib.fNotifyAISProc}
    */
    public NetSDKLib.fNotifyAISProc cbNotify;
    /**
     * 用户信息
    */
    public Pointer          dwUser;

    public NET_IN_ATTACH_AIS_PROC() {
        this.dwSize = this.size();
    }
}

