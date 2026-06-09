package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 设置水平旋转边界值 输出参数
*/
public class NET_OUT_PAN_GROUP_LIMIT_INFO extends NetSDKLib.SdkStructure
{
    public int              dwSize;

    public NET_OUT_PAN_GROUP_LIMIT_INFO() {
        this.dwSize = this.size();
    }
}

