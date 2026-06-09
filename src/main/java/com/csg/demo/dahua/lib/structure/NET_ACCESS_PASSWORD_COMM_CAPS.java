package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * AccessPasswordComm能力级
*/
public class NET_ACCESS_PASSWORD_COMM_CAPS extends NetSDKLib.SdkStructure
{
    /**
     * 每次最大插入的记录个数 参考 NET_ACCESS_PASSWORD_COMM_CAPS结构体中nMaxInsertRate取值
    */
    public int              nMaxInsertRate;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[252];

    public NET_ACCESS_PASSWORD_COMM_CAPS() {
    }
}

