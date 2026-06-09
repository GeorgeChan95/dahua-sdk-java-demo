package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_GetSubBusinessCaps 接口输出参数
*/
public class NET_OUT_GET_SUBBIZ_CAPS extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 子(分压)业务能力掩码, 0表示不支持分压, bit0:实时流 bit1:回放流 bit2:图片流, bit3:按文件下载, bit4:私有透传隧道
    */
    public int              nBusinessDivision;

    public NET_OUT_GET_SUBBIZ_CAPS() {
        this.dwSize = this.size();
    }
}

