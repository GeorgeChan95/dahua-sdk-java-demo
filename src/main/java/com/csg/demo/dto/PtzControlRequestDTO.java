package com.csg.demo.dto;

import lombok.Data;

/**
 * @ClassName PtzControlRequestDTO
 * @Description 云台控制请求参数
 * @Author George
 * @Date 2026/6/10 13:44
 */
@Data
public class PtzControlRequestDTO {
    private String ip;
    private int port;
    private String username;
    private String password;
    private int channelId;
    /** 云台命令值，对应 SDK NET_PTZ_ControlType：0上 1下 2左 3右 4变倍+ 5变倍- 等 */
    private int command;
    private int param1;
    private int param2;
    private int param3;
    private boolean stop; // stop=false 表示开始，stop=true 表示停止


    public int getParam1() {
        return 0;
    }

    public int getParam2() {
        if (this.stop) { // 停止动作传0
            return 0;
        } else { // 开始动作传4
            return 4;
        }
    }

    public int getParam3() {
        return 0;
    }
}
