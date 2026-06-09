package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 下发查询类别信息
*/
public class NET_LARGE_MODE_CLASS extends NetSDKLib.SdkStructure
{
    /**
     * 下发详细查询类别，一个元素为大类class(为空时查询定时抓图)查询；两个元素为大类class+事件code查询；
    */
    public BYTE_ARRAY_32[]  szClassList = new BYTE_ARRAY_32[2];
    /**
     * 下发详细查询类别数量
    */
    public int              nClassListNum;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1020];

    public NET_LARGE_MODE_CLASS() {
        for(int i = 0; i < szClassList.length; i++){
            szClassList[i] = new BYTE_ARRAY_32();
        }
    }
}

