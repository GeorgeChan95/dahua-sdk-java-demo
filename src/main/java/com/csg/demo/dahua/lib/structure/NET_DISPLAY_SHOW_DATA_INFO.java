package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 显示更新请求相关参数
*/
public class NET_DISPLAY_SHOW_DATA_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 显示屏节目数据数组（最大支持2个节目）,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_PROGRAM_REGIONAL_DATA}
    */
    public NET_PROGRAM_REGIONAL_DATA[] stuProgramData = new NET_PROGRAM_REGIONAL_DATA[2];
    /**
     * 显示器节目个数
    */
    public int              nProgramNumber;
    /**
     * 预留字段
    */
    public byte[]           szReserved = new byte[1020];

    public NET_DISPLAY_SHOW_DATA_INFO() {
        for(int i = 0; i < stuProgramData.length; i++){
            stuProgramData[i] = new NET_PROGRAM_REGIONAL_DATA();
        }
    }
}

