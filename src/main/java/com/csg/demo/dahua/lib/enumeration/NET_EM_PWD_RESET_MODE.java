package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 密码重置联系方式
*/
public enum NET_EM_PWD_RESET_MODE
{
    /**
     * 不预留联系方式
    */
    NET_EM_PWD_RESET_MODE_NOT_RESERVED(0, "不预留联系方式"),
    /**
     * 手机
    */
    NET_EM_PWD_RESET_MODE_PHONE(1, "手机"),
    /**
     * 邮箱
    */
    NET_EM_PWD_RESET_MODE_EMAIL(2, "邮箱"),
    /**
     * 继承预留的联系方式
    */
    NET_EM_PWD_RESET_MODE_INHERITANCE(3, "继承预留的联系方式");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    NET_EM_PWD_RESET_MODE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (NET_EM_PWD_RESET_MODE enumType : NET_EM_PWD_RESET_MODE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (NET_EM_PWD_RESET_MODE enumType : NET_EM_PWD_RESET_MODE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

