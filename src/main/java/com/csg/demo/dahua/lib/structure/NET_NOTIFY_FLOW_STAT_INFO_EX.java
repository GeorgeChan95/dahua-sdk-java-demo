package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 流量统计信息
*/
public class NET_NOTIFY_FLOW_STAT_INFO_EX extends NetSDKLib.SdkStructure
{
    /**
     * 交通流量事件信息,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.DEV_EVENT_TRAFFIC_FLOW_STATE}
    */
    public NetSDKLib.DEV_EVENT_TRAFFIC_FLOW_STATE stuTrafficFlowStatDataInfo = new NetSDKLib.DEV_EVENT_TRAFFIC_FLOW_STATE();
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[2048];

    public NET_NOTIFY_FLOW_STAT_INFO_EX() {
    }
}

