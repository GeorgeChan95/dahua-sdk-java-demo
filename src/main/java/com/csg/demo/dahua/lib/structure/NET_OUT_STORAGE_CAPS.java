package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_GetDevCaps 接口 NET_STORAGE_CAPS命令出参
*/
public class NET_OUT_STORAGE_CAPS extends NetSDKLib.SdkStructure
{
    public int              dwSize;
    /**
     * 格式化硬盘后是否需要重启,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.NET_EM_FORMAT_NEEDREBOOT}
    */
    public int              emReboot;
    /**
     * 是否支持定时存储
    */
    public int              bIsGeneralRecord;
    /**
     * 是否支持基于目标轨迹的录像查找功能
    */
    public int              bObjectTrackSearch;
    /**
     * 基于目标轨迹的录像查找功能,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_OBJECT_TRACK_SEARCH}
    */
    public NET_OBJECT_TRACK_SEARCH stuObjectTrackSearch = new NET_OBJECT_TRACK_SEARCH();
    /**
     * 是否支持格式化状态查询模式
    */
    public int              bSupportFormatQueryMode;
    /**
     * 是否支持格式化进度查询
    */
    public int              bSupportFormatProgress;

    public NET_OUT_STORAGE_CAPS() {
        this.dwSize = this.size();
    }
}

