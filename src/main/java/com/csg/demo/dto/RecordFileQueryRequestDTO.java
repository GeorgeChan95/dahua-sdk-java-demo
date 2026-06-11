package com.csg.demo.dto;

import lombok.Data;

/**
 * 录像文件查询请求参数。
 */
@Data
public class RecordFileQueryRequestDTO {
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

    /** 查询开始时间，格式：yyyy-MM-dd HH:mm:ss。 */
    private String startTime;

    /** 查询结束时间，格式：yyyy-MM-dd HH:mm:ss。 */
    private String endTime;

    /** 最大返回条数，默认 100，最大 200。 */
    private int maxCount;

    public int getChannelId() {
        if (channelId > 0) {
            return channelId - 1;
        }
        return channelId;
    }
}
