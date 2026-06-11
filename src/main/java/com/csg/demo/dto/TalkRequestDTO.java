package com.csg.demo.dto;

import lombok.Data;

/**
 * 语音对讲请求参数。
 */
@Data
public class TalkRequestDTO {
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

    /** 音频编码格式，支持 G711A、G711U。 */
    private String encoding;

    public int getChannelId() {
        if (channelId > 0) {
            return channelId - 1;
        }
        return channelId;
    }
}
