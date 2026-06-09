package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 采集文件数据详细信息
*/
public class NET_COLLOCT_FILE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 文件总数
    */
    public int              nFileTotal;
    /**
     * 采集成功文件个数
    */
    public int              nFileOkCounts;
    /**
     * 采集失败文件个数
    */
    public int              nFileFailCounts;
    /**
     * 文件总长度，单位字节
    */
    public int              nFileTotalLength;
    /**
     * 采集成功文件总大小，单位字节
    */
    public int              nFileOkTotalLength;
    /**
     * 采集失败文件总大小，单位字节
    */
    public int              nFileFailTotalLength;
    /**
     * 保留字节
    */
    public byte[]           szResvered = new byte[128];

    public NET_COLLOCT_FILE_INFO() {
    }
}

