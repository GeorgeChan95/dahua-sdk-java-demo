package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_QueryDevInfo , NET_QUERY_DEV_REMOTE_DEVICE_INFO 查询远程设备信息输入参数
*/
public class NET_IN_GET_DEVICE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 用户使用该结构体时,dwSize 需赋值为 sizeof(NET_IN_GET_DEVICE_INFO)
    */
    public int              dwSize;
    /**
     * 设备ID
    */
    public byte[]           szDevice = new byte[128];
    /**
     * 设备地址
    */
    public byte[]           szAttributeIP = new byte[32];
    /**
     * 设备端口
    */
    public int              nAttributePort;
    /**
     * 用户名
    */
    public byte[]           szAttributeUsername = new byte[128];
    /**
     * 密码
    */
    public byte[]           szAttributePassword = new byte[128];
    /**
     * 协议
    */
    public byte[]           szAttributeManufacturer = new byte[128];

    public NET_IN_GET_DEVICE_INFO() {
        this.dwSize = this.size();
    }
}

