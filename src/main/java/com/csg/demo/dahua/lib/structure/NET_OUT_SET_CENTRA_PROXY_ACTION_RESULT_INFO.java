package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_SetCentraProxyActionResult 接口出参
*/
public class NET_OUT_SET_CENTRA_PROXY_ACTION_RESULT_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 代理功能,0:代理开锁功能；1:代理视频预览功能,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.EM_CENTRA_PROXY_ACTION}
    */
    public int              emAction;
    /**
     * 事务ID,由设备产生唯一ID
    */
    public byte[]           szTransID = new byte[36];
    /**
     * 返回码,0:成功；1:失败,对应Action为远程开门才有效,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.EM_CENTRA_PROXY_CODE}
    */
    public int              emRet1;
    /**
     * 返回码,0:成功；1:失败,对应Action预览地址请求才有效,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.EM_CENTRA_PROXY_CODE}
    */
    public int              emRet2;

    public NET_OUT_SET_CENTRA_PROXY_ACTION_RESULT_INFO() {
        this.dwSize = this.size();
    }
}

