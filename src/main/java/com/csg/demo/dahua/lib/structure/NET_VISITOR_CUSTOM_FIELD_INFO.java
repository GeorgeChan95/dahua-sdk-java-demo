package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 平台下发的访客自定义扩展字段
*/
public class NET_VISITOR_CUSTOM_FIELD_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 扩展信息字段key值
    */
    public byte[]           szCustomFieldKey = new byte[64];
    /**
     * 扩展信息文本框中文名
    */
    public byte[]           szCustomFieldName = new byte[128];
    /**
     * 此字段访客签入时，是否必填 ：必填，0：非必填 -1:未知
    */
    public int              nIsRequired;
    /**
     * 此字段是否是默认字段 1：默认字段 0：用户自定义字段 -1:未知
    */
    public int              nIsDefault;
    /**
     * 凭条是否需要打印此字段 1：需要打印，0：不需要 -1:未知
    */
    public int              nIsPrint;
    /**
     * 字段类型，0：字符串类型 -1:未知
    */
    public int              nfieldType;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[128];

    public NET_VISITOR_CUSTOM_FIELD_INFO() {
    }
}

