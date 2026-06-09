package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_RemoveMultiAccessPasswordComm接口输入参数
*/
public class NET_IN_REMOVE_MULTI_ACCESS_PASSWORD_COMM extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 1.管理者密码/公共密码;2.个性化密码; 3.临时密码
    */
    public int              nPassWordType;
    /**
     * ID列表有效长度,不能超过256且不能超过CLIENT_GetDevCaps(NET_ACCESSCONTROL_CAPS) 接口获取到的 NET_ACCESS_PASSWORD_COMM_CAPS.stuAccessPasswordCommCaps.nMaxInsertRate
    */
    public int              nIDListNum;
    /**
     * ID列表
    */
    public BYTE_ARRAY_32[]  szIDList = new BYTE_ARRAY_32[256];

    public NET_IN_REMOVE_MULTI_ACCESS_PASSWORD_COMM() {
        this.dwSize = this.size();
        for(int i = 0; i < szIDList.length; i++){
            szIDList[i] = new BYTE_ARRAY_32();
        }
    }
}

