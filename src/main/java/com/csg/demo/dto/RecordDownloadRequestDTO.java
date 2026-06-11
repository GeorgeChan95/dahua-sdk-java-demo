package com.csg.demo.dto;

import lombok.Data;

/**
 * 录像下载请求参数。
 */
@Data
public class RecordDownloadRequestDTO {
    /** 设备 IP。 */
    private String ip;

    /** 设备端口。 */
    private int port;

    /** 设备登录用户名；未登录时用于自动登录。 */
    private String username;

    /** 设备登录密码；未登录时用于自动登录。 */
    private String password;

    /** 通道号，从 0 开始。 */
    private int channelId;

    /** 下载开始时间，格式：yyyy-MM-dd HH:mm:ss。 */
    private String startTime;

    /** 下载结束时间，格式：yyyy-MM-dd HH:mm:ss。 */
    private String endTime;

    /** 录像类型，默认 0 表示所有录像。 */
    private int recordFileType;

    /** 本地保存文件名，可为空；为空时自动生成 .mp4 文件名。 */
    private String fileName;

    public int getChannelId() {
        if (channelId > 0) {
            return channelId - 1;
        }
        return channelId;
    }
}
