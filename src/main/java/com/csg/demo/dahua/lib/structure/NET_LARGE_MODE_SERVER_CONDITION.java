package com.csg.demo.dahua.lib.structure;
import com.sun.jna.Pointer;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 查询过滤条件
*/
public class NET_LARGE_MODE_SERVER_CONDITION extends NetSDKLib.SdkStructure
{
    /**
     * 开始时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuStartTime = new NetSDKLib.NET_TIME();
    /**
     * 结束时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuEndTime = new NetSDKLib.NET_TIME();
    /**
     * 数据源,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.EM_LARGE_MODE_SERVER_DATA_SOURCE}
    */
    public int              emDataSource;
    /**
     * 事件名称个数
    */
    public int              nEventsNum;
    /**
     * 事件名称
    */
    public BYTE_ARRAY_32[]  szEvents = new BYTE_ARRAY_32[32];
    /**
     * 内容描述
    */
    public byte[]           szAlarmContent = new byte[512];
    /**
     * 通道
    */
    public int[]            nChannels = new int[512];
    /**
     * 通道个数
    */
    public int              nChannelsNum;
    /**
     * 是否下发相似度数组
    */
    public int              bUseSimilarityRange;
    /**
     * 相似度数组，是一个数组 [0] 表示最小值 [1] 表示最大值
    */
    public int[]            nSimilarityRange = new int[2];
    /**
     * 数据异步推送类型：0 比对全部完成后，分批推送搜索进度和结果数据; 1 比对过程实时推送进度，不推送比对结果数据，需要通过doFind协议主动获取结果数据;
    */
    public int              nNotifyType;
    /**
     * 查询类别数量
    */
    public int              nClassNum;
    /**
     * 下发查询类别信息,内存由用户申请释放,sizeof(NET_LARGE_MODE_CLASS)*nClassNum,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_LARGE_MODE_CLASS}
    */
    public Pointer          pstuClass;
    /**
     * 保留字段
    */
    public byte[]           szReserved = new byte[488-NetSDKLib.POINTERSIZE];

    public NET_LARGE_MODE_SERVER_CONDITION() {
        for(int i = 0; i < szEvents.length; i++){
            szEvents[i] = new BYTE_ARRAY_32();
        }
    }
}

