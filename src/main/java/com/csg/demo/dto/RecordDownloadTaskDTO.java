package com.csg.demo.dto;

import lombok.Data;

/**
 * 录像下载任务状态。
 */
@Data
public class RecordDownloadTaskDTO {
    /** 下载任务 ID。 */
    private String taskId;

    /** 任务状态：PENDING、DOWNLOADING、COMPLETED、FAILED。 */
    private String status;

    /** 下载进度，0~100。 */
    private int progress;

    /** 已下载大小，单位字节；SDK 无法提供时为 0。 */
    private int downloadSize;

    /** 总大小，单位字节；SDK 无法提供时为 0。 */
    private int totalSize;

    /** 本地保存文件路径。 */
    private String filePath;

    /** 失败原因。 */
    private String errorMessage;
}
