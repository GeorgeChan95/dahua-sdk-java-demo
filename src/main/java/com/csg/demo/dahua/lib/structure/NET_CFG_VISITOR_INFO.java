package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * NET_EM_CFG_VISITOR 访客功能配置
*/
public class NET_CFG_VISITOR_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 访客机工作默认 1：脱机模式，0：联网、配套平台，-1:未知
    */
    public int              nWorkMode;
    /**
     * 主界面展示的功能配置列表个数
    */
    public int              nMainInterfaceListCount;
    /**
     * 主界面展示的功能配置列表, 0:未知, 1:访客登记按钮, 2:访客签离按钮, 3:访客记录按钮, 4:人证核验按钮, 5:设置按钮, 6:预约登记按钮, 7:现场登记按钮
    */
    public int[]            nMainInterfaceList = new int[128];
    /**
     * 客户端logo,图片URL路径
    */
    public byte[]           szClientLogoURL = new byte[512];
    /**
     * 客户端名字
    */
    public byte[]           szClientName = new byte[64];
    /**
     * 随访人是否必填，1：必填 0：非必填 -1:未知
    */
    public int              nFollowRequire;
    /**
     * 被访人是否必填，1：必填 0：非必填 -1:未知
    */
    public int              nVisitedRequire;
    /**
     * 是否启用无证登记模式（启用无证登记，登记簿不需要进行人证核验）true：启用无证登记，false：不启用
    */
    public int              bUnlicensedRegEnable;
    /**
     * 是否需要发临时卡 true：下发临时卡，false：不下发临时卡
    */
    public int              bTemporaryCardEnable;
    /**
     * 是否开启打印凭条功能
    */
    public int              bPrintReceipt;
    /**
     * 是否需要抓拍人脸图
    */
    public int              bNeedVisitorPic;
    /**
     * 访客凭条打印字段配置,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISITOR_RECEIPT_PRINT_INFO}
    */
    public NET_VISITOR_RECEIPT_PRINT_INFO stuReceiptPrint = new NET_VISITOR_RECEIPT_PRINT_INFO();
    /**
     * 访客登记必填信息配置,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISITOR_REQUIRED_INFO}
    */
    public NET_VISITOR_REQUIRED_INFO stuRequiredInfo = new NET_VISITOR_REQUIRED_INFO();
    /**
     * 访客平台下发的访客信息扩展字段个数
    */
    public int              nCustomFieldNum;
    /**
     * 访客平台下发的访客信息扩展字段,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISITOR_CUSTOM_FIELD_INFO}
    */
    public NET_VISITOR_CUSTOM_FIELD_INFO[] stuCustomField = new NET_VISITOR_CUSTOM_FIELD_INFO[32];
    /**
     * 来访事由个数
    */
    public int              nVisitReasonNum;
    /**
     * 来访事由,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISITOR_VISIT_REASON_INFO}
    */
    public NET_VISITOR_VISIT_REASON_INFO[] stuVisitReason = new NET_VISITOR_VISIT_REASON_INFO[32];
    /**
     * 默认访客区域个数
    */
    public int              nDefaultAreasNum;
    /**
     * 默认访客区域,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISITOR_DEFAULT_AREAS_INFO}
    */
    public NET_VISITOR_DEFAULT_AREAS_INFO[] stuDefaultAreas = new NET_VISITOR_DEFAULT_AREAS_INFO[32];
    /**
     * 已经定义的访客字段信息有效个数
    */
    public int              nDefindVisitorInfoNum;
    /**
     * 已经定义的访客字段信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISITOR_DEFINED_VISITOR_INFO}
    */
    public NET_VISITOR_DEFINED_VISITOR_INFO[] stuDefindVisitorInfo = new NET_VISITOR_DEFINED_VISITOR_INFO[64];
    /**
     * 凭条抬头信息
    */
    public byte[]           szPrintHead = new byte[512];
    /**
     * 凭条结尾信息
    */
    public byte[]           szPrintTail = new byte[512];
    /**
     * 访客信息凭条打印顺序,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISITOR_PRINT_ORDER_INFO}
    */
    public NET_VISITOR_PRINT_ORDER_INFO[] stuPrintOrder = new NET_VISITOR_PRINT_ORDER_INFO[64];
    /**
     * 访客信息凭条打印顺序有效个数
    */
    public int              nPrintOrderNum;
    /**
     * 控制无用字段的下发：ReceiptPrint、RequiredInfo、CustomField.IsDefault、CustomField.IsPrint
    */
    public int              bSetInvalid;

    public NET_CFG_VISITOR_INFO() {
        this.dwSize = this.size();
        for(int i = 0; i < stuCustomField.length; i++){
            stuCustomField[i] = new NET_VISITOR_CUSTOM_FIELD_INFO();
        }
        for(int i = 0; i < stuVisitReason.length; i++){
            stuVisitReason[i] = new NET_VISITOR_VISIT_REASON_INFO();
        }
        for(int i = 0; i < stuDefaultAreas.length; i++){
            stuDefaultAreas[i] = new NET_VISITOR_DEFAULT_AREAS_INFO();
        }
        for(int i = 0; i < stuDefindVisitorInfo.length; i++){
            stuDefindVisitorInfo[i] = new NET_VISITOR_DEFINED_VISITOR_INFO();
        }
        for(int i = 0; i < stuPrintOrder.length; i++){
            stuPrintOrder[i] = new NET_VISITOR_PRINT_ORDER_INFO();
        }
    }
}

