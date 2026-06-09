package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * CLIENT_AttachIotboxCommByCond 接口入参
*/
public class NET_IN_ATTACH_IOTBOX_COMM_BY_COND extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 过滤器数组数量
    */
    public int              nFilterNum;
    /**
     * 过滤器数组信息，每个代表一个传感器通道的过滤条件,内存由用户申请，sizeof(NET_IOTBOX_COMM_BY_COND_FILTER)*nFilterNum,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_IOTBOX_COMM_BY_COND_FILTER}
    */
    public Pointer          pstuFilter;
    /**
     * 回调函数,参见回调函数定义 {@link com.csg.demo.dahua.lib.NetSDKLib.fNotifyIotboxCommByCond}
    */
    public NetSDKLib.fNotifyIotboxCommByCond cbNotifyIotboxCommByCond;
    /**
     * 用户自定义参数
    */
    public Pointer          dwUser;
    /**
     * 数据上报频率，单位秒，取值范围0~3600s
    */
    public int              nNotifyPeriod;

    public NET_IN_ATTACH_IOTBOX_COMM_BY_COND() {
        this.dwSize = this.size();
    }
}

