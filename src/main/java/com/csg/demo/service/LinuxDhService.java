package com.csg.demo.service;

/**
 * @ClassName LinuxDhService
 * @Description 大华设备登录/注销服务接口，供 Controller 调用
 * @Author George
 * @Date 2026/6/9 15:48
 */
public interface LinuxDhService {

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
}
