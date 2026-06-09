package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_InitPwdAgentAccount 接口输入参数
*/
public class NET_IN_INIT_PWD_AGENT_ACCOUNT_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * IPC列表数量
    */
    public int              nListNum;
    /**
     * 需要初始化的IPC列表,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_IPC_INIT_INFO}
    */
    public NET_IPC_INIT_INFO[] stuList = new NET_IPC_INIT_INFO[128];
    /**
     * 密码明文
    */
    public byte[]           szPwd = new byte[128];
    /**
     * 是否使用预置密码初始化,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.NET_EM_USE_PRESECRET}
    */
    public int              emUsePreSecret;
    /**
     * 密码重置联系方式,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.NET_EM_PWD_RESET_MODE}
    */
    public int              emMode;
    /**
     * 预留手机号/邮箱,只在mode为1或2时需要
    */
    public byte[]           szContact = new byte[256];

    public NET_IN_INIT_PWD_AGENT_ACCOUNT_INFO() {
        this.dwSize = this.size();
        for(int i = 0; i < stuList.length; i++){
            stuList[i] = new NET_IPC_INIT_INFO();
        }
    }
}

