package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 多楼层信息
*/
public class NET_MULTI_FLOOR extends NetSDKLib.SdkStructure
{
    /**
     * 楼层信息，定制使用
    */
    public byte[]           szFloors = new byte[128];
    /**
     * 预留
    */
    public byte[]           byReserved = new byte[1024];

    public NET_MULTI_FLOOR() {
    }
}

