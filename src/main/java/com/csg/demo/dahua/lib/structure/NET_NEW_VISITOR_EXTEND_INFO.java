package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 访客扩展信息，需访客业务支持超长的访客信息
*/
public class NET_NEW_VISITOR_EXTEND_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 访客姓名扩展字段
    */
    public byte[]           szNameEx = new byte[512];
    /**
     * 访客住址扩展字段
    */
    public byte[]           szAddressEx = new byte[1024];
    /**
     * 来访单位扩展字段
    */
    public byte[]           szCompanyEx = new byte[512];
    /**
     * 访客电话号码扩展字段
    */
    public byte[]           szPhoneEx = new byte[64];
    /**
     * 来访车牌号扩展字段
    */
    public byte[]           szCarNumberEx = new byte[64];
    /**
     * 来访原因扩展字段
    */
    public byte[]           szReasonEx = new byte[4096];
    /**
     * 访客类型名称扩展字段
    */
    public byte[]           szVisitorTypeNameEx = new byte[256];
    /**
     * 访客证件编号扩展字段
    */
    public byte[]           szCertificateNumberEx = new byte[256];
    /**
     * 被访人姓名扩展字段
    */
    public byte[]           szIntervieweeNameEx = new byte[512];
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[512];

    public NET_NEW_VISITOR_EXTEND_INFO() {
    }
}

