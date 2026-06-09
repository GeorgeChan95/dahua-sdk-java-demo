package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_DoSubBizDownloadMediaFile 接口输入参数
*/
public class NET_IN_DO_DOWNLOAD_MEDIA_FILE extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 视频通道号, -1 表示无效, 即不关心通道
    */
    public int              nChannelID;
    /**
     * 录像文件类型,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.EM_RECORDFILE_TYPE}
    */
    public int              emRecordType;
    /**
     * 录像文件路径
    */
    public byte[]           szFilePath = new byte[256];
    /**
     * 偏移大小, 单位字节
    */
    public int              nOffLength;
    /**
     * 文件长度, 单位字节
    */
    public int              nFileLength;

    public NET_IN_DO_DOWNLOAD_MEDIA_FILE() {
        this.dwSize = this.size();
    }
}

