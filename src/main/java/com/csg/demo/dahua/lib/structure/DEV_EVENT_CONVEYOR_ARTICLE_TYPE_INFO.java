package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 事件类型 EVENT_IVS_CONVEYOR_ARTICLE_TYPE (传送带异物检测事件)对应的数据块描述信息
*/
public class DEV_EVENT_CONVEYOR_ARTICLE_TYPE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 通道号
    */
    public int              nChannelID;
    /**
     * 0:脉冲,1:开始, 2:停止
    */
    public int              nAction;
    /**
     * 事件名称
    */
    public byte[]           szName = new byte[128];
    /**
     * 智能事件所属大类
    */
    public byte[]           szClass = new byte[16];
    /**
     * 智能事件规则编号，用于标示哪个规则触发的事件
    */
    public int              nRuleID;
    /**
     * 事件时间毫秒数
    */
    public int              nUTCMS;
    /**
     * 相对事件时间戳,(单位是毫秒)
    */
    public double           dbPTS;
    /**
     * 事件发生的时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME_EX}
    */
    public NetSDKLib.NET_TIME_EX stuUTC = new NetSDKLib.NET_TIME_EX();
    /**
     * 事件ID
    */
    public int              nEventID;
    /**
     * 检测区,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_POINT}
    */
    public NetSDKLib.NET_POINT[] stuDetectRegion = new NetSDKLib.NET_POINT[20];
    /**
     * 检测区个数
    */
    public int              nDetectRegionNum;
    /**
     * 检测异物类型,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.EM_ARTICLE_TYPE}
    */
    public int              emArticleType;
    /**
     * 全景广角图,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
    */
    public NetSDKLib.SCENE_IMAGE_INFO_EX stuSceneImage = new NetSDKLib.SCENE_IMAGE_INFO_EX();
    /**
     * 物体信息数据,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_MSG_OBJECT_EX2}
    */
    public Pointer          pstObjectInfo;
    /**
     * 物体信息数
    */
    public int              nObjectNum;
    /**
     * RealUTC 是否有效，bRealUTC 为 TRUE 时，用 RealUTC ，否则用 UTC 字段
    */
    public int              bRealUTC;
    /**
     * 事件发生的时间（标准UTC）,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME_EX}
    */
    public NetSDKLib.NET_TIME_EX RealUTC = new NetSDKLib.NET_TIME_EX();
    /**
     * 异物事件累计计数
    */
    public int              nArticleEventCount;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[976-NetSDKLib.POINTERSIZE];

    public DEV_EVENT_CONVEYOR_ARTICLE_TYPE_INFO() {
        for(int i = 0; i < stuDetectRegion.length; i++){
            stuDetectRegion[i] = new NetSDKLib.NET_POINT();
        }
    }
}

