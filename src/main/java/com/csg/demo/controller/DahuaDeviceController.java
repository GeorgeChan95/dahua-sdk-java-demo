package com.csg.demo.controller;

import com.csg.demo.service.LinuxDhService;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 大华设备登录与注销接口。
 *
 * @author George
 */
@RestController
@RequestMapping("/dahua/device")
public class DahuaDeviceController {

    private final LinuxDhService linuxDhService;

    public DahuaDeviceController(LinuxDhService linuxDhService) {
        this.linuxDhService = linuxDhService;
    }

    /** 登录设备，返回是否成功。 */
    @PostMapping("/login")
    public boolean login(@RequestBody LoginRequest request) {
        return linuxDhService.login(request.getIp(), request.getPort(),
                request.getUsername(), request.getPassword());
    }

    /** 按 ip:port 注销设备，返回是否成功。 */
    @PostMapping("/logout")
    public boolean logout(@RequestBody LogoutRequest request) {
        return linuxDhService.logout(request.getIp(), request.getPort());
    }

    /** 登录请求参数。 */
    @Data
    public static class LoginRequest {
        private String ip;
        private int port;
        private String username;
        private String password;
    }

    /** 注销请求参数。 */
    @Data
    public static class LogoutRequest {
        private String ip;
        private int port;
    }
}
