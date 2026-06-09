package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 错误码
*/
public enum EM_CENTRA_PROXY_CODE
{
    /**
     * 未知
    */
    EM_CENTRA_PROXY_CODE_UNKNOWN(-1, "未知"),
    /**
     * 成功
    */
    EM_CENTRA_PROXY_CODE_SUCCESS(0, "成功"),
    /**
     * 失败
    */
    EM_CENTRA_PROXY_CODE_FAIL(1, "失败");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_CENTRA_PROXY_CODE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_CENTRA_PROXY_CODE enumType : EM_CENTRA_PROXY_CODE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_CENTRA_PROXY_CODE enumType : EM_CENTRA_PROXY_CODE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

