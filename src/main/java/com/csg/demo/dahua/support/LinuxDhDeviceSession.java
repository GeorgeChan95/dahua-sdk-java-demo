package com.csg.demo.dahua.support;

/**
 * @ClassName LinuxDhDeviceSession
 * @Description Linux 平台大华设备会话，负责登录、注销、重连、在线检测
 * @Author George
 * @Date 2026/6/9 15:35
 */
public class LinuxDhDeviceSession extends AbstractDhDeviceSession {

    /** 构造 Linux 平台会话。 */
    public LinuxDhDeviceSession(String ip, int port, String username, String password) {
        super("Linux", ip, port, username, password);
    }
}
