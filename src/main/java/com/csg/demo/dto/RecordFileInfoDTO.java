package com.csg.demo.dto;

import lombok.Data;

/**
 * 录像文件信息。
 */
@Data
public class RecordFileInfoDTO {
    /** 通道号，从 0 开始。 */
    private int channelId;

    /** 设备返回的录像文件名。 */
    private String fileName;

    /** 录像文件大小，单位字节。 */
    private int fileSize;

    /** 录像总帧数。 */
    private int frameCount;

    /** 录像类型，0普通录像、1报警录像、2移动检测等。 */
    private int recordFileType;

    /** 录像码流类型，0主码流、1辅码流1、2辅码流2、3辅码流3。 */
    private int streamType;

    /** 是否重要录像。 */
    private boolean important;

    /** 录像开始时间，格式：yyyy-MM-dd HH:mm:ss。 */
    private String startTime;

    /** 录像结束时间，格式：yyyy-MM-dd HH:mm:ss。 */
    private String endTime;
}
