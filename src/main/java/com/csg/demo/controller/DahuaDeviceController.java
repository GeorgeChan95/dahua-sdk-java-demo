package com.csg.demo.controller;

import com.csg.demo.dto.CapturePictureRequestDTO;
import com.csg.demo.dto.PtzControlRequestDTO;
import com.csg.demo.dto.PtzLocationInfoDTO;
import com.csg.demo.dto.PtzLocationRequestDTO;
import com.csg.demo.dto.PtzPresetInfoDTO;
import com.csg.demo.dto.PtzPresetRequestDTO;
import com.csg.demo.dto.RecordDownloadRequestDTO;
import com.csg.demo.dto.RecordDownloadTaskDTO;
import com.csg.demo.dto.RecordFileInfoDTO;
import com.csg.demo.dto.RecordFileQueryRequestDTO;
import com.csg.demo.dto.TalkFormatDTO;
import com.csg.demo.dto.TalkRequestDTO;
import com.csg.demo.service.DhSdkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 大华设备登录与注销接口。
 *
 * @author George
 */
@Slf4j
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
        log.info("参数为：{}", request);
        return dhSdkService.control(request.getIp(), request.getPort(), request.getUsername(), request.getPassword(), request.getChannelId(),
                request.getCommand(), request.getParam1(), request.getParam2(), request.getParam3(), request.isStop());
    }

    /**
     * 查询当前通道的云台位置信息。
     *
     * @param request 云台位置查询请求参数
     * @return 云台位置信息；设备不支持时字段为空
     */
    @PostMapping("/ptz/location")
    public PtzLocationInfoDTO getPtzLocation(@RequestBody PtzLocationRequestDTO request) {
        log.info("参数为：{}", request);
        return dhSdkService.getPtzLocation(request.getIp(), request.getPort(), request.getUsername(),
                request.getPassword(), request.getChannelId());
    }

    /**
     * 远程抓取当前通道图片，成功时直接返回 JPEG 字节。
     *
     * @param request 抓图请求参数，包含设备地址、登录凭据和通道号
     * @return JPEG 图片字节
     */
    @PostMapping(value = "/capture", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> capturePicture(@RequestBody CapturePictureRequestDTO request) {
        log.info("参数为：{}", request);
        byte[] pictureBytes = dhSdkService.capturePicture(request.getIp(), request.getPort(), request.getUsername(),
                request.getPassword(), request.getChannelId());
        if (pictureBytes.length == 0) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(pictureBytes);
    }

    /**
     * 查询指定时间段内的录像文件列表。
     *
     * @param request 录像文件查询请求参数
     * @return 录像文件列表
     */
    @PostMapping("/record/list")
    public List<RecordFileInfoDTO> listRecordFiles(@RequestBody RecordFileQueryRequestDTO request) {
        log.info("参数为：{}", request);
        return dhSdkService.listRecordFiles(request.getIp(), request.getPort(), request.getUsername(),
                request.getPassword(), request.getChannelId(), request.getStartTime(), request.getEndTime(),
                request.getMaxCount());
    }

    /**
     * 异步下载指定时间段内的录像文件。
     *
     * @param request 录像下载请求参数
     * @return 下载任务状态
     */
    @PostMapping("/record/download")
    public ResponseEntity<RecordDownloadTaskDTO> downloadRecordFile(@RequestBody RecordDownloadRequestDTO request) {
        log.info("参数为：{}", request);
        RecordDownloadTaskDTO task = dhSdkService.downloadRecordFile(request.getIp(), request.getPort(),
                request.getUsername(), request.getPassword(), request.getChannelId(), request.getStartTime(),
                request.getEndTime(), request.getRecordFileType(), request.getFileName());
        if (task == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(task);
    }

    /**
     * 查询录像下载任务状态。
     *
     * @param taskId 下载任务 ID
     * @return 下载任务状态
     */
    @PostMapping("/record/download/status")
    public ResponseEntity<RecordDownloadTaskDTO> getRecordDownloadTask(@RequestParam String taskId) {
        RecordDownloadTaskDTO task = dhSdkService.getRecordDownloadTask(taskId);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    /**
     * 查询设备支持的语音对讲编码格式。
     *
     * @param request 对讲请求参数，包含设备地址和登录凭据
     * @return 支持的 G711A/G711U 编码格式列表
     */
    @PostMapping("/talk/formats")
    public List<TalkFormatDTO> listTalkFormats(@RequestBody TalkRequestDTO request) {
        log.info("参数为：{}", request);
        return dhSdkService.listTalkFormats(request.getIp(), request.getPort(),
                request.getUsername(), request.getPassword());
    }

    /**
     * 查询云台预置点列表。
     *
     * @param request 预置点请求参数，包含设备地址、登录凭据和通道号
     * @return 预置点列表
     */
    @PostMapping("/preset/list")
    public List<PtzPresetInfoDTO> listPresets(@RequestBody PtzPresetRequestDTO request) {
        log.info("参数为：{}", request);
        return dhSdkService.listPresets(request.getIp(), request.getPort(), request.getUsername(),
                request.getPassword(), request.getChannelId());
    }

    /**
     * 切换到指定云台预置点。
     *
     * @param request 预置点请求参数，包含设备地址、登录凭据、通道号和预置点编号
     * @return 是否切换成功
     */
    @PostMapping("/preset/goto")
    public boolean gotoPreset(@RequestBody PtzPresetRequestDTO request) {
        log.info("参数为：{}", request);
        return dhSdkService.gotoPreset(request.getIp(), request.getPort(), request.getUsername(),
                request.getPassword(), request.getChannelId(), request.getPresetIndex());
    }


}
