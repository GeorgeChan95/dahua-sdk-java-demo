package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 获取指定格式的YUV数据出参
*/
public class NET_OUT_RAWFRAMEDARA extends NetSDKLib.SdkStructure
{
    public int              dwSize;
    /**
     * 返回图片的高度
    */
    public int              nHeight;
    /**
     * 返回图片的宽
    */
    public int              nWidth;
    /**
     * YUV二进制数据大小，单位字节
    */
    public int              nDataLen;
    /**
     * YUV数据，由用户申请内存，大小为，nBufferLen
    */
    public Pointer          pszBuffer;
    /**
     * 用户申请YUV数据内存大小
    */
    public int              nBufferLen;

    public NET_OUT_RAWFRAMEDARA() {
        this.dwSize = this.size();
    }
}

