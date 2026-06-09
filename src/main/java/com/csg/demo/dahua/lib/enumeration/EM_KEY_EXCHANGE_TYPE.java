package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 密钥交换方式
*/
public enum EM_KEY_EXCHANGE_TYPE
{
    /**
     * 未知
    */
    EM_KEY_EXCHANGE_UNKNOWN(0, "未知"),
    /**
     * Mikey密钥
    */
    EM_KEY_EXCHANGE_MIKEY(1, "Mikey密钥"),
    /**
     * 预共享密钥
    */
    EM_KEY_EXCHANGE_PSK(2, "预共享密钥"),
    /**
     * 公共密钥
    */
    EM_KEY_EXCHANGE_PK(3, "公共密钥"),
    /**
     * 霍夫曼密钥
    */
    EM_KEY_EXCHANGE_DH(4, "霍夫曼密钥");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_KEY_EXCHANGE_TYPE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_KEY_EXCHANGE_TYPE enumType : EM_KEY_EXCHANGE_TYPE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_KEY_EXCHANGE_TYPE enumType : EM_KEY_EXCHANGE_TYPE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

