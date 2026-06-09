package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 获取SIM卡的状态类型入参
*/
public class NET_IN_SIMINFO_GET_SIMSTATE extends NetSDKLib.SdkStructure
{
    /**
     * 赋值为结构体大小
    */
    public int              dwSize;
    /**
     * 无线模块名称,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.EM_WIRELESS_MODE}
    */
    public int              emMode;

    public NET_IN_SIMINFO_GET_SIMSTATE() {
        this.dwSize = this.size();
    }
}

