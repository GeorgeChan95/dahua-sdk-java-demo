package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 平台下发的访客自定义扩展字段
*/
public class NET_VISITOR_CUSTOM_FIELD extends NetSDKLib.SdkStructure
{
    /**
     * 扩展信息字段key值
    */
    public byte[]           szCustomFieldKey = new byte[64];
    /**
     * 扩展信息字段Value值
    */
    public byte[]           szCustomFieldValue = new byte[256];
    /**
     * 扩展信息字段Value值扩展, 内存由用户/SDK申请释放，最大204800
    */
    public Pointer          pszCustomFieldValueEx;
    /**
     * 是否使用扩展信息字段Value值扩展
    */
    public int              bUseCustomFieldValueEx;
    /**
     * 扩展字段类型,-1:无此字段,0：字符串类型，1:选项 ,2:数字 ,3:日期,4: 图片
    */
    public int              nFieldType;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[120-NetSDKLib.POINTERSIZE];

    public NET_VISITOR_CUSTOM_FIELD() {
    }
}

