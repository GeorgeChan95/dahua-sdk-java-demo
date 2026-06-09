package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * CLIENT_StartSubBizDownloadMediaFile 接口输入参数
*/
public class NET_IN_START_DOWNLOAD_MEDIA_FILE extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[4];
    /**
     * 文件下载回调函数,参见回调函数定义 {@link com.csg.demo.dahua.lib.NetSDKLib.fDownLoadMediaFileCallBack}
    */
    public NetSDKLib.fDownLoadMediaFileCallBack cbDownLoadCallBack;
    /**
     * 连接断线回调,参见回调函数定义 {@link com.csg.demo.dahua.lib.NetSDKLib.fSubBizDisConnectCallBack}
    */
    public NetSDKLib.fSubBizDisConnectCallBack cbDisConnectCallBack;
    /**
     * 用户数据
    */
    public Pointer          dwUserData;

    public NET_IN_START_DOWNLOAD_MEDIA_FILE() {
        this.dwSize = this.size();
    }
}

