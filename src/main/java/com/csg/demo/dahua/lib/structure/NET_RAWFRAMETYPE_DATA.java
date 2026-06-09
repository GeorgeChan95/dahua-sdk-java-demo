package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * YUV数据格式能力集
*/
public class NET_RAWFRAMETYPE_DATA extends NetSDKLib.SdkStructure
{
    /**
     * 实际返回的数据格式个数
    */
    public int              nListNum;
    /**
     * 支持的YUV数据格式列表
    */
    public BYTE_ARRAY_8[]   szList = new BYTE_ARRAY_8[16];
    public byte[]           byReserved = new byte[1024];

    public NET_RAWFRAMETYPE_DATA() {
        for(int i = 0; i < szList.length; i++){
            szList[i] = new BYTE_ARRAY_8();
        }
    }
}

