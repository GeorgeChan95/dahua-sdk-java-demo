package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 数据传输细节，Action为访客机数据传输时有效
*/
public class NET_CB_CENTRA_PROXY_DETAIL_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 来访者信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_NEW_VISITOR_INFO}
    */
    public NET_NEW_VISITOR_INFO stuVisitor = new NET_NEW_VISITOR_INFO();
    /**
     * 被访者信息数组,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_NEW_INTERVIEWEE_INFO}
    */
    public NET_NEW_INTERVIEWEE_INFO stuInterviewee = new NET_NEW_INTERVIEWEE_INFO();
    /**
     * 具体传输内容 0：客户端向服务端发送访客信息 1：客户端向服务端发送指定被访者信息 2：客户端向服务端发送访客区域, 3：客户端向服务端发送访客类型列表 4：客户端向服务端发送需要补充的信息 5：客户端向服务端发送通行二维码 6：客户端答复推送信息收到 7：客户端下发被访人的访客区域信息。8：客户端分页下发查询访客列表信息
    */
    public int              nTransMethod;
    /**
     * 通过被访人查询访客区域，TransMethod=8时有效,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISITOR_AREA_CONDITION_INFO}
    */
    public NET_VISITOR_AREA_CONDITION_INFO stuVisitAreaCondition = new NET_VISITOR_AREA_CONDITION_INFO();
    /**
     * 访客列表 TransMethod=7时有效,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISITOR_LIST_CONDITION_INFO}
    */
    public NET_VISITOR_LIST_CONDITION_INFO stuVisitorListCondition = new NET_VISITOR_LIST_CONDITION_INFO();
    /**
     * 访客登记类型，-1:未知，0：普通登记，1：快速登记
    */
    public int              nRegisterType;
    /**
     * 搜索类型，-1:未知，0：普通搜索，1：模糊搜索。
    */
    public int              nSearchType;
    /**
     * 访客查询扩展信息：访客业务支持超长的访客信息，由用户申请和释放内存，内存大小为sieof(NET_VISITOR_CONDITION_EXTEND_INFO),参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISITOR_CONDITION_EXTEND_INFO}
    */
    public Pointer          pstVisitorCondExtendInfo;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1012-NetSDKLib.POINTERSIZE];

    public NET_CB_CENTRA_PROXY_DETAIL_INFO() {
    }
}

