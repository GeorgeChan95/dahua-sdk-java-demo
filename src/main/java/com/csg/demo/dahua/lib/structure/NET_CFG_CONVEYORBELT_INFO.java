package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 传送带检测预览OSD 实时数据 NET_EM_CFG_CONVEYORBELTINFO
*/
public class NET_CFG_CONVEYORBELT_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 传送带实时装载率
    */
    public float            fLoadingRate;

    public NET_CFG_CONVEYORBELT_INFO() {
        this.dwSize = this.size();
    }
}

