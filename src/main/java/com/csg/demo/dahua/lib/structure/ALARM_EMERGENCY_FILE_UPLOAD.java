package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 事件类型DH_ALARM_EMERGENCY_FILE_UPLOAD (MPT设备要求主动上传指定文件给平台事件) 对应的数据块描述信息
*/
public class ALARM_EMERGENCY_FILE_UPLOAD extends NetSDKLib.SdkStructure
{
    /**
     * 参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_EMERGENCY_FILE_INFO}
    */
    public NET_EMERGENCY_FILE_INFO[] stFileInfo = new NET_EMERGENCY_FILE_INFO[50];
    /**
     * 事件公共扩展字段结构体,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_EVENT_INFO_EXTEND}
    */
    public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND();
    /**
     * 保留字节
    */
    public byte[]           byReserved = new byte[512];

    public ALARM_EMERGENCY_FILE_UPLOAD() {
        for(int i = 0; i < stFileInfo.length; i++){
            stFileInfo[i] = new NET_EMERGENCY_FILE_INFO();
        }
    }
}

