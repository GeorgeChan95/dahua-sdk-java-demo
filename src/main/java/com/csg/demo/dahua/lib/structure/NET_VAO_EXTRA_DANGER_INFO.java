package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 危险品号牌信息
*/
public class NET_VAO_EXTRA_DANGER_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 危险品号牌区域框,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_RECT_EX}
    */
    public NET_RECT_EX      stuBoundingBox = new NET_RECT_EX();
    /**
     * 种类，用于区分国内海外危险品车牌，取值如下[MainLandDangerous#国内,OverseasDangerous#海外]
    */
    public byte[]           szCategory = new byte[32];
    /**
     * 危险品号牌内容 上下两层的字符中间加空格，例如22 1234 ，只有一层的直接输出，例如1234
    */
    public byte[]           szText = new byte[32];
    /**
     * 危险品号牌转义，转义表格见国标GB13392-2023
    */
    public byte[]           szNumberMeanings = new byte[1024];
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_VAO_EXTRA_DANGER_INFO() {
    }
}

