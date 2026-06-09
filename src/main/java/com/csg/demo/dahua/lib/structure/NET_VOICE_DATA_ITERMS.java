package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 事件触发预警时,语音播报内容列表
*/
public class NET_VOICE_DATA_ITERMS extends NetSDKLib.SdkStructure
{
    /**
     * 语音播报内容
    */
    public byte[]           szVoiceData = new byte[128];
    /**
     * 预警时,屏幕播报添加内容;Plate:车牌;Speed:速度;Distance:距离
    */
    public BYTE_ARRAY_32[]  szGeneralContent = new BYTE_ARRAY_32[32];
    /**
     * 播报添加内容个数
    */
    public int              nGeneralContentNum;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[204];

    public NET_VOICE_DATA_ITERMS() {
        for(int i = 0; i < szGeneralContent.length; i++){
            szGeneralContent[i] = new BYTE_ARRAY_32();
        }
    }
}

