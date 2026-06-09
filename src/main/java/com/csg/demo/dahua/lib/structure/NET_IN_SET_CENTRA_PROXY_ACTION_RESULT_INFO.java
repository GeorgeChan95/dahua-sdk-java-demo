package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_SetCentraProxyActionResult 接口入参
*/
public class NET_IN_SET_CENTRA_PROXY_ACTION_RESULT_INFO extends NetSDKLib.SdkStructure
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
     * 错误码,0:开锁成功；1:开锁失败,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.EM_CENTRA_PROXY_CODE}
    */
    public int              emErrorCode;
    /**
     * 请求流地址
    */
    public byte[]           szURL = new byte[1024];
    /**
     * 访客信息，Action为访客机数据传输时有效,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_CENTRA_PROXY_DETAIL_INFO}
    */
    public NET_CENTRA_PROXY_DETAIL_INFO stDetailInfo = new NET_CENTRA_PROXY_DETAIL_INFO();

    public NET_IN_SET_CENTRA_PROXY_ACTION_RESULT_INFO() {
        this.dwSize = this.size();
    }
}

