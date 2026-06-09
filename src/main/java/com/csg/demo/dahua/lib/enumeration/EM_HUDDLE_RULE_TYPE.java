package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 检测类型
*/
public enum EM_HUDDLE_RULE_TYPE
{
    /**
     * 未知
    */
    EM_HUDDLE_RULE_UNKNOWN(0, "未知"),
    /**
     * 工地物料乱堆放
    */
    EM_HUDDLE_RULE_CONSTRUCTION_SITE(1, "工地物料乱堆放");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_HUDDLE_RULE_TYPE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_HUDDLE_RULE_TYPE enumType : EM_HUDDLE_RULE_TYPE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_HUDDLE_RULE_TYPE enumType : EM_HUDDLE_RULE_TYPE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

