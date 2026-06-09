package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 空间占有率统计信息
*/
public class NET_SPACE_OCCUPY_RATIO_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 对应ID的空间占有率
    */
    public double           dbSpaceOccupyRatio;
    /**
     * 空间占有率统计区域的ID
    */
    public int              nID;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[60];

    public NET_SPACE_OCCUPY_RATIO_INFO() {
    }
}

