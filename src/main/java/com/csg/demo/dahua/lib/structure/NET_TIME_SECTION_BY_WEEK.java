package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 配置时间部分第二版
*/
public class NET_TIME_SECTION_BY_WEEK extends NetSDKLib.SdkStructure
{
    /**
     * 时间段数量
    */
    public int              nTimeSectionNum;
    /**
     * 每一天的时间段信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_TIME_SECTION_EX2}
    */
    public NET_TIME_SECTION_EX2[] stuTimeSectionEx = new NET_TIME_SECTION_EX2[7];

    public NET_TIME_SECTION_BY_WEEK() {
        for(int i = 0; i < stuTimeSectionEx.length; i++){
            stuTimeSectionEx[i] = new NET_TIME_SECTION_EX2();
        }
    }
}

