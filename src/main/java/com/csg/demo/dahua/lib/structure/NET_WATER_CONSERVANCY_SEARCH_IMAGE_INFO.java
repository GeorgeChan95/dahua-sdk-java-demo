package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 抠图信息
*/
public class NET_WATER_CONSERVANCY_SEARCH_IMAGE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 抠图长度
    */
    public int              nLength;
    /**
     * 抠图路径
    */
    public byte[]           szFilePath = new byte[128];
    /**
     * 预留字段
    */
    public byte[]           szReserved = new byte[892];

    public NET_WATER_CONSERVANCY_SEARCH_IMAGE_INFO() {
    }
}

