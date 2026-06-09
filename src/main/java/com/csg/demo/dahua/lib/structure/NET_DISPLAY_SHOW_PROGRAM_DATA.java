package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 显示屏节目数据
*/
public class NET_DISPLAY_SHOW_PROGRAM_DATA extends NetSDKLib.SdkStructure
{
    /**
     * 节目内区域个数
    */
    public int              nRegionalNumber;
    /**
     * 节目内区域内容个数
    */
    public int              nRegionalDataNum;
    /**
     * 节目内区域内容,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_DISPLAY_SHOW_REGIONAL_DATA}
    */
    public NET_DISPLAY_SHOW_REGIONAL_DATA[] stuRegionalData = new NET_DISPLAY_SHOW_REGIONAL_DATA[8];
    /**
     * 节目内的区域内容,按照事件类型配置,内存由用户申请,大小为sizeof(NET_REGIONAL_DATA_FOR_EVENT_TYPE)*nMaxRegionalDataForEventTypeNum,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_REGIONAL_DATA_FOR_EVENT_TYPE}
    */
    public Pointer          pstuRegionalDataForEventType;
    /**
     * 显示屏节目数据最大个数
    */
    public int              nMaxRegionalDataForEventTypeNum;
    /**
     * 显示屏节目数据实际个数
    */
    public int              nRetRegionalDataForEventTypeNum;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_DISPLAY_SHOW_PROGRAM_DATA() {
        for(int i = 0; i < stuRegionalData.length; i++){
            stuRegionalData[i] = new NET_DISPLAY_SHOW_REGIONAL_DATA();
        }
    }
}

