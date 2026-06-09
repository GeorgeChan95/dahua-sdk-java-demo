package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 已查找到的目标事件信息
*/
public class NET_OBJECT_MEDIA_EVENT_INFO extends NetSDKLib.SdkStructure
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
     * 目标类型信息数组,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_OBJECT_MEDIA_EVENT_OBJECT_INFO}
    */
    public NET_OBJECT_MEDIA_EVENT_OBJECT_INFO[] stuObjectInfo = new NET_OBJECT_MEDIA_EVENT_OBJECT_INFO[8];
    /**
     * 目标类型信息数组个数
    */
    public int              nObjectInfoCount;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1020];

    public NET_OBJECT_MEDIA_EVENT_INFO() {
        for(int i = 0; i < stuObjectInfo.length; i++){
            stuObjectInfo[i] = new NET_OBJECT_MEDIA_EVENT_OBJECT_INFO();
        }
    }
}

