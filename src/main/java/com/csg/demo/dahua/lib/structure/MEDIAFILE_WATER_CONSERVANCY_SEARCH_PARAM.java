package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 水利查询条件 ( CLIENT_FindFileEx + DH_FILE_QUERY_WATER_CONSERVANCY_SEARCH )
*/
public class MEDIAFILE_WATER_CONSERVANCY_SEARCH_PARAM extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 通道号从0开始,-1表示查询所有通道
    */
    public int              nChannelID;
    /**
     * 开始时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuStartTime = new NetSDKLib.NET_TIME();
    /**
     * 结束时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuEndTime = new NetSDKLib.NET_TIME();
    /**
     * 水利查询事件类型,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.NET_EM_WATER_CONSERVANCY_SEARCH_EVENT_TYPE}
    */
    public int              emEventType;
    /**
     * 水体颜色检测搜索颜色, emEventType为NET_EM_WATER_CONSERVANCY_SEARCH_EVENT_TYPE_WATER_COLOR_DETECTION时有效,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.NET_EM_WATER_CONSERVANCY_SEARCH_WATER_COLOR}
    */
    public int              emWaterColor;
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

    public MEDIAFILE_WATER_CONSERVANCY_SEARCH_PARAM() {
        this.dwSize = this.size();
    }
}

