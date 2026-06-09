package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 防假等级和防假模式映射关系
*/
public class NET_FACE_ANTIFAKE_STRATEGY_MAP_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 防假等级
    */
    public int              nLevel;
    /**
     * 防假模式，每位代表一种方式, 0x01 是否开启活体 0x02 是否开启口罩活体
    */
    public int              nMode;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_FACE_ANTIFAKE_STRATEGY_MAP_INFO() {
    }
}

