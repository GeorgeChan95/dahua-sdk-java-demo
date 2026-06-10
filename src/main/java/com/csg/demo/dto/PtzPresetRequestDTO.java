package com.csg.demo.dto;

import lombok.Data;

/**
 * 云台预置点请求参数。
 */
@Data
public class PtzPresetRequestDTO {
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

    /** 预置点编号；查询列表时不使用，切换预置点时必填且大于 0。 */
    private int presetIndex;
}
