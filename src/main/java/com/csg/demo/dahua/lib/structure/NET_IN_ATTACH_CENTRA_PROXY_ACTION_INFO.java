package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * CLIENT_AttachCentraProxyAction 接口入参
*/
public class NET_IN_ATTACH_CENTRA_PROXY_ACTION_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 代理功能,0:代理开锁功能；1:代理视频预览功能,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.EM_CENTRA_PROXY_ACTION}
    */
    public int              emFilter;
    /**
     * 回调函数,参见回调函数定义 {@link com.csg.demo.dahua.lib.NetSDKLib.fNotifyCentraProxyActiondata}
    */
    public NetSDKLib.fNotifyCentraProxyActiondata cbNotifyCentraProxyActiondata;
    /**
     * 用户自定义参数
    */
    public Pointer          dwUser;

    public NET_IN_ATTACH_CENTRA_PROXY_ACTION_INFO() {
        this.dwSize = this.size();
    }
}

