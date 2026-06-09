package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_GetObjectMediaFindState 接口输出参数
*/
public class NET_OUT_GET_OBJECT_MEDIA_FIND_STATE extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 当前查找状态, 0:搜索未启动, 1:搜索中, 2:搜索完成
    */
    public int              nFindStatus;
    /**
     * 已查找到的录像文件信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_OBJECT_MEDIA_FILE_INFO}
    */
    public NET_OBJECT_MEDIA_FILE_INFO stuFileInfo = new NET_OBJECT_MEDIA_FILE_INFO();
    /**
     * 已查找到的目标事件信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_OBJECT_MEDIA_EVENT_INFO}
    */
    public NET_OBJECT_MEDIA_EVENT_INFO stuEventInfo = new NET_OBJECT_MEDIA_EVENT_INFO();

    public NET_OUT_GET_OBJECT_MEDIA_FIND_STATE() {
        this.dwSize = this.size();
    }
}

