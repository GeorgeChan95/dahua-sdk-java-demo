package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 鞋子检测结果
*/
public class NET_WORK_CLOTHES_SHOES_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 鞋子颜色
    */
    public byte[]           szShoesColor = new byte[16];
    /**
     * 鞋子检测结果,0-合规,1-不合规,2-未知
    */
    public short            nHasLegalShoes;
    /**
     * 鞋子款式,0-未知,1-凉鞋,2-皮鞋,3-运动鞋,4-靴子,5-拖鞋,6-休闲鞋,7-其他,8-鞋套,9-劳保鞋
    */
    public short            nShoesType;
    /**
     * 0-未知,1-未穿ShoesType对应款式鞋子,2-穿了ShoesType对应款式鞋子
    */
    public short            nHasShoes;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[26];

    public NET_WORK_CLOTHES_SHOES_INFO() {
    }
}

