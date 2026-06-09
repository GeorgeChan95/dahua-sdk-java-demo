package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * NET_EM_CFG_ALERT_SCREEN AlertScreen预警显示屏配置
*/
public class NET_CFG_ALERT_SCREEN_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 格瑞普光电显示屏信息最大数量
    */
    public int              nGRAlertScreenMaxNum;
    /**
     * 格瑞普光电显示屏信息,内存由用户申请,大小为sizeof(NET_CFG_GR_ALERT_SCREEN_INFO)*nGRAlertScreenMaxNum,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_CFG_GR_ALERT_SCREEN_INFO}
    */
    public Pointer          pstuGRAlertScreen;
    /**
     * 格瑞普光电显示屏信息实际数量
    */
    public int              nGRAlertScreenRetNum;

    public NET_CFG_ALERT_SCREEN_INFO() {
        this.dwSize = this.size();
    }
}

