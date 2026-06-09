package com.csg.demo.dahua.lib.structure;

import com.csg.demo.dahua.lib.NetSDKLib;

/**
 * @author 291189
 * @version 1.0
 * @description CLIENT_RadarManualTrack接口出参
 * @date 2021/10/22 9:03
 */
public class NET_OUT_RADAR_MANUAL_TRACK extends NetSDKLib.SdkStructure {
	/** 结构体大小 */
    public int              dwSize;
	/** 返回被跟踪轨迹对应的球机ip */
    public byte[]           szSDIP = new byte[32];
	/**返回被跟踪目标的一个全局唯一id */
    public int              nEventID;
	/** 字节对齐 **/
    public int[]            szReserved = new int[4];
	/** 联动目标唯一id；（多雷达情况下也是唯一） char数组的第1-12位： 是设备的MAC地址； 13-23位 ： 是时间； 23-28位 ：是随机数； 29-32位 ：是目标id，即TrackID字段 **/
    public byte[]           szTargetUUID = new byte[32];
    /**
     * 手动跟踪接口失败错误码  1：球机没有开启联动使能  2：球机没有进行校准配置  3：球机正在手动跟踪，请先关闭手动跟踪后在开启。 4：目标到雷达端已经消失或者不存在的id  5：本雷达为从雷达，请发往主雷达设备  6：选择的目标超出球机设置的跟踪范围, 1~4使用场景：一雷一球场景，并且不指派球机   5使用场景：多雷一球场景，并且不指派球机IP
    */
    public int              nErrorCode;
    /**
     * 球机列表中的球机数量
    */
    public int              nSDInfoListNum;
    /**
     * 多球机时，返回分配的球机列表（返回最近的N个球机ip）,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_RADAR_TRACK_SD_INFO}
    */
    public NET_RADAR_TRACK_SD_INFO[] stuSDInfoList = new NET_RADAR_TRACK_SD_INFO[8];

	public NET_OUT_RADAR_MANUAL_TRACK() {
		this.dwSize = this.size();
	}
}

