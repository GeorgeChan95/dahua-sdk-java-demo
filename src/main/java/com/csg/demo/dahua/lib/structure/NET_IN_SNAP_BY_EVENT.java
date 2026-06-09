package com.csg.demo.dahua.lib.structure;

import com.csg.demo.dahua.lib.NetSDKLib;
import com.csg.demo.dahua.lib.enumeration.EM_EVENT_IVS;
import com.csg.demo.dahua.lib.enumeration.EM_EVENT_IVS_TYPE;

/**
 * 按照事件类型抓图 入参
 * 接口 {@link NetSDKLib#CLIENT_SnapPictureByEvent}
 * 出参 {@link NET_OUT_SNAP_BY_EVENT}
 *
 * @author 47040
 * @since Created in 2020/11/18 17:21
 */
public class NET_IN_SNAP_BY_EVENT extends NetSDKLib.SdkStructure {
    /**
     * 本结构体大小, 初始化时必须填写
     */
    public int              dwSize;
    /**
     * 视频通道号, 从 0开始
     */
    public int              nChannel;
    /**
     * 参考 CLIENT_RealLoadPicEx 的事件类型
     * {@link NetSDKLib#EVENT_IVS_ALL ...}
     * {@link EM_EVENT_IVS}
     * {@link EM_EVENT_IVS_TYPE}
     */
    public int              dwEventID;
    /**
     * 抓图序列号,用于和回调事件作匹配
     */
    public byte[]           szSerialNo = new byte[128];
    /**
     * true：以非精确方式抓图（不指定帧编码），不填或false：精确抓图（指定帧编码）
    */
    public int              bSnapShotByGeneral;
    /**
     * 若SnapShotByGeneral为true，可以指定抓图模式, "Mannul": 手动抓图 "Intelli_event"： 智能非精确抓图 "Alarm"：报警 "Event"：普通事件，如动检等
    */
    public byte[]           szSnapMode = new byte[32];
    /**
     * 事件序号，可以是报警通道号，视频检查通道号，或者视频分析规则ID号
    */
    public int              nIndex;
    /**
     * 抓图次数,-1~10；其中-1表示无限制抓图次数
    */
    public int              nSnapshotTimes;

    public NET_IN_SNAP_BY_EVENT() {
        dwSize = this.size();
    }
}

