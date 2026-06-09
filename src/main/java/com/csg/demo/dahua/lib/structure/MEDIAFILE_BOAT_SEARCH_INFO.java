package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 船只查询结果 ( CLIENT_FindNextFileEx + DH_FILE_QUERY_BOAT_SEARCH )
*/
public class MEDIAFILE_BOAT_SEARCH_INFO extends NetSDKLib.SdkStructure
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
     * 查询规则,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.NET_EM_BOAT_SEARCH_RULE}
    */
    public int              emRule;
    /**
     * 规则动作,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.NET_EM_BOAT_SEARCH_ACTION}
    */
    public int              emAction;
    /**
     * 方向,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.NET_EM_BOAT_SEARCH_DIRECTION}
    */
    public int              emDirection;
    /**
     * 全景大图图片路径
    */
    public byte[]           szGlobalSceneFilePath = new byte[256];
    /**
     * 全景大图图片长度
    */
    public int              nGlobalScenePicLength;
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

    public MEDIAFILE_BOAT_SEARCH_INFO() {
        this.dwSize = this.size();
    }
}

