package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 被访者扩展信息，需访客业务支持超长的访客信息
*/
public class NET_NEW_INTERVIEWEE_EXTEND_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 被访者姓名扩展字段
    */
    public byte[]           szNameEx = new byte[512];
    /**
     * 被访者电话号码扩展字段
    */
    public byte[]           szPhoneEx = new byte[64];
    /**
     * 被访者证件编号扩展字段
    */
    public byte[]           szCertificateNumberEx = new byte[256];
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[512];

    public NET_NEW_INTERVIEWEE_EXTEND_INFO() {
    }
}

