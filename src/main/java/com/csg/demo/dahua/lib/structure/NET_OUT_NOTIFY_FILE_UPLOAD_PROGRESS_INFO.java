package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_NotifyFileUploadProgress 接口输出参数
*/
public class NET_OUT_NOTIFY_FILE_UPLOAD_PROGRESS_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_OUT_NOTIFY_FILE_UPLOAD_PROGRESS_INFO() {
        this.dwSize = this.size();
    }
}

