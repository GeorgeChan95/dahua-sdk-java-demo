package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 公共信息扩展
*/
public class EVENT_COMM_INFO_EX extends NetSDKLib.SdkStructure
{
    /**
     * 根据功能划分的车辆类型
    */
    public byte[]           szVehicleTypeByFunc = new byte[32];
    /**
     * 标准车辆类型
    */
    public byte[]           szStandardVehicleType = new byte[32];
    /**
     * 关联规则的检测区域名称
    */
    public byte[]           szRegionName = new byte[128];
    /**
     * 关联规则的区域下标索引
    */
    public int              nRegionIndex;
    /**
     * 预留字节
    */
    public byte[]           bReserved = new byte[1916];

    public EVENT_COMM_INFO_EX() {
    }
}

