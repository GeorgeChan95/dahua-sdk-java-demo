package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_PTZStartTour 接口输入参数
*/
public class NET_IN_PTZ_START_TOUR extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 通道号, 范围 0~设备通道数
    */
    public int              nChannel;
    /**
     * 巡航线路编号
    */
    public int              nIndex;

    public NET_IN_PTZ_START_TOUR() {
        this.dwSize = this.size();
    }
}

