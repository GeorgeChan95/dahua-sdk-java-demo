package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 回调的实时数据
*/
public class NET_NOTIFY_IOTBOX_COMM_BY_COND extends NetSDKLib.SdkStructure
{
    /**
     * 传感器的信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_IOTBOX_BY_COND_DEVICE_INFO}
    */
    public NET_IOTBOX_BY_COND_DEVICE_INFO[] stuDeviceInfo = new NET_IOTBOX_BY_COND_DEVICE_INFO[16];
    /**
     * 传感器的信息个数
    */
    public int              nDeviceInfoNum;
    /**
     * 保留字段
    */
    public byte[]           szResvered = new byte[1028];

    public NET_NOTIFY_IOTBOX_COMM_BY_COND() {
        for(int i = 0; i < stuDeviceInfo.length; i++){
            stuDeviceInfo[i] = new NET_IOTBOX_BY_COND_DEVICE_INFO();
        }
    }
}

