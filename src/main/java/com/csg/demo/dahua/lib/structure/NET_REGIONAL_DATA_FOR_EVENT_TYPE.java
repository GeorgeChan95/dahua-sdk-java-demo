package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 节目内的区域内容,按照事件类型配置
*/
public class NET_REGIONAL_DATA_FOR_EVENT_TYPE extends NetSDKLib.SdkStructure
{
    /**
     * 事件类型名称,NoneEvent:默认;TrafficOverSpeed:超速;TrafficJunction:卡口
    */
    public byte[]           szEventType = new byte[32];
    /**
     * 节目内区域内容,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_DISPLAY_SHOW_REGIONAL_DATA}
    */
    public NET_DISPLAY_SHOW_REGIONAL_DATA[] stuRegionalData = new NET_DISPLAY_SHOW_REGIONAL_DATA[8];
    /**
     * 节目内区域内容个数
    */
    public int              nRegionalDataNum;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1020];

    public NET_REGIONAL_DATA_FOR_EVENT_TYPE() {
        for(int i = 0; i < stuRegionalData.length; i++){
            stuRegionalData[i] = new NET_DISPLAY_SHOW_REGIONAL_DATA();
        }
    }
}

