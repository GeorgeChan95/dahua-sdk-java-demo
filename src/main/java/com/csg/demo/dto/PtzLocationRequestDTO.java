package com.csg.demo.dto;

import lombok.Data;

/**
 * 云台位置查询请求参数。
 */
@Data
public class PtzLocationRequestDTO {
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
}
