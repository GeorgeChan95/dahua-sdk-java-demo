package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_NotifyFileUploadProgress 接口输入参数
*/
public class NET_IN_NOTIFY_FILE_UPLOAD_PROGRESS_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 文件上传进度，取值范围0~100
    */
    public int              nProgress;
    /**
     * 已废弃，请使用szFilePath
    */
    public byte[]           szFileName = new byte[256];
    /**
     * 文件唯一标识
    */
    public byte[]           szUniqueID = new byte[128];
    /**
     * 文件路径
    */
    public byte[]           szFilePath = new byte[256];
    /**
     * 文件当前状态,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.EM_STATE_MODE}
    */
    public int              emState;
    /**
     * 当emState为EM_STATE_MODE_FAILED时有效，0xff-未知错误(255)，0x01-文件不存在(1)，0x02-文件重复上传(2)
    */
    public int              nErrorCode;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1784];

    public NET_IN_NOTIFY_FILE_UPLOAD_PROGRESS_INFO() {
        this.dwSize = this.size();
    }
}

