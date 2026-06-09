package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 代理信息
*/
public class NET_NOTIFY_CENTRA_PROXY_ACTION_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 代理功能,0:代理开锁功能；1:代理视频预览功能 2:访客机数据传输功能
    */
    public int              nAction;
    /**
     * 事务ID,由设备产生唯一ID
    */
    public byte[]           szTransID = new byte[36];
    /**
     * 目的设备号码
    */
    public byte[]           szDstDevCallNo = new byte[32];
    /**
     * 代理视频预览功能才有效，表示门通道编号
    */
    public int              nDoorChannel;
    /**
     * 访客信息，Action为访客机数据传输时有效, 由SDK申请释放内存,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_CENTRA_PROXY_DETAIL_INFO}
    */
    public Pointer          pstDetailInfo;
    /**
     * 访客信息，pstDetailInfo字段废弃,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_CB_CENTRA_PROXY_DETAIL_INFO}
    */
    public Pointer          pstCbDetailInfo;
    /**
     * 保留字段
    */
    public byte[]           szResvered = new byte[1020-2*NetSDKLib.POINTERSIZE];

    public NET_NOTIFY_CENTRA_PROXY_ACTION_INFO() {
    }
}

