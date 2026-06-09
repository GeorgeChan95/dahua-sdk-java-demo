package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 已查找到的录像文件信息
*/
public class NET_OBJECT_MEDIA_FILE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 已查找到的录像文件总数
    */
    public int              nTotalCount;
    /**
     * 录像查询进度，百分比，0-100，100表示搜索完成。
    */
    public int              nProgress;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_OBJECT_MEDIA_FILE_INFO() {
    }
}

