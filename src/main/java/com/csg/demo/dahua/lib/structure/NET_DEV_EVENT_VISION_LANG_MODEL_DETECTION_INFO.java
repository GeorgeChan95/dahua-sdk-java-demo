package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 事件类型 EVENT_IVS_VISION_LANG_MODEL_DETECTION对应的数据描述信息
*/
public class NET_DEV_EVENT_VISION_LANG_MODEL_DETECTION_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 通道号
    */
    public int              nChannelID;
    /**
     * 0:脉冲，1:开始，2:停止
    */
    public int              nAction;
    /**
     * 扩展协议字段,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_EVENT_INFO_EXTEND}
    */
    public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND();
    /**
     * 事件发生的时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME_EX}
    */
    public NetSDKLib.NET_TIME_EX stuUTC = new NetSDKLib.NET_TIME_EX();
    /**
     * 事件编号
    */
    public int              nEventID;
    /**
     * 事件名称
    */
    public byte[]           szName = new byte[128];
    /**
     * 智能事件所属大类
    */
    public byte[]           szClass = new byte[32];
    /**
     * 事件组ID
    */
    public int              nGroupID;
    /**
     * 一个事件组内应有的抓拍张数
    */
    public int              nCountInGroup;
    /**
     * 一个事件组内的抓拍序号
    */
    public int              nIndexInGroup;
    /**
     * 帧序号
    */
    public int              nSequence;
    /**
     * 相对事件时间戳，单位毫秒
    */
    public double           dbPTS;
    /**
     * 视频分析帧序号
    */
    public int              nFrameSequence;
    /**
     * 自定义报警id
    */
    public int              nAlarmId;
    /**
     * 规则使用的算法id
    */
    public byte[]           szAlgId = new byte[32];
    /**
     * 算法名称
    */
    public byte[]           szAlgName = new byte[128];
    /**
     * 智能事件规则编号
    */
    public int              nRuleID;
    /**
     * 特征值信息个数
    */
    public int              nFeatureVectorNum;
    /**
     * 特征值信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_FEATURE_VECTORS_INFO}
    */
    public NET_FEATURE_VECTORS_INFO[] stuFeatureVector = new NET_FEATURE_VECTORS_INFO[8];
    /**
     * 全景图,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
    */
    public NetSDKLib.SCENE_IMAGE_INFO_EX stuImage = new NetSDKLib.SCENE_IMAGE_INFO_EX();
    /**
     * 大模型定时抓图多目标信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISION_LANG_MODEL_DETECTION_OBJECTS}
    */
    public Pointer          pstuObjects;
    /**
     * 大模型定时抓图多目标信息个数
    */
    public int              nObjectsNum;
    /**
     * 原始物体包围盒，用于支持文本布控越用越准,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_RECT_EX}
    */
    public NET_RECT_EX      stuOriginalBoundingBox = new NET_RECT_EX();
    /**
     * 图片信息个数
    */
    public int              nImageInfoNum;
    /**
     * 图片信息数组,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_IMAGE_INFO_EX3}
    */
    public Pointer          pstuImageInfo;
    /**
     * 0：未知 1：待研判 2：正报 3：疑似误报 4：漏报
    */
    public int              nSecondEvaluation;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[996-NetSDKLib.POINTERSIZE*2];

    public NET_DEV_EVENT_VISION_LANG_MODEL_DETECTION_INFO() {
        for(int i = 0; i < stuFeatureVector.length; i++){
            stuFeatureVector[i] = new NET_FEATURE_VECTORS_INFO();
        }
    }
}

