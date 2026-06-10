package com.csg.demo.dahua.support;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName LinuxDeviceSessionManager
 * @Description Linux 平台大华设备会话管理器，以 ip:port 为 key 缓存会话
 * @Author George
 * @Date 2026/6/9 15:44
 */
@Component
public class LinuxDeviceSessionManager {

    /** 设备会话缓存，使用 ConcurrentHashMap 保证多线程安全。 */
    private final Map<String, LinuxDhDeviceSession> sessionMap = new ConcurrentHashMap<>();

    /** 登录并缓存会话；登录失败时不缓存，返回 false。 */
    public boolean login(String ip, int port, String username, String password) {
        LinuxDhDeviceSession session = sessionMap.compute(buildKey(ip, port), (key, existing) -> {
            LinuxDhDeviceSession target = existing != null ? existing : new LinuxDhDeviceSession(ip, port, username, password);
            return target.login() ? target : null;
        });
        return session != null;
    }

    /** 注销并移除会话；本未登录或注销成功返回 true，注销失败保留会话并返回 false。 */
    public boolean logout(String ip, int port) {
        // computeIfPresent 返回 null 表示已移除（不存在或注销成功），非 null 表示注销失败仍保留。
        LinuxDhDeviceSession remaining = sessionMap.computeIfPresent(buildKey(ip, port),
                (key, session) -> session.logout() ? null : session);
        return remaining == null;
    }

    /** 返回当前缓存的所有会话，供心跳检测遍历。 */
    public Collection<LinuxDhDeviceSession> getAllSessions() {
        return sessionMap.values();
    }

    /** 获取已登录设备会话；未登录时自动登录，登录失败返回 null 且不缓存。 */
    public LinuxDhDeviceSession getSession(String ip, int port, String username, String password) {
        return sessionMap.compute(buildKey(ip, port), (key, existing) -> {
            LinuxDhDeviceSession session = existing != null ? existing : new LinuxDhDeviceSession(ip, port, username, password);
            return session.login() ? session : null;
        });
    }

    /** 以 ip:port 构造缓存 key。 */
    private String buildKey(String ip, int port) {
        return ip + ":" + port;
    }
}
