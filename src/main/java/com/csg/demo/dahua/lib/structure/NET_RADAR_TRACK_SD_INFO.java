package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 被跟踪轨迹对应的球机信息
*/
public class NET_RADAR_TRACK_SD_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 被跟踪轨迹对应的球机ip
    */
    public byte[]           szSDIP = new byte[32];
    /**
     * 球机距离目标距离的排序值，数值越小，表示球机离目标越近，1值最近
    */
    public int              nSortNumber;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[124];

    public NET_RADAR_TRACK_SD_INFO() {
    }
}

