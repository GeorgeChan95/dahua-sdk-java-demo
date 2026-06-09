package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 被访问目标信息
*/
public class NET_VISITOR_TARGET_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 被访人ID
    */
    public byte[]           szIntervieweeID = new byte[64];
    /**
     * 被访人工号
    */
    public byte[]           szWorkNumber = new byte[64];
    /**
     * 被访部门
    */
    public byte[]           szDepartment = new byte[128];
    /**
     * 被访人姓名
    */
    public byte[]           szIntervieweeName = new byte[128];
    /**
     * 被访人联系电话
    */
    public byte[]           szPhoneNo = new byte[16];
    /**
     * 被访人联系电话扩展字段，需访客业务支持超长访客信息
    */
    public byte[]           szPhoneNoEx = new byte[64];
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[304];

    public NET_VISITOR_TARGET_INFO() {
    }
}

