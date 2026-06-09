package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 接口 CLIENT_AttachCollectDevice 的输入参数
*/
public class NET_IN_ATTACH_COLLECT_DEVICE_MANAGER extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 回调函数,通知槽位信息,参见回调函数定义 {@link com.csg.demo.dahua.lib.NetSDKLib.fNotifyCollectDeviceManager}
    */
    public NetSDKLib.fNotifyCollectDeviceManager cbNotify;
    /**
     * 用户自定义参数
    */
    public Pointer          dwUser;
    /**
     * 槽位状态
    */
    public int[]            nState = new int[16];
    /**
     * 槽位状态个数
    */
    public int              nStateCount;

    public NET_IN_ATTACH_COLLECT_DEVICE_MANAGER() {
        this.dwSize = this.size();
    }
}

