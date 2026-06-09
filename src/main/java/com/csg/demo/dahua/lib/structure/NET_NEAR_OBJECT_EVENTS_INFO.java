package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 目标事件列表
*/
public class NET_NEAR_OBJECT_EVENTS_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 通道号，从0开始
    */
    public int              nChannel;
    /**
     * 目标轨迹ID
    */
    public int              nObjectID;
    /**
     * 目标类型 0 未知, 1 人, 2 车, 3 动物, 4 非机动车
    */
    public int              nObjectType;
    /**
     * 目标事件小图
    */
    public byte[]           szObjectUrl = new byte[256];
    /**
     * 物体进入画面时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuEnterTime = new NetSDKLib.NET_TIME();
    /**
     * 物体离开画面时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuLeaveTime = new NetSDKLib.NET_TIME();
    /**
     * 目标关联的录像片段在录像文件列表中的索引位置，从0开始。
    */
    public int              nRecordIndex;
    /**
     * 唯一标识一个事件
    */
    public long             nEventUUID;
    /**
     * 是否收藏. 0-未知 1-未收藏 2-已收藏
    */
    public int              nFavorite;
    /**
     * 字节对齐
    */
    public byte[]           szReserved1 = new byte[4];
    /**
     * 全景大图图片路径
    */
    public byte[]           szSceneUrl = new byte[256];
    /**
     * 目标包围盒。矩形信息，8192坐标系,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_RECT_EX}
    */
    public NET_RECT_EX      stuBoundingBox = new NET_RECT_EX();
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[744];

    public NET_NEAR_OBJECT_EVENTS_INFO() {
    }
}

