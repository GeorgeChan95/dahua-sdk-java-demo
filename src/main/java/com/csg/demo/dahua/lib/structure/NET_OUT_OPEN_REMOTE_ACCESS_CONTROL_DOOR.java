package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_OpenRemoteAccessControlDoor 输出参数
*/
public class NET_OUT_OPEN_REMOTE_ACCESS_CONTROL_DOOR extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_OUT_OPEN_REMOTE_ACCESS_CONTROL_DOOR() {
        this.dwSize = this.size();
    }
}

