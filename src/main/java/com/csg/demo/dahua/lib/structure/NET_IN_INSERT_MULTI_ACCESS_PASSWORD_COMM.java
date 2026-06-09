package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_InsertMultiAccessPasswordComm接口输入参数
*/
public class NET_IN_INSERT_MULTI_ACCESS_PASSWORD_COMM extends NetSDKLib.SdkStructure
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
     * 下发用户信息列表大小
    */
    public int              nAccessPasswordCommNum;
    /**
     * 下发用户信息列表,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_ACCESS_PASSWORD_COMM}
    */
    public NET_ACCESS_PASSWORD_COMM[] stuAccessPasswordComm = new NET_ACCESS_PASSWORD_COMM[256];

    public NET_IN_INSERT_MULTI_ACCESS_PASSWORD_COMM() {
        this.dwSize = this.size();
        for(int i = 0; i < stuAccessPasswordComm.length; i++){
            stuAccessPasswordComm[i] = new NET_ACCESS_PASSWORD_COMM();
        }
    }
}

