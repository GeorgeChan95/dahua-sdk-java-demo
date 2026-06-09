package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 配置临时密码、管理员密码和公共密码
*/
public class NET_ACCESS_PASSWORD_COMM extends NetSDKLib.SdkStructure
{
    /**
     * 记录编号 只读
    */
    public int              nRecNo;
    /**
     * 开始时间 (UTC 秒数)
    */
    public int              nCreateTime;
    /**
     * 用户 ID
    */
    public byte[]           szID = new byte[9];
    /**
     * 对齐预留
    */
    public byte[]           szRes = new byte[7];
    /**
     * 开门密码
    */
    public byte[]           szOpenDoorPassword = new byte[64];
    /**
     * 报警密码
    */
    public byte[]           szAlarmPassword = new byte[64];
    /**
     * 门权限数量
    */
    public int              nDoorsNum;
    /**
     * 门权限
    */
    public int[]            nDoors = new int[16];
    /**
     * 门权限对应时间段数量
    */
    public int              nTimeSectionsNum;
    /**
     * 门权限对应时间段
    */
    public int[]            nTimeSections = new int[64];
    /**
     * 室内机关联的门口号码
    */
    public byte[]           szVTOPosition = new byte[16];
    /**
     * 开始有效期
    */
    public byte[]           szValidDateStart = new byte[16];
    /**
     * 截止有效期
    */
    public byte[]           szValidDateEnd = new byte[16];
    /**
     * 所属网关
    */
    public byte[]           szOriginSmartGateWay = new byte[32];
    /**
     * 密码有效次数
    */
    public int              nValidCounts;
    /**
     * 身份证号码
    */
    public byte[]           szCitizenIDNo = new byte[20];
    /**
     * 保留字节
    */
    public byte[]           byReserved = new byte[1024];

    public NET_ACCESS_PASSWORD_COMM() {
    }
}

