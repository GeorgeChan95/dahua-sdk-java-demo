package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 访客记录列表
*/
public class NET_VISITOR_RECORD_LIST_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 查询记录总页数
    */
    public int              nTotalPage;
    /**
     * 每一页记录条数
    */
    public int              nPageSize;
    /**
     * 记录总数
    */
    public int              nTotalRows;
    /**
     * 当前页码
    */
    public int              nCurrentPage;
    /**
     * 当前页记录数据,用户申请空间，大小为nPageNum*sizeof(NET_VISITOR_RECORD_PAGE_INFO),参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISITOR_RECORD_PAGE_INFO}
    */
    public Pointer          pstuPageData;
    /**
     * 当前页记录数据数量
    */
    public int              nPageNum;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[124-NetSDKLib.POINTERSIZE];

    public NET_VISITOR_RECORD_LIST_INFO() {
    }
}

