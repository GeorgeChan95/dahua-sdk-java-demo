package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 显示更新请求相关配置
*/
public class NET_DISPLAY_SHOW_DATA extends NetSDKLib.SdkStructure
{
    /**
     * 显示器节目个数
    */
    public int              nProgramNumber;
    /**
     * 显示屏节目数据最大个数
    */
    public int              nMaxProgramDataNum;
    /**
     * 显示屏节目数据,内存由用户申请,大小为sizeof(NET_DISPLAY_SHOW_PROGRAM_DATA)*nMaxProgramDataNum,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_DISPLAY_SHOW_PROGRAM_DATA}
    */
    public Pointer          pstuProgramData;
    /**
     * 显示屏节目数据实际个数
    */
    public int              nRetProgramDataNum;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1020];

    public NET_DISPLAY_SHOW_DATA() {
    }
}

