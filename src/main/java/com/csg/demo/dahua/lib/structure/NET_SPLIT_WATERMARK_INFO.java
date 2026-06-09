package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 水印信息
*/
public class NET_SPLIT_WATERMARK_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 水印内容
    */
    public byte[]           szContent = new byte[1024];
    /**
     * 使能
    */
    public int              nEnable;
    /**
     * 字体大小
    */
    public int              nFont;
    /**
     * 前景色,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_COLOR_RGBA}
    */
    public NetSDKLib.NET_COLOR_RGBA stuFrontColor = new NetSDKLib.NET_COLOR_RGBA();
    /**
     * 旋转角度
    */
    public int              nRotationAngle;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[124];

    public NET_SPLIT_WATERMARK_INFO() {
    }
}

