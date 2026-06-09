package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_PTZStartTour 接口输出参数
*/
public class NET_OUT_PTZ_START_TOUR extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_OUT_PTZ_START_TOUR() {
        this.dwSize = this.size();
    }
}

