package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 设备类型信息
*/
public class CFG_DEVICE_CLASS_INFO extends NetSDKLib.SdkStructure
{
    public int              dwSize;
    /**
     * 设备类型,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.NET_EM_DEVICE_TYPE}
    */
    public int              emDeviceType;

    public CFG_DEVICE_CLASS_INFO() {
        this.dwSize = this.size();
    }
}

