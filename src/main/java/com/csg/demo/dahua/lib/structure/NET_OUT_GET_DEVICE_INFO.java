package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_QueryDevInfo , NET_QUERY_DEV_REMOTE_DEVICE_INFO 查询远程设备信息输出参数
*/
public class NET_OUT_GET_DEVICE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 用户使用该结构体时,dwSize 需赋值为 sizeof(NET_OUT_GET_DEVICE_INFO)
    */
    public int              dwSize;
    /**
     * 设备信息,该结构体内部成员 dwSize 需用户赋值,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_REMOTE_DEVICE}
    */
    public NetSDKLib.NET_REMOTE_DEVICE stuInfo = new NetSDKLib.NET_REMOTE_DEVICE();

    public NET_OUT_GET_DEVICE_INFO() {
        this.dwSize = this.size();
    }
}

