package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 船只查询条件 ( CLIENT_FindFileEx + DH_FILE_QUERY_BOAT_SEARCH )
*/
public class MEDIAFILE_BOAT_SEARCH_PARAM extends NetSDKLib.SdkStructure
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
     * 查询规则个数
    */
    public int              nRuleNum;
    /**
     * 查询规则,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.NET_EM_BOAT_SEARCH_RULE}
    */
    public int[]            emRule = new int[4];
    /**
     * 规则动作,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.NET_EM_BOAT_SEARCH_ACTION}
    */
    public int              emAction;
    /**
     * 方向,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.NET_EM_BOAT_SEARCH_DIRECTION}
    */
    public int              emDirection;
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

    public MEDIAFILE_BOAT_SEARCH_PARAM() {
        this.dwSize = this.size();
    }
}

