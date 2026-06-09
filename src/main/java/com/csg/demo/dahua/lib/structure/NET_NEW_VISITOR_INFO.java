package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 访客信息
*/
public class NET_NEW_VISITOR_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 姓名
    */
    public byte[]           szName = new byte[32];
    /**
     * 电话号码
    */
    public byte[]           szPhone = new byte[16];
    /**
     * 单位
    */
    public byte[]           szCompany = new byte[128];
    /**
     * 证件,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISITOR_CREDENTIALS_INFO}
    */
    public NET_VISITOR_CREDENTIALS_INFO stCredentials = new NET_VISITOR_CREDENTIALS_INFO();
    /**
     * 住址
    */
    public byte[]           szAddress = new byte[128];
    /**
     * 来访原因
    */
    public byte[]           szReason = new byte[256];
    /**
     * 车牌
    */
    public byte[]           szCarNumber = new byte[32];
    /**
     * 携带物品
    */
    public byte[]           szItems = new byte[256];
    /**
     * 被访问目标信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISITOR_TARGET_INFO}
    */
    public NET_VISITOR_TARGET_INFO stTarget = new NET_VISITOR_TARGET_INFO();
    /**
     * 时间相关信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISITOR_TIME_INFO}
    */
    public NET_VISITOR_TIME_INFO stTimeInfo = new NET_VISITOR_TIME_INFO();
    /**
     * 访客授权访问区域, 废弃, 使用pstuAreaIdList
    */
    public BYTE_ARRAY_64[]  szAreaIdList = new BYTE_ARRAY_64[64];
    /**
     * 访客授权访问区域有效个数
    */
    public int              nAreaIdListNum;
    /**
     * 访客类型ID
    */
    public byte[]           szVisitorTypeID = new byte[64];
    /**
     * 邀请码(Base64)
    */
    public byte[]           szInviteCode = new byte[256];
    /**
     * 通行码(Base64)
    */
    public byte[]           szAccessCode = new byte[256];
    /**
     * 随行人员信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISITOR_ENTOURAGES_INFO}
    */
    public NET_VISITOR_ENTOURAGES_INFO[] stEntourages = new NET_VISITOR_ENTOURAGES_INFO[32];
    /**
     * 随行人员信息有效个数
    */
    public int              nEntouragesNum;
    /**
     * 岗亭ID
    */
    public byte[]           szBoxID = new byte[64];
    /**
     * 访客状态,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.EM_VISITOR_STATUS}
    */
    public int              emStatus;
    /**
     * 性别,参见枚举定义 {@link com.csg.demo.dahua.lib.NetSDKLib.EM_SEX_TYPE}
    */
    public int              emSex;
    /**
     * 图片信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISITOR_PICTURE_INFO}
    */
    public NET_VISITOR_PICTURE_INFO stPicture = new NET_VISITOR_PICTURE_INFO();
    /**
     * 访客ID
    */
    public byte[]           szID = new byte[64];
    /**
     * 平台下发的访客自定义扩展字段个数
    */
    public int              nCustomFieldNum;
    /**
     * 平台下发的访客自定义扩展字段,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISITOR_CUSTOM_FIELD}
    */
    public NET_VISITOR_CUSTOM_FIELD[] stuCustomField = new NET_VISITOR_CUSTOM_FIELD[20];
    /**
     * 访客下发的事物卡的卡号
    */
    public byte[]           szCardNo = new byte[32];
    /**
     * 访客温度信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISITOR_TEMPERATURE_INFO}
    */
    public NET_VISITOR_TEMPERATURE_INFO stuVisitorTemperatureInfo = new NET_VISITOR_TEMPERATURE_INFO();
    /**
     * 来访人数
    */
    public int              nPersonSum;
    /**
     * 访问次数，用于限制访客门禁开门次数
    */
    public int              nVisitTime;
    /**
     * 访客访问类型, 临时访问不能跨天, 0:未知, 1:临时, 2:长期
    */
    public int              nVisitType;
    /**
     * MZ
    */
    public byte[]           szNation = new byte[32];
    /**
     * 访客类型名称
    */
    public byte[]           szVisitorTypeName = new byte[128];
    /**
     * 姓名扩展
    */
    public byte[]           szNameEx = new byte[128];
    /**
     * 是否使用姓名扩展
    */
    public int              nUseNameEx;
    /**
     * 访客授权访问区域列表, 最大支持64个访问区域, 请求时/回调时，内存由用户/SDK申请释放, 有效个数由nAreaIdListNum2控制,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VISITOR_AREA_ID_LIST_INFO}
    */
    public Pointer          pstuAreaIdList;
    /**
     * 访客授权访问区域有效个数
    */
    public int              nAreaIdListNum2;
    /**
     * 电子邮箱
    */
    public byte[]           szEmail = new byte[256];
    /**
     * 访客扩展信息，需访客业务支持超长的访客信息，由用户申请和释放内存，内存大小为sizeof(NET_NEW_VISITOR_EXTEND_INFO),参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_NEW_VISITOR_EXTEND_INFO}
    */
    public Pointer          pstuVisitorExtendInfo;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[472-2*NetSDKLib.POINTERSIZE];

    public NET_NEW_VISITOR_INFO() {
        for(int i = 0; i < szAreaIdList.length; i++){
            szAreaIdList[i] = new BYTE_ARRAY_64();
        }
        for(int i = 0; i < stEntourages.length; i++){
            stEntourages[i] = new NET_VISITOR_ENTOURAGES_INFO();
        }
        for(int i = 0; i < stuCustomField.length; i++){
            stuCustomField[i] = new NET_VISITOR_CUSTOM_FIELD();
        }
    }
}

