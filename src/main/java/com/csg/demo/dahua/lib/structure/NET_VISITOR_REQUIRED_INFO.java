package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 访客登记必填信息配置
*/
public class NET_VISITOR_REQUIRED_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 身份证号是否必填
    */
    public int              bCitizenIDNo;
    /**
     * 签离时间是否必填
    */
    public int              bEndTime;
    /**
     * 被访人姓名是否必填
    */
    public int              bIntervieweeName;
    /**
     * 名族是否必填
    */
    public int              bMingZu;
    /**
     * 访客姓名是否必填
    */
    public int              bName;
    /**
     * 访客联系电话是否必填
    */
    public int              bPhoneNo;
    /**
     * 访客来访事由是否必填
    */
    public int              bReason;
    /**
     * 访客性别是否必填
    */
    public int              bSex;
    /**
     * 到访时间是否必填
    */
    public int              bStartTime;
    /**
     * 访客卡号是否必填
    */
    public int              bVisitorCarNo;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[128];

    public NET_VISITOR_REQUIRED_INFO() {
    }
}

