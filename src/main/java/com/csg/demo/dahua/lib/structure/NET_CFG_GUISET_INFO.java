package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * GUI界面的配置 NET_EM_CFG_GUISET
*/
public class NET_CFG_GUISET_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 时间段数量
    */
    public int              nTimeSectionCount;
    /**
     * LCD常亮配置时间段,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TSECT}
    */
    public NetSDKLib.NET_TSECT[] stuLCDTimeSection = new NetSDKLib.NET_TSECT[8];
    /**
     * LCD是否始终常亮
    */
    public int              bLCDAlwaysOn;

    public NET_CFG_GUISET_INFO() {
        this.dwSize = this.size();
        for(int i = 0; i < stuLCDTimeSection.length; i++){
            stuLCDTimeSection[i] = new NetSDKLib.NET_TSECT();
        }
    }
}

