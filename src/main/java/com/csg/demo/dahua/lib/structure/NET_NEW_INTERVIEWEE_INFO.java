package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 被访者信息
*/
public class NET_NEW_INTERVIEWEE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 姓名
    */
    public byte[]           szName = new byte[32];
    /**
     * 电话号码
    */
    public byte[]           szPhone = new byte[16];
    /**
     * 工号
    */
    public byte[]           szWorkNumber = new byte[64];
    /**
     * 证件,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISITOR_CREDENTIALS_INFO}
    */
    public NET_VISITOR_CREDENTIALS_INFO stCredentials = new NET_VISITOR_CREDENTIALS_INFO();
    /**
     * 证件编号
    */
    public byte[]           szLocation = new byte[64];
    /**
     * 编号
    */
    public byte[]           szID = new byte[64];
    /**
     * 部门名称
    */
    public byte[]           szDeptName = new byte[128];
    /**
     * 部门ID
    */
    public byte[]           szDeptId = new byte[64];
    /**
     * 姓名扩展
    */
    public byte[]           szNameEx = new byte[128];
    /**
     * 是否使用姓名扩展
    */
    public int              bUseNameEx;
    /**
     * 被访者扩展信息，需访客业务支持超长的访客信息，由用户申请和释放内存，内存大小为sizeof(NET_NEW_INTERVIEWEE_EXTEND_INFO),参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_NEW_INTERVIEWEE_EXTEND_INFO}
    */
    public Pointer          pstuIntervieweeExtendInfo;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[700-NetSDKLib.POINTERSIZE];

    public NET_NEW_INTERVIEWEE_INFO() {
    }
}

