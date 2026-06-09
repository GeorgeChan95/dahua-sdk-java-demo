package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_SetConfigDeviceDiscovery 接口输入参数
*/
public class NET_IN_SETCONFIG_DEVICE_DISCOVERY extends NetSDKLib.SdkStructure
{
    public int              dwSize;
    /**
     * 接口子类型,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.EM_NET_DEVICE_DISCOVERY_SUBCLASSID_TYPE}
    */
    public int              emSubClassID;
    /**
     * 希望修改的设备MAC
    */
    public byte[]           szMac = new byte[18];
    /**
     * 字节补齐
    */
    public byte[]           szReserved = new byte[6];
    /**
     * 用户名
    */
    public byte[]           szUsername = new byte[64];
    /**
     * 密码
    */
    public byte[]           szPassword = new byte[64];
    /**
     * IP等配置信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_DEVICE_DISCOVERY_CONFIG}
    */
    public NET_DEVICE_DISCOVERY_CONFIG stuDevConfig = new NET_DEVICE_DISCOVERY_CONFIG();

    public NET_IN_SETCONFIG_DEVICE_DISCOVERY() {
        this.dwSize = this.size();
    }
}

