package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_RadiometryGetRandomRegionTemper 出参
*/
public class NET_OUT_RADIOMETRY_RANDOM_REGION_TEMPER extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 随机测温区域的温度信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_RADIOMETRY_REGION_TEMP_INFO}
    */
    public NET_RADIOMETRY_REGION_TEMP_INFO stuRegionTempInfo = new NET_RADIOMETRY_REGION_TEMP_INFO();

    public NET_OUT_RADIOMETRY_RANDOM_REGION_TEMPER() {
        this.dwSize = this.size();
    }
}

