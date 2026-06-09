package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 消警类型
*/
public enum EM_ALARM_CLEAR_TYPE
{
    /**
     * 未知
    */
    EM_ALARM_CLEAR_TYPE_UNKNOWN(0, "未知"),
    /**
     * 全消警
    */
    EM_ALARM_CLEAR_TYPE_ALL(1, "全消警"),
    /**
     * 子系统消警
    */
    EM_ALARM_CLEAR_TYPE_ALARM_AREA(2, "子系统消警"),
    /**
     * 单防区消警
    */
    EM_ALARM_CLEAR_TYPE_ALARM_LOCAL(3, "单防区消警");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_ALARM_CLEAR_TYPE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_ALARM_CLEAR_TYPE enumType : EM_ALARM_CLEAR_TYPE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_ALARM_CLEAR_TYPE enumType : EM_ALARM_CLEAR_TYPE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

