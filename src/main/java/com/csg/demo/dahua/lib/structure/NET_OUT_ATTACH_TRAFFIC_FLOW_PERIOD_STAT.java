package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_AttachTrafficFlowPeriodStat 输出参数
*/
public class NET_OUT_ATTACH_TRAFFIC_FLOW_PERIOD_STAT extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;

    public NET_OUT_ATTACH_TRAFFIC_FLOW_PERIOD_STAT() {
        this.dwSize = this.size();
    }
}

