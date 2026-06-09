package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 屏幕语音参数设置
*/
public class NET_SMALL_DISPLAY_VOICE_MES extends NetSDKLib.SdkStructure
{
    /**
     * 播报语音内容列表个数
    */
    public int              nVoiceObjectItermsNum;
    /**
     * 事件触发预警时,语音播报内容列表个数
    */
    public int              nVoiceDataItermsNum;
    /**
     * 播报语音内容列表,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VOICE_OBJECT_ITERMS}
    */
    public NET_VOICE_OBJECT_ITERMS[] stuVoiceObjectIterms = new NET_VOICE_OBJECT_ITERMS[8];
    /**
     * 事件触发预警时,语音播报内容列表,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VOICE_DATA_ITERMS}
    */
    public NET_VOICE_DATA_ITERMS[] stuVoiceDataIterms = new NET_VOICE_DATA_ITERMS[8];
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_SMALL_DISPLAY_VOICE_MES() {
        for(int i = 0; i < stuVoiceObjectIterms.length; i++){
            stuVoiceObjectIterms[i] = new NET_VOICE_OBJECT_ITERMS();
        }
        for(int i = 0; i < stuVoiceDataIterms.length; i++){
            stuVoiceDataIterms[i] = new NET_VOICE_DATA_ITERMS();
        }
    }
}

