package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 特征值信息
*/
public class NET_FEATURE_VECTORS_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 特征值在二进制数据块中的偏移
    */
    public int              nOffset;
    /**
     * 特征值长度，单位:字节
    */
    public int              nLength;
    /**
     * 特征版本版本号
    */
    public byte[]           szFeatureVersion = new byte[32];
    /**
     * 用于标识特征值是否加密
    */
    public int              bFeatureEnc;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[252];

    public NET_FEATURE_VECTORS_INFO() {
    }
}

