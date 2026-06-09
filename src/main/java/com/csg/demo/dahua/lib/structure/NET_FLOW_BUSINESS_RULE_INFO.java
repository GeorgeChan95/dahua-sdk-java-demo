package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 事件类型 EVENT_IVS_FLOWBUSINESS(流动摊贩事件)对应的规则配置
*/
public class NET_FLOW_BUSINESS_RULE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 检测区顶点数
    */
    public int              nDetectRegionNum;
    /**
     * 检测区,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_POINT_EX}
    */
    public NET_POINT_EX[]   stuDetectRegion = new NET_POINT_EX[20];
    /**
     * 最短持续时间,单位：秒, 0-3600
    */
    public short            nMinDuration;
    /**
     * 跟踪持续时间,单位：秒, 0-3600
    */
    public short            nTrackDuration;
    /**
     * 灵敏度，取值1-10，数值越大代表灵敏度越高
    */
    public int              nSensitivity;
    /**
     * 检测区域号
    */
    public int              nDetectRegionNumber;
    /**
     * 重复报警时间，单位：秒，0则不重复报警
    */
    public short            nReportInterval;
    /**
     * 证据链图片张数，取值1-4，1表示不使用证据链
    */
    public short            nAlarmPicNum;
    /**
     * 抓图是否叠加目标框，true叠加，false不叠加，无此字段默认叠加目标框
    */
    public int              bSnapObjRectEnable;
    /**
     * 最大重复报警时间 单位：秒
    */
    public int              nMaxReportInterval;

    public NET_FLOW_BUSINESS_RULE_INFO() {
        this.dwSize = this.size();
        for(int i = 0; i < stuDetectRegion.length; i++){
            stuDetectRegion[i] = new NET_POINT_EX();
        }
    }
}

