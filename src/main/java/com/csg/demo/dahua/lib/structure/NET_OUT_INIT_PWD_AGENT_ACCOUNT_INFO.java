package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_InitPwdAgentAccount 接口输出参数
*/
public class NET_OUT_INIT_PWD_AGENT_ACCOUNT_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 批量初始化会话标识，用于查询初始化进度
    */
    public int              nToken;

    public NET_OUT_INIT_PWD_AGENT_ACCOUNT_INFO() {
        this.dwSize = this.size();
    }
}

