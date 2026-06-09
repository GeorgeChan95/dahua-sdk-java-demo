package com.csg.demo.dahua.lib.structure;

import com.csg.demo.dahua.lib.NetSDKLib;

/**
 * @author 291189
 * @version 1.0
 * @description CLIENT_RadarManualTrack接口入参
 * @date 2021/10/21 20:03
 */
public class NET_IN_RADAR_MANUAL_TRACK extends NetSDKLib.SdkStructure {
	/**
	 * 结构体大小
	 */
    public int              dwSize;
	/** 需要跟踪的球机 */
    public byte[]           szSDIP = new byte[32];
	/** 需要跟踪目标的轨迹号 */
    public int              nTrackID;
	/** 跟踪时间，单位为s */
    public int              nTime;
	/** 跟踪动作, 0:停止手动跟踪, 1:开始手动跟踪 */
    public int              nAction;
	/** 经纬度坐标，用整型传输，扩大1000000倍，小数点后6位有效，数组0表示经度，数组1表示纬度 */
    public int[]            arrCoordinate = new int[2];
	/** 手动跟踪的原因, bit0表示超速 bit1表示是否AIS匹配 bit2表示是否禁行 bit3表示是否逆行 （bit位置1表示需要手动跟踪，全0表示普通跟踪命令） */
    public int              nAlarmType;
	/** 航速：要跟踪目标的速度，单位km/h 扩大10000 倍下发（精度0.0001） */
    public int              nSpeed;
	/** 航向：正北方向为0° 顺时针为正 0-360°，扩大100倍下发 */
    public int              nAzimuth;
	/** 通道号 */
    public int              nChannel;
    /**
     * 手动跟踪目标唯一id；（多雷达情况下也是唯一） char数组的第1-12位： 是设备的MAC地址； 13-23位 ： 是时间； 23-28位 ：是随机数； 29-32位 ：是目标id.（多雷多球场景下增加此字段）
    */
    public byte[]           szTargetUUID = new byte[32];

	public NET_IN_RADAR_MANUAL_TRACK() {
		this.dwSize = this.size();
	}
}

