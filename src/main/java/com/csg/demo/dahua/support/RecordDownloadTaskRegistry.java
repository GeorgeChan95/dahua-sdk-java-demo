package com.csg.demo.dahua.support;

import com.csg.demo.dahua.lib.NetSDKLib;
import com.csg.demo.dto.RecordDownloadTaskDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 录像下载任务注册表，负责异步调度和维护下载进度。
 */
public final class RecordDownloadTaskRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecordDownloadTaskRegistry.class);
    private static final NetSDKLib NET_SDK = NetSDKLib.NETSDK_INSTANCE;
    private static final Timer DOWNLOAD_TIMER = new Timer("dahua-record-download", true);
    private static final ConcurrentMap<String, RecordDownloadTask> TASKS = new ConcurrentHashMap<>();

    private RecordDownloadTaskRegistry() {
    }

    /**
     * 创建下载任务。
     *
     * @param filePath 本地保存文件路径
     * @return 下载任务
     */
    public static RecordDownloadTask createTask(String filePath) {
        String taskId = UUID.randomUUID().toString();
        RecordDownloadTask task = new RecordDownloadTask(taskId, filePath);
        TASKS.put(taskId, task);
        return task;
    }

    /**
     * 异步执行下载启动逻辑。
     *
     * @param task 下载任务
     * @param runnable 下载启动逻辑
     */
    public static void schedule(RecordDownloadTask task, Runnable runnable) {
        DOWNLOAD_TIMER.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    runnable.run();
                } catch (Exception ex) {
                    task.fail("录像下载任务执行异常: " + ex.getMessage());
                    LOGGER.warn("\n录像下载任务执行异常，taskId: {}", task.getTaskId(), ex);
                }
            }
        }, 0);
    }

    /**
     * 查询下载任务状态。
     *
     * @param taskId 下载任务 ID
     * @return 下载任务状态；不存在时返回 null
     */
    public static RecordDownloadTaskDTO getTask(String taskId) {
        RecordDownloadTask task = TASKS.get(taskId);
        return task == null ? null : task.toDTO();
    }

    /** 录像下载任务。 */
    public static final class RecordDownloadTask {
        private final String taskId;
        private final String filePath;
        private final NetSDKLib.fTimeDownLoadPosCallBack callback;

        private volatile String status = "PENDING";
        private volatile int progress;
        private volatile int downloadSize;
        private volatile int totalSize;
        private volatile String errorMessage;
        private volatile NetSDKLib.LLong downloadHandle = new NetSDKLib.LLong(0);

        private RecordDownloadTask(String taskId, String filePath) {
            this.taskId = taskId;
            this.filePath = filePath;
            this.callback = (handle, total, downloaded, index, recordInfo, user) -> {
                if (downloaded == -1) {
                    complete();
                    stopDownload();
                    return;
                }
                updateProgress(total, downloaded);
            };
        }

        public String getTaskId() {
            return taskId;
        }

        public String getFilePath() {
            return filePath;
        }

        public NetSDKLib.fTimeDownLoadPosCallBack getCallback() {
            return callback;
        }

        /** 记录 SDK 返回的下载句柄，并标记为下载中。 */
        public void start(NetSDKLib.LLong handle) {
            this.downloadHandle = handle;
            this.status = "DOWNLOADING";
            this.progress = 0;
        }

        /** 标记任务失败。 */
        public void fail(String message) {
            this.status = "FAILED";
            this.errorMessage = message;
            stopDownload();
        }

        /** 转换为接口返回对象。 */
        public RecordDownloadTaskDTO toDTO() {
            RecordDownloadTaskDTO dto = new RecordDownloadTaskDTO();
            dto.setTaskId(taskId);
            dto.setStatus(status);
            dto.setProgress(progress);
            dto.setDownloadSize(downloadSize);
            dto.setTotalSize(totalSize);
            dto.setFilePath(filePath);
            dto.setErrorMessage(errorMessage);
            return dto;
        }

        private void updateProgress(int total, int downloaded) {
            this.totalSize = Math.max(total, 0);
            this.downloadSize = Math.max(downloaded, 0);
            if (total > 0 && downloaded >= 0) {
                this.progress = Math.max(0, Math.min(100, downloaded * 100 / total));
            }
        }

        private void complete() {
            this.status = "COMPLETED";
            this.progress = 100;
            LOGGER.info("\n视频文件下载完成，文件路径：{}", this.filePath);
        }

        private void stopDownload() {
            NetSDKLib.LLong handle = downloadHandle;
            if (handle != null && handle.longValue() != 0) {
                NET_SDK.CLIENT_StopDownload(handle);
                downloadHandle = new NetSDKLib.LLong(0);
            }
        }
    }
}
