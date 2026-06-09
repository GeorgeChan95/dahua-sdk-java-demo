package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * IP等配置信息
*/
public class NET_DEVICE_DISCOVERY_CONFIG extends NetSDKLib.SdkStructure
{
    /**
     * 私有协议服务端口
    */
    public int              nPort;
    /**
     * HTTP服务端口
    */
    public int              nHttpPort;
    /**
     * 是否使能DHCP
    */
    public int              bIPv4DhcpEnable;
    /**
     * IPv6是否使能Dhcp
    */
    public int              bIPv6DhcpEnable;
    /**
     * 修改后的IPv4地址
    */
    public byte[]           szIPv4Address = new byte[40];
    /**
     * 修改前IPv4地址
    */
    public byte[]           szIPv4AddressOld = new byte[40];
    /**
     * IPv4子网掩码
    */
    public byte[]           szIPv4SubnetMask = new byte[40];
    /**
     * IPv4默认网关
    */
    public byte[]           szIPv4DefaultGateway = new byte[40];
    /**
     * 修改后IPv6地址
    */
    public byte[]           szIPv6Address = new byte[40];
    /**
     * 修改前IPv6地址
    */
    public byte[]           szIPv6AddressOld = new byte[40];
    /**
     * IPv6默认网关
    */
    public byte[]           szIPv6DefaultGateway = new byte[40];
    /**
     * 网卡IP
    */
    public byte[]           szClientAddress = new byte[40];
    /**
     * 是否绑定IP,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.NET_EM_BIND_CLIENT_ADDRWAY}
    */
    public int              emBindClientAddrWay;
    /**
     * 保留字节
    */
    public byte[]           byReserved = new byte[1020];

    public NET_DEVICE_DISCOVERY_CONFIG() {
    }
}

