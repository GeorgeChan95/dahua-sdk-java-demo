package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 访客查询扩展信息 TransMethod=7时有效
*/
public class NET_VISITOR_CONDITION_EXTEND_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 访客姓名扩展字段
    */
    public byte[]           szVisitorNameEx = new byte[512];
    /**
     * 访客证件号码扩展字段
    */
    public byte[]           szCertificateNumberEx = new byte[256];
    /**
     * 访客手机号扩展字段
    */
    public byte[]           szPhoneNoEx = new byte[64];
    /**
     * 被访人姓名扩展字段
    */
    public byte[]           szIntervieweeNameEx = new byte[512];
    /**
     * 扩展区域名称模糊搜速
    */
    public byte[]           szAreaNameEx = new byte[256];
    /**
     * 访客邮箱
    */
    public byte[]           szEmail = new byte[256];
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[512];

    public NET_VISITOR_CONDITION_EXTEND_INFO() {
    }
}

