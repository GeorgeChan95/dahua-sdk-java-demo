package com.csg.demo.dto;

import lombok.Data;

/**
 * 云台位置信息。
 */
@Data
public class PtzLocationInfoDTO {
    /** 通道号。 */
    private Integer channelId;

    /** 水平位置，范围 [0, 3600]，通常表示角度扩大 10 倍。 */
    private Integer ptzPan;

    /** 水平角度，ptzPan / 10。 */
    private Double panDegree;

    /** 垂直位置，范围 [-1800, 1800]，通常表示角度扩大 10 倍。 */
    private Integer ptzTilt;

    /** 垂直角度，ptzTilt / 10。 */
    private Double tiltDegree;

    /** 变倍位置，范围 [0, 128]，偏 SDK 内部控制值。 */
    private Integer ptzZoom;

    /** 聚焦位置，设备支持时有效。 */
    private Float focusPosition;

    /** 当前真实变倍值，扩大 100 倍。 */
    private Integer zoomValue;

    /** 当前真实变倍倍率，zoomValue / 100。 */
    private Double zoomValueRatio;

    /** 绝对水平角度，扩大 100 倍。 */
    private Integer absPositionX;

    /** 绝对水平角度，absPositionX / 100。 */
    private Double absPositionXDegree;

    /** 绝对垂直角度，扩大 100 倍。 */
    private Integer absPositionY;

    /** 绝对垂直角度，absPositionY / 100。 */
    private Double absPositionYDegree;

    /** 显示放大倍数，扩大 100 倍。 */
    private Integer absPositionZoom;

    /** 显示放大倍率，absPositionZoom / 100。 */
    private Double absPositionZoomRatio;

    /** 聚焦映射值，更偏设备内部映射值。 */
    private Integer focusMapValue;

    /** 变倍映射值，更偏设备内部映射值。 */
    private Integer zoomMapValue;

    /** 云台运动状态，0未知、1运动、2空闲。 */
    private Integer state;

    /** 聚焦状态，0未知、1运动、2空闲。 */
    private Integer focusState;

    /** 变倍状态，0未知、1变倍中、2空闲。 */
    private Integer zoomState;
}
