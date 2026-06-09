package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 访客信息凭条打印顺序
*/
public class NET_VISITOR_PRINT_ORDER_INFO extends NetSDKLib.SdkStructure
{
    /**
     * CustomFieldKey的值，空字符为无效值; CustomKey和DefindKey只能选择一个
    */
    public byte[]           szCustomKey = new byte[64];
    /**
     * DefindVisitorInfo字段中的key值，0为无效值; CustomKey和DefindKey只能选择一个,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.NET_EM_DEFINED_VISITOR_KEY}
    */
    public int              emDefindKey;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[252];

    public NET_VISITOR_PRINT_ORDER_INFO() {
    }
}

