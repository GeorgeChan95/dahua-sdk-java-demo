package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 涉水安全检测、水域监测报警事件查询 ( CLIENT_FindFileEx + DH_FILE_QUERY_WADING_DETECTION )
*/
public class NET_MEDIAFILE_WADING_DETECTION_PARAM extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 通道号
    */
    public int              nChannelID;
    /**
     * 通道号数组有效个数
    */
    public int              nChannelsNum;
    /**
     * 通道号数组
    */
    public int[]            nChannels = new int[256];
    /**
     * 起始时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuStartTime = new NetSDKLib.NET_TIME();
    /**
     * 结束时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuEndTime = new NetSDKLib.NET_TIME();
    /**
     * 文件类型,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.EM_FINDFILE_TYPE}
    */
    public int[]            emFileType = new int[8];
    /**
     * 文件类型个数, 为0表示查询任意类型
    */
    public int              nFileTypeNum;
    /**
     * 为TRUE表示仅下发stuStartTimeRealUTC和stuEndTimeRealUTC(不下发stuStartTime, stuEndTime), 为FALSE表示仅下发stuStartTime, stuEndTime(不下发stuStartTimeRealUTC和stuEndTimeRealUTC)
    */
    public int              bOnlySupportRealUTC;
    /**
     * UTC开始时间(标准UTC时间), 与stuEndTimeRealUTC配对使用，与(stuStartTime, stuEndTime)互斥,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuStartTimeRealUTC = new NetSDKLib.NET_TIME();
    /**
     * UTC结束时间(标准UTC时间), 与stuStartTimeRealUTC配对使用，与(stuStartTime, stuEndTime)互斥,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuEndTimeRealUTC = new NetSDKLib.NET_TIME();
    /**
     * 事件类型列表,参见智能分析事件类型
    */
    public int[]            nEventLists = new int[256];
    /**
     * 事件总数
    */
    public int              nEventCount;
    /**
     * 标志总数
    */
    public int              nFlagCount;
    /**
     * 录像或抓图文件标志, 不设置标志表示查询所有文件,参见枚举定义 {@link com.csg.demo.dahua.lib.NetSDKLib.EM_RECORD_SNAP_FLAG_TYPE}
    */
    public int[]            emFlagLists = new int[128];
    /**
     * 规则类型
    */
    public BYTE_ARRAY_32[]  szRule = new BYTE_ARRAY_32[64];
    /**
     * 规则类型数量
    */
    public int              nRuleNum;

    public NET_MEDIAFILE_WADING_DETECTION_PARAM() {
        this.dwSize = this.size();
        for(int i = 0; i < szRule.length; i++){
            szRule[i] = new BYTE_ARRAY_32();
        }
    }
}

