package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 已经定义的访客字段信息
*/
public class NET_VISITOR_DEFINED_VISITOR_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 已经定义的访客信息字段的key值,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.NET_EM_DEFINED_VISITOR_KEY}
    */
    public int              emKey;
    /**
     * 此字段访客签入时，是否必填 ：必填，TRUE：非必填: FALSE
    */
    public int              bIsRequired;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[128];

    public NET_VISITOR_DEFINED_VISITOR_INFO() {
    }
}

