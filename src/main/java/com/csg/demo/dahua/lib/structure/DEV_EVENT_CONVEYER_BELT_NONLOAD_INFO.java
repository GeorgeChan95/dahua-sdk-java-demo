package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 报警事件类型EVENT_IVS_CONVEYER_BELT_NONLOAD (传送带空载检测事件) 对应的数据块描述信息
*/
public class DEV_EVENT_CONVEYER_BELT_NONLOAD_INFO extends NetSDKLib.SdkStructure
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
     * 时间戳(单位是毫秒)
    */
    public double           PTS;
    /**
     * 事件发生的时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME_EX}
    */
    public NetSDKLib.NET_TIME_EX UTC = new NetSDKLib.NET_TIME_EX();
    /**
     * 规则编号,用于标示哪个规则触发的事件，缺省时默认为0
    */
    public int              nRuleID;
    /**
     * 事件ID
    */
    public int              nEventID;
    /**
     * 智能事件所属大类,参见枚举定义 {@link com.csg.demo.dahua.lib.NetSDKLib.EM_CLASS_TYPE}
    */
    public int              emClassType;
    /**
     * 规则检测区域,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_POINT}
    */
    public NetSDKLib.NET_POINT[] stuDetectRegion = new NetSDKLib.NET_POINT[20];
    /**
     * 规则检测区域顶点数
    */
    public int              nDetectRegionNum;
    /**
     * 全景广角图信息,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
    */
    public NetSDKLib.SCENE_IMAGE_INFO_EX stuSceneImage = new NetSDKLib.SCENE_IMAGE_INFO_EX();
    /**
     * 装载率报警等级
    */
    public int              nAlarmLevel;
    /**
     * 0:未知 1:实时数据 2:报警数据
    */
    public int              nEventDataType;
    /**
     * 装载率, 百分比
    */
    public float            fLoadingRate;
    /**
     * 保留字节
    */
    public byte[]           byReserved = new byte[1012];

    public DEV_EVENT_CONVEYER_BELT_NONLOAD_INFO() {
        for(int i = 0; i < stuDetectRegion.length; i++){
            stuDetectRegion[i] = new NetSDKLib.NET_POINT();
        }
    }
}

