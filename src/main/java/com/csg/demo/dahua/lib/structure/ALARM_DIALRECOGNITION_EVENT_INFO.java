package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * @brief 事件类型DH_ALARM_DIALRECOGNITION_EVENT （仪表检测事件) 对应的数据块描述信息
*/
public class ALARM_DIALRECOGNITION_EVENT_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 视频通道号,从0开始
    */
    public int              nChannelID;
    /**
     * 预置点ID,如果普通IPC则为0
    */
    public int              nPresetID;
    /**
     * 仪表类型,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.EM_INSTRUMENT_TYPE}
    */
    public int              emType;
    /**
     * 告警类型：0-该字段无效;1-数值异常;2-定时上报; 3-高阀值报警; 4-低阀值报警
    */
    public int              nAlarmType;
    /**
     * 任务ID.添加时设备端生成
    */
    public byte[]           szTaskID = new byte[64];
    /**
     * 检测结果，根据Type的不同，格式也不同
    */
    public byte[]           szDialResult = new byte[2048];
    /**
     * 仪表检测具体子类型
    */
    public byte[]           szDialSubType = new byte[32];
    /**
     * 报警上限阈值
    */
    public float            fUpperThreshold;
    /**
     * 报警下限阈值
    */
    public float            fLowerThreshold;
    /**
     * 包围盒,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_RECT}
    */
    public NET_RECT[]       stuBoundingBox = new NET_RECT[128];
    /**
     * 包围盒个数
    */
    public int              nRetBoundingBoxNum;
    /**
     * 目标置信度列表中元素数目
    */
    public int              nConfidenceListNum;
    /**
     * 目标置信度列表，0~100，越大表示越高
    */
    public int[]            nConfidenceList = new int[128];
    /**
     * 单位
    */
    public byte[]           szUnit = new byte[8];
    /**
     * 区域id数组
    */
    public int[]            nRegionIDList = new int[64];
    /**
     * 区域id数组中元素数目
    */
    public int              nRegionIDListNum;
    /**
     * 刀臂夹角列表中元素数目
    */
    public int              nAngleListNum;
    /**
     * 刀臂夹角列表，目前适用刀闸类型，每个元素代表一个刀闸
    */
    public float[]          fAngleList = new float[128];
    /**
     * 国网标准协议的仪表类型  "isolator": 刀闸 "switch"：开关 "meter"：表计 "light"：指示灯
    */
    public byte[]           szGWDialType = new byte[32];
    /**
     * 自定义结果上报，区别于协议定好的结果上报字段DialResult
    */
    public byte[]           szDialCustomResult = new byte[128];
    /**
     * 报警颜色  Red"：红色 "Blue"：蓝色 "Orange"：橙色 "Green"：绿色 "White"：白色
    */
    public byte[]           szAlarmColor = new byte[16];
    /**
     * 事件触发的预置点名称
    */
    public byte[]           szPresetName = new byte[64];
    /**
     * RealUTC 是否有效,bRealUTC 为 TRUE 时,用stuRealUTC,否则 stuRealUTC 字段无效
    */
    public int              bRealUTC;
    /**
     * 事件发生的时间(标准UTC时间(不带时区夏令时偏差)),参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME_EX}
    */
    public NetSDKLib.NET_TIME_EX stuRealUTC = new NetSDKLib.NET_TIME_EX();
    /**
     * 多个检测到的仪表目标信息，内存由NetSDK申请释放,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_DIAL_OBJECT_INFO}
    */
    public Pointer          pstuDialObjects;
    /**
     * 检测到的仪表目标信息有效个数，最多100个
    */
    public int              nDialObjectsNum;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1020];

    public ALARM_DIALRECOGNITION_EVENT_INFO() {
        for(int i = 0; i < stuBoundingBox.length; i++){
            stuBoundingBox[i] = new NET_RECT();
        }
    }
}

