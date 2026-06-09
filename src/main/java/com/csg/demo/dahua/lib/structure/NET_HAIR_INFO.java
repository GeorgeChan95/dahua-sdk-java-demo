package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 发型检测结果
*/
public class NET_HAIR_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 发型, 0-未知，1-短发，2-长发，3-马尾，4-盘发，5-头部被遮挡，6-光头，7-秃顶，8-戴帽子长发外露
    */
    public int              nHairStyle;
    /**
     * 发型检测结果 , 0-合规 1-不合规 2-未知
    */
    public int              nHasLegalHair;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[24];

    public NET_HAIR_INFO() {
    }
}

