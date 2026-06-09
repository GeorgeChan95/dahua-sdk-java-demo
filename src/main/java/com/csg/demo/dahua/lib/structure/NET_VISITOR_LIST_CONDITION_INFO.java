package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 访客列表 TransMethod=7时有效
*/
public class NET_VISITOR_LIST_CONDITION_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 条件字段的逻辑关系，-1:未知 0：所有条件字段且的关系（&&），1：所有字段或者的关系（||）
    */
    public int              nLogicType;
    /**
     * 每一页返回多少条记录
    */
    public int              nPageSize;
    /**
     * 查询的页码
    */
    public int              nPageNum;
    /**
     * 访客来访时间段开始时间, 废弃，使用RealUTC,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuStartTime = new NetSDKLib.NET_TIME();
    /**
     * 访客来访时间段结束时间, 废弃，使用RealUTC,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuEndTime = new NetSDKLib.NET_TIME();
    /**
     * 被访人姓名
    */
    public byte[]           szIntervieweeName = new byte[64];
    /**
     * 被访人id
    */
    public byte[]           szIntervieweeID = new byte[64];
    /**
     * 访客姓名
    */
    public byte[]           szVisitorName = new byte[64];
    /**
     * 访客手机号
    */
    public byte[]           szPhoneNo = new byte[32];
    /**
     * 访客状态个数
    */
    public int              nStatusCount;
    /**
     * 访客状态, 0:待审核，1：预约，2：在访，3：离访，4：预约取消
    */
    public int[]            nStatus = new int[16];
    /**
     * 被访人工号
    */
    public byte[]           szIntervieweeWorkNo = new byte[64];
    /**
     * 被访人的部门
    */
    public byte[]           szDepartment = new byte[128];
    /**
     * 访客证件号
    */
    public byte[]           szCertificateNumber = new byte[64];
    /**
     * 访客来访时间段开始时间,UTC时间
    */
    public int              nStartTimeRealUTC;
    /**
     * 访客来访时间段结束时间，UTC时间
    */
    public int              nEndTimeRealUTC;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[184];

    public NET_VISITOR_LIST_CONDITION_INFO() {
    }
}

