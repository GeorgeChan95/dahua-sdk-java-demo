package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 随行人员信息
*/
public class NET_VISITOR_ENTOURAGES_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 随行人员姓名
    */
    public byte[]           szNames = new byte[64];
    /**
     * 随行人员姓名扩展
    */
    public byte[]           szNamesEx = new byte[128];
    /**
     * 是否使用随行人员姓名扩展
    */
    public int              bUseNamesEx;
    /**
     * 随行人员姓名扩展字段字符串长度（等价于strlen），最大值为511
    */
    public int              nSuperLongNamesLen;
    /**
     * 随行人员姓名扩展字段字符串，需访客业务支持超长访客信息，由用户申请和释放内存
    */
    public Pointer          szSupeLongNamesEx;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[120-NetSDKLib.POINTERSIZE];

    public NET_VISITOR_ENTOURAGES_INFO() {
    }
}

