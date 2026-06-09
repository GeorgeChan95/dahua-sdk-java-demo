package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 灯光方案配置 (对应命令 NET_EM_CFG_VIDEOIN_LIGHTING_SCHEME)
*/
public class NET_CFG_VIDEOIN_LIGHTING_SCHEME_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 灯光方案个数
    */
    public int              nModeNum;
    /**
     * 灯光方案 元素0开始分别表示 白天、夜晚、普通、顺光、一般逆光、强逆光、低照度、自定义,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.EM_LIGHTING_SCHEME_MODE}
    */
    public int[]            emMode = new int[8];
    /**
     * 灯光方案按时间段切换是否使能, 元素0开始分别表示 白天、夜晚、普通、顺光、一般逆光、强逆光、低照度、自定义
    */
    public int[]            bSchemeSchedule = new int[8];
    /**
     * 灯光方案按时间段切换, 元素0开始分别表示 白天、夜晚、普通、顺光、一般逆光、强逆光、低照度、自定义,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_SCHEME_SCHEDULE}
    */
    public NET_SCHEME_SCHEDULE[] stuSchemeSchedule = new NET_SCHEME_SCHEDULE[8];

    public NET_CFG_VIDEOIN_LIGHTING_SCHEME_INFO() {
        this.dwSize = this.size();
        for(int i = 0; i < stuSchemeSchedule.length; i++){
            stuSchemeSchedule[i] = new NET_SCHEME_SCHEDULE();
        }
    }
}

