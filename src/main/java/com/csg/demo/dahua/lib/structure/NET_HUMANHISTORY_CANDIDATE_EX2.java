package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 人体历史库以图搜图主动上报的候选人信息扩展
*/
public class NET_HUMANHISTORY_CANDIDATE_EX2 extends NetSDKLib.SdkStructure
{
    /**
     * 目标图片文件路径
    */
    public byte[]           szPicFilePath = new byte[256];
    /**
     * 事件关联ID
    */
    public byte[]           szSourceID = new byte[32];
    /**
     * 预留字节
    */
    public byte[]           bReserved = new byte[256];

    public NET_HUMANHISTORY_CANDIDATE_EX2() {
    }
}

