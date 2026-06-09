package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 报警记录信息查询条件
*/
public class FIND_RECORD_ALARMRECORD_CONDITION extends NetSDKLib.SdkStructure
{
    public int              dwSize;
    /**
     * 启用时间段查询
    */
    public int              bTimeEnable;
    /**
     * 起始时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stStartTime = new NetSDKLib.NET_TIME();
    /**
     * 结束时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stEndTime = new NetSDKLib.NET_TIME();

    public FIND_RECORD_ALARMRECORD_CONDITION() {
        this.dwSize = this.size();
    }
}

