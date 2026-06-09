package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * Invite事件自定义信息
*/
public class TALKING_INVITE_CUSTOM_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 开包台ip
    */
    public byte[]           szAddress = new byte[64];
    /**
     * 保留字段
    */
    public byte[]           szReserved = new byte[1024];

    public TALKING_INVITE_CUSTOM_INFO() {
    }
}

