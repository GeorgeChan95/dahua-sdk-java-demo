package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 数据传输细节，Action为访客机数据传输时有效
*/
public class NET_CENTRA_PROXY_DETAIL_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 来访者信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_NEW_VISITOR_INFO}
    */
    public NET_NEW_VISITOR_INFO stVisitor = new NET_NEW_VISITOR_INFO();
    /**
     * 被访者信息数组,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_NEW_INTERVIEWEE_INFO}
    */
    public NET_NEW_INTERVIEWEE_INFO[] stInterviewees = new NET_NEW_INTERVIEWEE_INFO[100];
    /**
     * 被访者信息数组有效个数
    */
    public int              nIntervieweesNum;
    /**
     * 访客区域有效个数
    */
    public int              nAreaListNum;
    /**
     * 访客区域,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISITOR_AREALIST_INFO}
    */
    public NET_VISITOR_AREALIST_INFO[] stAreaList = new NET_VISITOR_AREALIST_INFO[64];
    /**
     * 访客类型列表,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISITOR_TYPE_LIST_INFO}
    */
    public NET_VISITOR_TYPE_LIST_INFO[] stVistorTypeList = new NET_VISITOR_TYPE_LIST_INFO[32];
    /**
     * 访客类型列表有效个数
    */
    public int              nVistorTypeListNum;
    /**
     * 错误码 1 未知错误 2 成功 3 参数错误 4 访客信息已存在 5 被访者不存在 6 访客信息不存在 7 访客在禁止名单中
    */
    public int              nErrorCode;
    /**
     * 具体传输内容 0：客户端向服务端发送访客信息 1：客户端向服务端发送指定被访者信息 2：客户端向服务端发送访客区域, 3：客户端向服务端发送访客类型列表 4：客户端向服务端发送需要补充的信息 5：客户端向服务端发送通行二维码 6：客户端答复推送信息收到 7：客户端下发被访人的访客区域信息。8：客户端分页下发查询访客列表信息
    */
    public int              nTransMethod;
    /**
     * 访客记录列表，TransMethod =8有效,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISITOR_RECORD_LIST_INFO}
    */
    public NET_VISITOR_RECORD_LIST_INFO stuVisitorRecordList = new NET_VISITOR_RECORD_LIST_INFO();
    /**
     * 访客登记类型，-1:未知，0：普通登记，1：快速登记
    */
    public int              nRegisterType;
    /**
     * 搜索类型，-1:未知，0：普通搜索，1：模糊搜索。
    */
    public int              nSearchType;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[868];

    public NET_CENTRA_PROXY_DETAIL_INFO() {
        for(int i = 0; i < stInterviewees.length; i++){
            stInterviewees[i] = new NET_NEW_INTERVIEWEE_INFO();
        }
        for(int i = 0; i < stAreaList.length; i++){
            stAreaList[i] = new NET_VISITOR_AREALIST_INFO();
        }
        for(int i = 0; i < stVistorTypeList.length; i++){
            stVistorTypeList[i] = new NET_VISITOR_TYPE_LIST_INFO();
        }
    }
}

