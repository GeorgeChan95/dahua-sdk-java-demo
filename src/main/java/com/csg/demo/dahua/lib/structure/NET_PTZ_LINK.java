package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 云台联动项
*/
public class NET_PTZ_LINK extends NetSDKLib.SdkStructure
{
    /**
     * 云台联动类型,参见枚举定义 {@link com.csg.demo.dahua.lib.NetSDKLib.CFG_LINK_TYPE}
    */
    public int              emType;
    /**
     * 联动参数1
    */
    public int              nParam1;
    /**
     * 联动参数2
    */
    public int              nParam2;
    /**
     * 联动参数3
    */
    public int              nParam3;
    /**
     * 所联动云台通道
    */
    public int              nChannelID;

    public NET_PTZ_LINK() {
    }
}

