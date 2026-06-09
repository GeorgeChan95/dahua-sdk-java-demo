package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 基于目标轨迹的录像查找功能
*/
public class NET_OBJECT_TRACK_SEARCH extends NetSDKLib.SdkStructure
{
    /**
     * 是否支持基于目标轨迹的录像查找功能
    */
    public int              bSupport;
    /**
     * 支持的目标类型列表个数
    */
    public int              nObjectTypeListCount;
    /**
     * 支持的目标类型列表, 目前支持如下类型: "Human": 人, "Vehicle": 车, "Animal"：动物, "NonMotor": 非机动车
    */
    public BYTE_ARRAY_32[]  szObjectTypeList = new BYTE_ARRAY_32[8];
    /**
     * 单次查询最大天数
    */
    public int              nMaxQueryDays;
    /**
     * 单次查询最大通道数
    */
    public int              nMaxQueryChannels;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_OBJECT_TRACK_SEARCH() {
        for(int i = 0; i < szObjectTypeList.length; i++){
            szObjectTypeList[i] = new BYTE_ARRAY_32();
        }
    }
}

