package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 事件类型 EVENT_IVS_GARBAGE_EXPOSURE(垃圾暴露检测事件)对应的规则配置
*/
public class NET_GARBAGE_EXPOSURE_RULE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 检测区域个数
    */
    public int              nDetectRegionNum;
    /**
     * 检测区域,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_POINT_EX}
    */
    public NET_POINT_EX[]   stuDetectRegion = new NET_POINT_EX[20];
    /**
     * 最短持续时间,单位：秒 0-3600s
    */
    public int              nMinDuration;
    /**
     * 跟踪持续时间,单位：秒 0-3600s
    */
    public int              nTrackDuration;
    /**
     * 检测区域号
    */
    public int              nDetectRegionNumber;
    /**
     * 灵敏度,值越小灵敏度越低,取值1-10
    */
    public int              nSensitivity;
    /**
     * 重复报警时间，单位：秒，0则不重复报警
    */
    public int              nReportInterval;
    /**
     * 证据链图片张数，取值1-4，1表示不使用证据链
    */
    public int              nAlarmPicNum;
    /**
     * 抓图是否叠加目标框，true叠加，false不叠加
    */
    public int              bSnapObjRectEnable;
    /**
     * 最大重复报警时间 单位：秒，此字段只有在不使用证据链时有效，表示同一目标，其首次报警后时间超过该时间段，则不会重复报警
    */
    public int              nMaxReportInterval;

    public NET_GARBAGE_EXPOSURE_RULE_INFO() {
        this.dwSize = this.size();
        for(int i = 0; i < stuDetectRegion.length; i++){
            stuDetectRegion[i] = new NET_POINT_EX();
        }
    }
}

