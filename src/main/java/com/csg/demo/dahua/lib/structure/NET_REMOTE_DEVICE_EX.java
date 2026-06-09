package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 远程设备信息扩展
*/
public class NET_REMOTE_DEVICE_EX extends NetSDKLib.SdkStructure
{
    /**
     * 密码
    */
    public byte[]           szPwdEx2 = new byte[128];
    /**
     * 是否使用szPwdEx2密码
    */
    public int              bUsePwdEx2;
    /**
     * IP
    */
    public byte[]           szIpEx = new byte[64];
    /**
     * 是否使用szIpEx IP
    */
    public int              bUseIpEx;
    /**
     * 加密用户名并Base64编码
    */
    public byte[]           szEncryptedUserName = new byte[128];
    /**
     * 用户名GCM256认证标签 并Base64编码
    */
    public byte[]           szTagUserName = new byte[128];
    /**
     * 加密密码 并Base64编码
    */
    public byte[]           szEncrypedPassword = new byte[128];
    /**
     * 密码GCM256认证标签 并Base64编码
    */
    public byte[]           szTagPassword = new byte[128];
    /**
     * 主动注册ID
    */
    public byte[]           szAutoRegisteID = new byte[48];
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[392];

    public NET_REMOTE_DEVICE_EX() {
    }
}

