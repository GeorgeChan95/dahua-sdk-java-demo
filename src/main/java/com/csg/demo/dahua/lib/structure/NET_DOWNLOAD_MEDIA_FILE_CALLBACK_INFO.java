package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 文件下载回调信息
*/
public class NET_DOWNLOAD_MEDIA_FILE_CALLBACK_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 文件总长度
    */
    public long             nTotalSize;
    /**
     * 已下载数据长度, -1 表示下载完成
    */
    public long             nDownLoadSize;
    /**
     * 错误信息, 0:无错误, 1: 无权限,2:设备不支持此操作,3:资源不足, 4:设备无法获取当前请求数据, 11: 表示高级用户抢占低级用户资源, 12: 禁止入网, 13: 磁盘读数据限制, 14: 多画面预览已打开,资源不足,压缩回放失败, 15: 压缩回放功能已打开,导致失败, 16: 表示此视频通道处于离线，拉流失败, 21: 未知的压缩失败原因, , 22: 超出解码能力，导致压缩失败, 23: 超出压缩能力，导致压缩失败, 24: 无原始码流，导致压缩失败, 25: 压缩通道所在的从片掉线，导致压缩失败, 26: 主码流成功的情况下，拉辅码流时登录失败, 27: 辅码流被前端关闭, 28: 文件不存在, 100: 未知错误
    */
    public int              nErrCode;
    /**
     * 数据长度
    */
    public int              nBufferSize;
    /**
     * 文件数据
    */
    public Pointer          pDataBuffer;
    /**
     * 用户数据
    */
    public Pointer          dwUserData;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_DOWNLOAD_MEDIA_FILE_CALLBACK_INFO() {
    }
}

