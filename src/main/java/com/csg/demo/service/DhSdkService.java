package com.csg.demo.service;

import com.csg.demo.dto.PtzPresetInfoDTO;
import com.csg.demo.dto.PtzLocationInfoDTO;
import com.csg.demo.dto.RecordDownloadTaskDTO;
import com.csg.demo.dto.RecordFileInfoDTO;

import java.util.List;

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

    /**
     * 查询当前通道的云台位置信息。
     *
     * @param ip 设备 IP
     * @param port 设备端口
     * @param username 用户名，未登录时用于自动登录
     * @param password 密码，未登录时用于自动登录
     * @param channelId 通道号，从 0 开始
     * @return 云台位置信息；设备不支持或查询失败时返回空对象
     */
    PtzLocationInfoDTO getPtzLocation(String ip, int port, String username, String password, int channelId);

    /**
     * 远程抓取当前通道的一张图片。
     *
     * @param ip 设备 IP
     * @param port 设备端口
     * @param username 用户名，未登录时用于自动登录
     * @param password 密码，未登录时用于自动登录
     * @param channelId 通道号，从 0 开始
     * @return JPEG 图片字节；失败时返回空数组
     */
    byte[] capturePicture(String ip, int port, String username, String password, int channelId);

    /**
     * 查询指定时间段内的录像文件列表。
     *
     * @param ip 设备 IP
     * @param port 设备端口
     * @param username 用户名，未登录时用于自动登录
     * @param password 密码，未登录时用于自动登录
     * @param channelId 通道号，从 0 开始
     * @param startTime 查询开始时间，格式：yyyy-MM-dd HH:mm:ss
     * @param endTime 查询结束时间，格式：yyyy-MM-dd HH:mm:ss
     * @param maxCount 最大返回条数
     * @return 录像文件列表；失败时返回空列表
     */
    List<RecordFileInfoDTO> listRecordFiles(String ip, int port, String username, String password,
                                            int channelId, String startTime, String endTime, int maxCount);

    /**
     * 异步下载指定时间段内的录像文件。
     *
     * @param ip 设备 IP
     * @param port 设备端口
     * @param username 用户名，未登录时用于自动登录
     * @param password 密码，未登录时用于自动登录
     * @param channelId 通道号，从 0 开始
     * @param startTime 下载开始时间，格式：yyyy-MM-dd HH:mm:ss
     * @param endTime 下载结束时间，格式：yyyy-MM-dd HH:mm:ss
     * @param recordFileType 录像类型，0 表示所有录像
     * @param fileName 本地保存文件名，可为空
     * @return 下载任务状态；失败时返回 null
     */
    RecordDownloadTaskDTO downloadRecordFile(String ip, int port, String username, String password,
                                             int channelId, String startTime, String endTime,
                                             int recordFileType, String fileName);

    /**
     * 查询录像下载任务状态。
     *
     * @param taskId 下载任务 ID
     * @return 下载任务状态；不存在时返回 null
     */
    RecordDownloadTaskDTO getRecordDownloadTask(String taskId);

    /**
     * 查询云台预置点列表。
     *
     * @param ip 设备 IP
     * @param port 设备端口
     * @param username 用户名，未登录时用于自动登录
     * @param password 密码，未登录时用于自动登录
     * @param channelId 通道号，从 0 开始
     * @return 预置点列表；失败时返回空列表
     */
    List<PtzPresetInfoDTO> listPresets(String ip, int port, String username, String password, int channelId);

    /**
     * 切换到指定云台预置点。
     *
     * @param ip 设备 IP
     * @param port 设备端口
     * @param username 用户名，未登录时用于自动登录
     * @param password 密码，未登录时用于自动登录
     * @param channelId 通道号，从 0 开始
     * @param presetIndex 预置点编号，通常从 1 开始
     * @return 是否切换成功
     */
    boolean gotoPreset(String ip, int port, String username, String password, int channelId, int presetIndex);
}
