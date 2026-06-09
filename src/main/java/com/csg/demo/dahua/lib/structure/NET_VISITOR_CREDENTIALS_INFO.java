package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 证件
*/
public class NET_VISITOR_CREDENTIALS_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 证件编号
    */
    public byte[]           szCertificateNumber = new byte[64];
    /**
     * 证件类型
    */
    public byte[]           szCertificateType = new byte[64];
    /**
     * 证件签发单位
    */
    public byte[]           szIssuingAuthority = new byte[128];
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[512];

    public NET_VISITOR_CREDENTIALS_INFO() {
    }
}

