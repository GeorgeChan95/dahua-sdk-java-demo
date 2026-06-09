package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 水利查询结果 ( CLIENT_FindNextFileEx + DH_FILE_QUERY_WATER_CONSERVANCY_SEARCH )
*/
public class MEDIAFILE_WATER_CONSERVANCY_SEARCH_INFO extends NetSDKLib.SdkStructure
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
     * 事件类型,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.NET_EM_WATER_CONSERVANCY_SEARCH_EVENT_TYPE}
    */
    public int              emEventType;
    /**
     * 漂浮物占比
    */
    public int              nCurrentRatio;
    /**
     * 水位状态,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.NET_EM_WATER_STATUS}
    */
    public int              emWaterStatus;
    /**
     * 水位值（对于有拼接的情况，该值为标定的基准值加上当前刻度值，单位：米）
    */
    public float            fWaterValue;
    /**
     * 污水颜色,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.NET_EM_WATER_CONSERVANCY_SEARCH_WATER_COLOR}
    */
    public int              emWaterColor;
    /**
     * 全景大图图片路径
    */
    public byte[]           szGlobalSceneFilePath = new byte[256];
    /**
     * 全景大图图片长度
    */
    public int              nGlobalScenePicLength;
    /**
     * 抠图信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_WATER_CONSERVANCY_SEARCH_IMAGE_INFO}
    */
    public NET_WATER_CONSERVANCY_SEARCH_IMAGE_INFO stuImageInfo = new NET_WATER_CONSERVANCY_SEARCH_IMAGE_INFO();
    /**
     * 为TRUE表示仅stuStartTimeRealUTC和stuEndTimeRealUTC有效(仅使用stuStartTimeRealUTC和stuEndTimeRealUTC), 为FALSE表示仅stuStartTime和stuEndTime有效(仅使用stuStartTime和stuEndTime)
    */
    public int              bRealUTC;
    /**
     * UTC开始时间(标准UTC时间), 与stuEndTimeRealUTC配对使用,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuStartTimeRealUTC = new NetSDKLib.NET_TIME();
    /**
     * UTC结束时间(标准UTC时间), 与stuStartTimeRealUTC配对使用,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuEndTimeRealUTC = new NetSDKLib.NET_TIME();

    public MEDIAFILE_WATER_CONSERVANCY_SEARCH_INFO() {
        this.dwSize = this.size();
    }
}

