package com.csg.demo.dahua.lib.structure;

import com.csg.demo.dahua.lib.NetSDKLib;

/**
 * @author 251823
 * @description 事件公共字段扩展结构体: 该结构体仅用于 普通报警事件类型(不带图报警事件类型) 和 智能报警事件类型(带图报警事件类型)
 * 的公共字段扩展使用
 * @date 2022/07/22 09:37:13
 */
public class NET_EVENT_INFO_EXTEND extends NetSDKLib.SdkStructure {
    /**
     * RealUTC 是否有效，bRealUTC 为 TRUE 时，用 stuRealUTC，否则 stuRealUTC 字段无效(用原事件结构体中的 事件发生时间/事件触发时间(UTC) 字段)
     */
    public int              bRealUTC;
    /**
     * 仅用于字节对齐
     */
    public byte[]           byReserved = new byte[4];
    /**
     * 事件发生的时间(标准UTC时间(不带时区夏令时偏差)), 由于事件的UTC时间在产品线之间使用的差异性, 故增加RealUTC作为标准UTC时间, 平台在收到事件解析首优先级是RealUTC, 其次是UTC.
     */
    public NET_TIME_EX      stuRealUTC = new NET_TIME_EX();
    /**
     * 事件类型是否有效
     */
    public int              bIsEventsTypeValid;
    /**
     * 事件类型, bIsEventsTypeValid为TRUE时有效, 0:正常抓图事件, 1:邮件联动抓图事件(图片通过第二路抓图码流上来，和正常抓图的图片可以不一样)
     */
    public int              szEventsType;
    /**
     * 文件传输状态, -1: 未知, 0-实时数据下载，1-离线数据下载, 2-离线数据传输完成, 3-离线数据传输中断 4-手动上传数据传输
    */
    public int              nTransfer;
    /**
     * 事件发生的时间扩展(标准UTC时间(不带时区夏令时偏差)).,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_TIME_EX2}
    */
    public NET_TIME_EX2     stuRealUTCEx = new NET_TIME_EX2();
    /**
     * 保留字节
     */
    public byte[]           szReserved = new byte[968];

    public NET_EVENT_INFO_EXTEND() {
    }

    @Override
    public String toString() {
        return "NET_EVENT_INFO_EXTEND{" +
                "bRealUTC=" + bRealUTC +
                ", stuRealUTC=" + stuRealUTC +
                ", bIsEventsTypeValid=" + bIsEventsTypeValid +
                ", szEventsType=" + szEventsType +
                '}';
    }
}

