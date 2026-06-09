package com.csg.demo.dahua.lib.structure;

import com.csg.demo.dahua.lib.NetSDKLib;
import com.csg.demo.dahua.lib.NetSDKLib.fSearchDevicesCBEx;
import com.sun.jna.Pointer;

public class NET_IN_STARTSERACH_DEVICE extends NetSDKLib.SdkStructure {
	   /**
	    * 结构体大小
	    */
    public int              dwSize;
	   /**
	    * 发起搜索的本地IP
	    */
    public byte[]           szLocalIp = new byte[64];
	   /**
	    * 设备信息回调函数
	    */
    public fSearchDevicesCBEx cbSearchDevices;
	   /**
	    * 用户自定义数据
	    */
    public Pointer          pUserData;
	   /**
	    * 下发搜索类型(参考EM_SEND_SEARCH_TYPE)
	    */
    public int              emSendType;
    /**
     * TTLV设备信息回调函数,参见回调函数定义 {@link com.csg.demo.dahua.lib.NetSDKLib.fSearchDevicesCBTTLV}
    */
    public NetSDKLib.fSearchDevicesCBTTLV cbSearchDevicesTTLV;
    /**
     * 4代设备信息回调函数,参见回调函数定义 {@link com.csg.demo.dahua.lib.NetSDKLib.fSearchDevicesCB4th}
    */
    public NetSDKLib.fSearchDevicesCB4th cbSearchDevices4th;

	   public NET_IN_STARTSERACH_DEVICE()
	    {
	     this.dwSize = this.size();
	    }// 此结构体大小
}

