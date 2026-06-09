package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 获取SIM卡的状态类型出参
*/
public class NET_OUT_SIMINFO_GET_SIMSTATE extends NetSDKLib.SdkStructure
{
    /**
     * 赋值为结构体大小
    */
    public int              dwSize;
    /**
     * SIM卡状态类型,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.EM_SIMSTATE_MODE}
    */
    public int              emMode;

    public NET_OUT_SIMINFO_GET_SIMSTATE() {
        this.dwSize = this.size();
    }
}

