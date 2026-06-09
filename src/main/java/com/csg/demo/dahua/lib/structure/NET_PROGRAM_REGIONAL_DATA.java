package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 屏幕节目内区域数据
*/
public class NET_PROGRAM_REGIONAL_DATA extends NetSDKLib.SdkStructure
{
    /**
     * 节目号
    */
    public int              nProgramNo;
    /**
     * 节目内区域个数
    */
    public int              nRegionalNumber;
    /**
     * 节目内区域内容数组（最大支持4个区域）,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_REGIONAL_DATA}
    */
    public NET_REGIONAL_DATA[] stuRegionalData = new NET_REGIONAL_DATA[4];
    /**
     * 预留字段
    */
    public byte[]           szReserved = new byte[1024];

    public NET_PROGRAM_REGIONAL_DATA() {
        for(int i = 0; i < stuRegionalData.length; i++){
            stuRegionalData[i] = new NET_REGIONAL_DATA();
        }
    }
}

