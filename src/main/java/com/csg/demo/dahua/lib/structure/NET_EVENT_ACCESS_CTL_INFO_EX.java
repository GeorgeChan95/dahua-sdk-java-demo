package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 事件类型 EVENT_IVS_ACCESS_CTL(门禁事件)对应数据块描述信息扩展结构体
*/
public class NET_EVENT_ACCESS_CTL_INFO_EX extends NetSDKLib.SdkStructure
{
    /**
     * 考勤模式描述
    */
    public byte[]           szAttendanceMode = new byte[256];
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[2048];

    public NET_EVENT_ACCESS_CTL_INFO_EX() {
    }
}

