package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 访客凭条打印字段配置
*/
public class NET_VISITOR_RECEIPT_PRINT_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 身份证号是否需要打印
    */
    public int              bCitizenIDNo;
    /**
     * 来访结束时间是否需要打印
    */
    public int              bEndTime;
    /**
     * 被访人姓名是否打印
    */
    public int              bIntervieweeName;
    /**
     * 访客名族信息是否打印
    */
    public int              bMingZu;
    /**
     * 访客姓名是否打印
    */
    public int              bName;
    /**
     * 访客联系电话是否打印
    */
    public int              bPhoneNo;
    /**
     * 访客照片是否打印
    */
    public int              bPicture;
    /**
     * 访客二维码是否打印
    */
    public int              bQRCode;
    /**
     * 来访事由是否打印
    */
    public int              bReason;
    /**
     * 访客性别是否打印
    */
    public int              bSex;
    /**
     * 访客来访开始时间是否打印
    */
    public int              bStartTime;
    /**
     * 访客卡号是否打印
    */
    public int              bVisitorCarNo;
    /**
     * 访客凭条打印字段配置扩展结构体, 内存由用户申请释放,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISITOR_RECEIPT_PRINT_INFO_EX}
    */
    public Pointer          pstuReceiptPrintInfoEx;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[256-NetSDKLib.POINTERSIZE];

    public NET_VISITOR_RECEIPT_PRINT_INFO() {
    }
}

