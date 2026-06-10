package com.csg.demo.service;

/**
 * @ClassName LinuxDhService
 * @Description 大华设备登录/注销服务接口，供 Controller 调用
 * @Author George
 * @Date 2026/6/9 15:48
 */
public interface DhSdkService {

    /**
     * SDK登录设备
     * @param ip 设备IP
     * @param port 设备端口（大华默认 37777，超过 short 范围，故用 int）
     * @param username 用户名
     * @param password 密码
     * @return 登录是否成功
     */
    boolean login(String ip, int port, String username, String password);

    /**
     * 注销指定设备的登录
     * @param ip 设备IP
     * @param port 设备端口
     * @return 注销是否成功（未登录视为成功）
     */
    boolean logout(String ip, int port);


    /**
     * 执行云台控制。
     *
     * @param ip 设备 IP
     * @param port 设备端口
     * @param channelId 通道号
     * @param command 云台命令值，对应 SDK NET_PTZ_ControlType（如 0上 1下 2左 3右）
     * @param param1 参数 1，方向控制通常表示水平速度
     * @param param2 参数 2，方向/镜头控制通常表示垂直速度或控制速度
     * @param param3 参数 3，基础云台命令默认传 0
     * @param stop 是否停止命令，false 表示开始，true 表示停止
     * @return 控制是否成功
     */
    boolean control(String ip, int port, String username, String password, int channelId, int command,
                    int param1, int param2, int param3, boolean stop);
}
