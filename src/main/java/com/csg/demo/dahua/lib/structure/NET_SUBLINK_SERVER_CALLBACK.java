package com.csg.demo.dahua.lib.structure;


import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;

/** 
子链接监听服务回调信息
*/
public class NET_SUBLINK_SERVER_CALLBACK extends NetSDKLib.SdkStructure {
/** 
/< 子连接状态  {@link com.csg.demo.dahua.lib.enumeration.EM_SUBLINK_STATE }
*/
    public			int            emSubLinkState;
/** 
/< 设备网络信息
*/
    public NET_DEV_NETWORK_INFO stuDevNetInfo = new NET_DEV_NETWORK_INFO();
/** 
/< 用户数据 LDWORD
*/
    public Pointer          dwUserData;
}

