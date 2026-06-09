package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 目标事件列表
*/
public class NET_OBJECT_EVENTS_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 记录唯一编号
    */
    public int              nRecNo;
    /**
     * 通道号，从0开始
    */
    public int              nChannel;
    /**
     * 目标轨迹ID
    */
    public int              nObjectID;
    /**
     * 目标类型
    */
    public int              nObjectType;
    /**
     * 目标轨迹所关联的智能大类值
    */
    public byte[]           szSchemaID = new byte[32];
    /**
     * 目标事件小图
    */
    public byte[]           szObjectUrl = new byte[256];
    /**
     * 本地系统utc时间（0时区），单位：秒
    */
    public long             nUTCSystemTime;
    /**
     * 事件触发utc时间（0时区），单位：秒
    */
    public long             nUTCEventTime;
    /**
     * 物体进入画面时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuEnterTimeRealUTC = new NetSDKLib.NET_TIME();
    /**
     * 物体离开画面时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuLeaveTimeRealUTC = new NetSDKLib.NET_TIME();
    /**
     * 关联记录编号
    */
    public int              nLinkID;
    /**
     * 是否收藏. 0-未知 1-未收藏 2-已收藏
    */
    public int              nFavorite;
    /**
     * 唯一标识一个事件，用于确定抓图的原始事件信息
    */
    public long             nEventUUID;
    /**
     * 全景大图图片路径
    */
    public byte[]           szSceneUrl = new byte[256];
    /**
     * 目标包围盒。矩形信息，8192坐标系,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_RECT_EX}
    */
    public NET_RECT_EX      stuBoundingBox = new NET_RECT_EX();
    /**
     * 目标关联的录像片段在录像文件列表中的索引位置,从0开始,-1表示无此字段
    */
    public int              nRecordIndex;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[740];

    public NET_OBJECT_EVENTS_INFO() {
    }
}

