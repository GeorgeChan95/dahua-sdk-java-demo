package com.csg.demo.dahua.support;

/**
 * @ClassName WindowsDhDeviceSession
 * @Description Windows 平台大华设备会话，负责登录、注销、重连、在线检测
 * @Author George
 * @Date 2026/6/9 15:35
 */
public class WindowsDhDeviceSession extends AbstractDhDeviceSession {

    /** 构造 Windows 平台会话。 */
    public WindowsDhDeviceSession(String ip, int port, String username, String password) {
        super("Windows", ip, port, username, password);
    }
}
