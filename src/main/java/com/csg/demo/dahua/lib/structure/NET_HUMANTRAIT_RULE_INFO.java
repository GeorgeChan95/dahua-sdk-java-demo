package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 事件类型EVENT_IVS_HUMANTRAIT(人员检测规则)对应的规则配置
*/
public class NET_HUMANTRAIT_RULE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 支持的目标检测类型个数
    */
    public int              nHumanFaceTypeNum;
    /**
     * 支持的目标检测类型列表,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.NET_ANALYSE_HUMANFACE_TYPE}
    */
    public int[]            emHumanFaceType = new int[8];
    /**
     * 最短触发时间,单位：秒
    */
    public int              nMinDuration;
    /**
     * 触发报警的目标个数
    */
    public int              nTriggerTargets;
    /**
     * 灵敏度,越高越容易检测, 0-10
    */
    public int              nSensitivity;
    /**
     * 是否使用尺寸过滤器
    */
    public int              bSizeFileter;
    /**
     * 规则特定的尺寸过滤器，提高判断精度,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_CFG_SIZEFILTER_INFO}
    */
    public NET_CFG_SIZEFILTER_INFO stuSizeFileter = new NET_CFG_SIZEFILTER_INFO();
    /**
     * 是否开启目标属性识别
    */
    public int              bFeatureEnable;
    /**
     * 在目标属性开启前提下，如果目标图像质量太差，是否不上报属性  true-图像太差不上报属性;false-图像很差也上报属性(可能会非常不准，影响用户体验)
    */
    public int              bFeatureFilter;
    /**
     * 目标图片质量阈值,和bFeatureFilter一起使用
    */
    public int              nMinQuality;
    /**
     * 需要检测的目标属性个数
    */
    public int              nFaceFeatureNum;
    /**
     * 需检测的目标属性,参见枚举定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_EM_FACEFEATURE_TYPE}
    */
    public int[]            emFaceFeatureType = new int[32];
    /**
     * 检测区顶点数
    */
    public int              nDetectRegionPoint;
    /**
     * 检测区,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.POINTCOORDINATE}
    */
    public NetSDKLib.POINTCOORDINATE[] stuDetectRegion = new NetSDKLib.POINTCOORDINATE[20];
    /**
     * 排除区域数
    */
    public int              nExcludeRegionNum;
    /**
     * 排除区域,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_POLY_POINTS}
    */
    public NetSDKLib.NET_POLY_POINTS[] stuExcludeRegion = new NetSDKLib.NET_POLY_POINTS[10];
    /**
     * 是否进行目标抓图:  TRUE:进行目标抓图, FALSE：不进行目标抓图
    */
    public int              bFaceSnapEnable;
    /**
     * 是否提取人体特征向量
    */
    public int              bFeatureExtractEnable;
    /**
     * 合规报警规则,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_COMPLIANT_INFO}
    */
    public NET_COMPLIANT_INFO stuCompliant = new NET_COMPLIANT_INFO();
    /**
     * 是否开启人体属性识别, TRUE-开启 FALSE-关闭
    */
    public int              bHumanFeatureEnable;
    /**
     * 人体属性的个数
    */
    public int              nHumanFeatureList;
    /**
     * 配置要检测哪些人体属性,开启人体属性时有效(bHumanFeatureEnable为TRUE),目前支持的取值范围见如下能力描述,,*********能力描述 Start***************************************************************************************************************************************************************,,,*********能力描述 End*****************************************************************************************************************************************************************,
    */
    public BYTE_ARRAY_16[]  szHumanFeatureList = new BYTE_ARRAY_16[32];
    /**
     * 是否提取目标特征向量
    */
    public int              bFaceFeatureExtractEnable;

    public NET_HUMANTRAIT_RULE_INFO() {
        this.dwSize = this.size();
        for(int i = 0; i < stuDetectRegion.length; i++){
            stuDetectRegion[i] = new NetSDKLib.POINTCOORDINATE();
        }
        for(int i = 0; i < stuExcludeRegion.length; i++){
            stuExcludeRegion[i] = new NetSDKLib.NET_POLY_POINTS();
        }
        for(int i = 0; i < szHumanFeatureList.length; i++){
            szHumanFeatureList[i] = new BYTE_ARRAY_16();
        }
    }
}

