package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_DoSubBizDownloadMediaFile 接口输出参数
*/
public class NET_OUT_DO_DOWNLOAD_MEDIA_FILE extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_OUT_DO_DOWNLOAD_MEDIA_FILE() {
        this.dwSize = this.size();
    }
}

