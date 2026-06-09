package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 报警事件类型EVENT_IVS_CONVEYER_BELT_COAL_RATIO (传送带煤量检测) 对应的数据块描述信息
*/
public class DEV_EVENT_CONVEYER_BELT_COAL_RATIO_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 通道号
    */
    public int              nChannelID;
    /**
     * 事件动作,1表示持续性事件开始,2表示持续性事件结束;
    */
    public int              nEventAction;
    /**
     * 事件名称
    */
    public byte[]           szName = new byte[128];
    /**
     * 智能事件所属大类,参见枚举定义 {@link com.csg.demo.dahua.lib.NetSDKLib.EM_CLASS_TYPE}
    */
    public int              emClassType;
    /**
     * 智能事件规则编号，用于标示哪个规则触发的事件
    */
    public int              nRuleID;
    /**
     * 事件发生的时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME_EX}
    */
    public NetSDKLib.NET_TIME_EX UTC = new NetSDKLib.NET_TIME_EX();
    /**
     * 事件时间毫秒数
    */
    public int              UTCMS;
    /**
     * 事件编号，用来唯一标志一个事件
    */
    public int              nEventID;
    /**
     * 规则检测区域顶点数
    */
    public int              nDetectRegionNum;
    /**
     * 规则检测区域,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_UINT_POINT}
    */
    public NET_UINT_POINT[] stuDetectRegion = new NET_UINT_POINT[20];
    /**
     * 当前煤量占比值
    */
    public float            fCoalData;
    /**
     * 本次事件触发对应的报警模式
    */
    public int              nAlarmOutMode;
    /**
     * 全景广角图信息,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
    */
    public NetSDKLib.SCENE_IMAGE_INFO_EX stuSceneImage = new NetSDKLib.SCENE_IMAGE_INFO_EX();
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1024];

    public DEV_EVENT_CONVEYER_BELT_COAL_RATIO_INFO() {
        for(int i = 0; i < stuDetectRegion.length; i++){
            stuDetectRegion[i] = new NET_UINT_POINT();
        }
    }
}

