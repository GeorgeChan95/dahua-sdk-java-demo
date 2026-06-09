package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 访客凭条打印字段配置扩展结构体
*/
public class NET_VISITOR_RECEIPT_PRINT_INFO_EX extends NetSDKLib.SdkStructure
{
    /**
     * 凭条抬头信息
    */
    public byte[]           szPrinttHead = new byte[128];
    /**
     * 凭条结尾信息
    */
    public byte[]           szPrintTail = new byte[128];
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_VISITOR_RECEIPT_PRINT_INFO_EX() {
    }
}

