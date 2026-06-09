package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 大模型定时抓图多目标信息
*/
public class NET_VISION_LANG_MODEL_DETECTION_OBJECTS extends NetSDKLib.SdkStructure
{
    /**
     * 目标框,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_RECT_EX}
    */
    public NET_RECT_EX      stuBoundingBox = new NET_RECT_EX();
    /**
     * 物体类型
    */
    public byte[]           szObjectType = new byte[32];
    /**
     * 目标ID
    */
    public int              nObjectId;
    /**
     * 正样本相似度(0-100),分值越高越相似
    */
    public int              nSimilarity;
    /**
     * 特征报警结果: -1:未知 0:报警 1:正样本相似度低于阈值 2:归一化相似度低于阈值 3:微模型过滤
    */
    public int              nAlarmStatus;
    /**
     * 特征值信息个数
    */
    public int              nFeatureVectorNum;
    /**
     * 特征值信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_FEATURE_VECTORS_INFO}
    */
    public NET_FEATURE_VECTORS_INFO[] stuFeatureVector = new NET_FEATURE_VECTORS_INFO[8];
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_VISION_LANG_MODEL_DETECTION_OBJECTS() {
        for(int i = 0; i < stuFeatureVector.length; i++){
            stuFeatureVector[i] = new NET_FEATURE_VECTORS_INFO();
        }
    }
}

