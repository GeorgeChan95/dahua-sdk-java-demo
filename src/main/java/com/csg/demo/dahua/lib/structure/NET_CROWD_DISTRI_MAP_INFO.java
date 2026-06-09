package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 人群分布图规则报表数据
*/
public class NET_CROWD_DISTRI_MAP_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 人群分布图规则报表数据,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_CROWD_DISTRI_MAP}
    */
    public NET_CROWD_DISTRI_MAP stuCrowdDistriMap = new NET_CROWD_DISTRI_MAP();
    /**
     * 保留字段
    */
    public byte[]           szReserved = new byte[1024];

    public NET_CROWD_DISTRI_MAP_INFO() {
    }
}

