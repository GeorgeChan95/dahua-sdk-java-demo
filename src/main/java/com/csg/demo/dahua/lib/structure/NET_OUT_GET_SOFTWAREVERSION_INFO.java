package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_GetSoftwareVersion 出参
*/
public class NET_OUT_GET_SOFTWAREVERSION_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 软件版本
    */
    public byte[]           szVersion = new byte[64];
    /**
     * 日期,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuBuildDate = new NetSDKLib.NET_TIME();
    /**
     * web软件信息
    */
    public byte[]           szWebVersion = new byte[16];
    /**
     * 安全基线版本
    */
    public byte[]           szSecurityVersion = new byte[64];
    /**
     * 返回的外设数量
    */
    public int              nPeripheralNum;
    /**
     * 设备的外设软件版本,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_PERIPHERAL_VERSIONS}
    */
    public NET_PERIPHERAL_VERSIONS[] stuPeripheralVersions = new NET_PERIPHERAL_VERSIONS[32];
    /**
     * 算法训练对外代号
    */
    public byte[]           szAlgorithmTrainingVersion = new byte[64];
    /**
     * 设备mobileWeb上云匹配版本号
    */
    public byte[]           szMobileWebVersion = new byte[128];
    /**
     * 程序编译UTC时间（BuildDate设备实现错误，没有精确到秒）。设备自动升级必填
    */
    public long             nBuildTimeUTC;

    public NET_OUT_GET_SOFTWAREVERSION_INFO() {
        this.dwSize = this.size();
        for(int i = 0; i < stuPeripheralVersions.length; i++){
            stuPeripheralVersions[i] = new NET_PERIPHERAL_VERSIONS();
        }
    }
}

