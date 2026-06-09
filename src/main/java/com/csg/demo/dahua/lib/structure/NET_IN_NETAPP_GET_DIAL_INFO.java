package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * EM_PRC_NETAPP_TYPE_GET_DIAL_INFO 入参
*/
public class NET_IN_NETAPP_GET_DIAL_INFO extends NetSDKLib.SdkStructure
{
    public int              dwSize;
    /**
     * 网卡名称,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.EM_DIAL_INTERFACE}
    */
    public int              emName;

    public NET_IN_NETAPP_GET_DIAL_INFO() {
        this.dwSize = this.size();
    }
}

