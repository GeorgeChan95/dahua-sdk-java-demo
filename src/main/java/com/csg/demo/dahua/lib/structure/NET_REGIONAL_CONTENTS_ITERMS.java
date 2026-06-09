package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 报警事件触发时,屏幕显示内容列表
*/
public class NET_REGIONAL_CONTENTS_ITERMS extends NetSDKLib.SdkStructure
{
    /**
     * 自定义显示内容
    */
    public BYTE_ARRAY_128[] szCustomContent = new BYTE_ARRAY_128[8];
    /**
     * 自定义显示内容个数
    */
    public int              nCustomContentNum;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[204];

    public NET_REGIONAL_CONTENTS_ITERMS() {
        for(int i = 0; i < szCustomContent.length; i++){
            szCustomContent[i] = new BYTE_ARRAY_128();
        }
    }
}

