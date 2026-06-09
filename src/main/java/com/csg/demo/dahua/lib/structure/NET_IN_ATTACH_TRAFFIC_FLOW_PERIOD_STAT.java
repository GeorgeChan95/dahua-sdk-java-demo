package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * CLIENT_AttachTrafficFlowPeriodStat 输入参数
*/
public class NET_IN_ATTACH_TRAFFIC_FLOW_PERIOD_STAT extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 字节对齐
    */
    public byte[]           szReserved = new byte[4];
    /**
     * 回调函数,参见回调函数定义 {@link com.csg.demo.dahua.lib.NetSDKLib.fNotifyFlowStatInfoEx}
    */
    public NetSDKLib.fNotifyFlowStatInfoEx cbNotifyFlowStatInfoEx;
    /**
     * 用户信息
    */
    public Pointer          dwUser;

    public NET_IN_ATTACH_TRAFFIC_FLOW_PERIOD_STAT() {
        this.dwSize = this.size();
    }
}

