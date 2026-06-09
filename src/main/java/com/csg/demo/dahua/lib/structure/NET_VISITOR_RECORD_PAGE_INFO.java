package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 当前页记录数据
*/
public class NET_VISITOR_RECORD_PAGE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 访客记录,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_NEW_VISITOR_INFO}
    */
    public NET_NEW_VISITOR_INFO stuVisitorRecords = new NET_NEW_VISITOR_INFO();
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[128];

    public NET_VISITOR_RECORD_PAGE_INFO() {
    }
}

