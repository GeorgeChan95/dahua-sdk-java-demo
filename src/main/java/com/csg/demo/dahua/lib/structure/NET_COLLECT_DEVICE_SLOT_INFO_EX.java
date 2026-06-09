package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 采集设备的槽位扩展信息
*/
public class NET_COLLECT_DEVICE_SLOT_INFO_EX extends NetSDKLib.SdkStructure
{
    /**
     * 手持使用的警员ID
    */
    public byte[]           szPoliceID = new byte[64];
    /**
     * 手持升级前版本
    */
    public byte[]           szPreviousVersion = new byte[64];
    /**
     * 手持升级后当前版本
    */
    public byte[]           szCurrentVersion = new byte[64];
    /**
     * 升级失败原因，"Invalid": 升级包校验失败 "Failed": 升级失败 "NetworkError": 网络异常
    */
    public byte[]           szErrorMsg = new byte[64];
    /**
     * 开始升级时间，UTC秒数
    */
    public long             nStartTime;
    /**
     * 保留字节
    */
    public byte[]           szResvered = new byte[1016];

    public NET_COLLECT_DEVICE_SLOT_INFO_EX() {
    }
}

