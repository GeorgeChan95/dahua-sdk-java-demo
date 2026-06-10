package com.csg.demo.controller;

import com.csg.demo.dto.PtzControlRequestDTO;
import com.csg.demo.service.DhSdkService;
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

    private final DhSdkService dhSdkService;

    public DahuaDeviceController(DhSdkService dhSdkService) {
        this.dhSdkService = dhSdkService;
    }

    /** 登录设备，返回是否成功。 */
    @PostMapping("/login")
    public boolean login(String ip, Integer port, String username, String password) {
        return dhSdkService.login(ip, port, username, password);
    }

    /** 按 ip:port 注销设备，返回是否成功。 */
    @PostMapping("/logout")
    public boolean logout(String ip, Integer port) {
        return dhSdkService.logout(ip, port);
    }

    /** 执行云台控制命令，stop=false 表示开始，stop=true 表示停止。 */
    @PostMapping("/control")
    public boolean control(@RequestBody PtzControlRequestDTO request) {
        return dhSdkService.control(request.getIp(), request.getPort(), request.getUsername(), request.getPassword(), request.getChannelId(),
                request.getCommand(), request.getParam1(), request.getParam2(), request.getParam3(), request.isStop());
    }


}
