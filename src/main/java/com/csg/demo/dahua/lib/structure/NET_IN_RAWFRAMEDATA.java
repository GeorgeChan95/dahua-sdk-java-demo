package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 获取指定格式的YUV数据入参
*/
public class NET_IN_RAWFRAMEDATA extends NetSDKLib.SdkStructure
{
    public int              dwSize;
    /**
     * 视频输入通道号
    */
    public int              nChannel;
    /**
     * sensor ID
    */
    public int              nSensorID;
    /**
     * YUV数据格式,支持的范围通过CLIENT_GetDevCaps方法, 命令: NET_VIDEOIN_RAWFRAME_CAPS获取
    */
    public byte[]           szRawFrameType = new byte[32];

    public NET_IN_RAWFRAMEDATA() {
        this.dwSize = this.size();
    }
}

