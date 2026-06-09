package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 码流加密方式
*/
public enum EM_ENCRYPT_ALGORITHM_TYPE
{
    /**
     * 未知
    */
    EM_ENCRYPT_ALGORITHM_UNKNOWN(0, "未知"),
    /**
     * AES加密
    */
    EM_ENCRYPT_ALGORITHM_AES(1, "AES加密"),
    /**
     * DES加密
    */
    EM_ENCRYPT_ALGORITHM_DES(2, "DES加密"),
    /**
     * 3DES加密
    */
    EM_ENCRYPT_ALGORITHM_3DES(3, "3DES加密");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_ENCRYPT_ALGORITHM_TYPE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_ENCRYPT_ALGORITHM_TYPE enumType : EM_ENCRYPT_ALGORITHM_TYPE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_ENCRYPT_ALGORITHM_TYPE enumType : EM_ENCRYPT_ALGORITHM_TYPE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

