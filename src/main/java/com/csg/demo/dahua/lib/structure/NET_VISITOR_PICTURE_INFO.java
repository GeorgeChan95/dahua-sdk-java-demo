package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 图片信息
*/
public class NET_VISITOR_PICTURE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 证件照片尺寸
    */
    public int              nIDPhotoSize;
    /**
     * 证件照片(Base64), 请求/回调时，内存由用户/SDK申请释放，最大204800
    */
    public Pointer          szIDPhoto;
    /**
     * 抓拍照片尺寸
    */
    public int              nCapturePicSize;
    /**
     * 抓拍照片(Base64), 请求/回调时，内存由用户/SDK申请释放，最大204800
    */
    public Pointer          szCapturePic;
    /**
     * 证件头像尺寸
    */
    public int              nCertificateHeadSize;
    /**
     * 证件头像(Base64), 请求/回调时，内存由用户/SDK申请释放，最大204800
    */
    public Pointer          szCertificateHead;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[128];

    public NET_VISITOR_PICTURE_INFO() {
    }
}

