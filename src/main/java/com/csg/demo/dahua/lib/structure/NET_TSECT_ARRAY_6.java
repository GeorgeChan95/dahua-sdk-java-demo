package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;

public class NET_TSECT_ARRAY_6 extends NetSDKLib.SdkStructure {
    /**
     * 参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TSECT}
    */
    public NetSDKLib.NET_TSECT[] obj_6 = new NetSDKLib.NET_TSECT[6];

    public NET_TSECT_ARRAY_6() {
        for(int i = 0; i < obj_6.length; i++){
            obj_6[i] = new NetSDKLib.NET_TSECT();
        }
    }
}

