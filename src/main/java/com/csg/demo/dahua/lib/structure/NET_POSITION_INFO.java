package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 预置点的坐标和放大倍数
*/
public class NET_POSITION_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 水平坐标
    */
    public int              nPosX;
    /**
     * 垂直坐标
    */
    public int              nPosY;
    /**
     * 放大倍数
    */
    public int              nScale;
    /**
     * 预留字段
    */
    public byte[]           szReserved = new byte[28];

    public NET_POSITION_INFO() {
    }
}

