package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 不同事件对应的自定义报警id列表
*/
public class NET_ALARM_ID_LIST extends NetSDKLib.SdkStructure
{
    /**
     * 事件Code
    */
    public int              dwCode;
    /**
     * 固定事件对应的自定义报警id列表个数
    */
    public int              nAlarmIDsNum;
    /**
     * 固定事件对应的自定义报警id列表
    */
    public int[]            nAlarmIDs = new int[128];
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[64];

    public NET_ALARM_ID_LIST() {
    }
}

