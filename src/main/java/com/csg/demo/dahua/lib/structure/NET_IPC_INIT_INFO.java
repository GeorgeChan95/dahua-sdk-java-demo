package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * IPC信息
*/
public class NET_IPC_INIT_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 设备MAC地址
    */
    public byte[]           szMac = new byte[18];
    /**
     * 字节对齐
    */
    public byte[]           byReserved = new byte[6];
    /**
     * 设备ip地址
    */
    public byte[]           szIp = new byte[16];
    /**
     * 初始化方式,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.NET_EM_NET_MODE}
    */
    public int              emNetMode;
    /**
     * 接入设备协议类型,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.NET_EM_PROTOCOL}
    */
    public int              emProtocol;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_IPC_INIT_INFO() {
    }
}

