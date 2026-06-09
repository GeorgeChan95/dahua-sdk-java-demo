package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 录像类型
*/
public enum EM_SUB_MODE
{
    /**
     * 未知
    */
    EM_SUB_UNKNOWN(-1, "未知"),
    /**
     * 不录像
    */
    EM_SUB_NORECORD(0, "不录像"),
    /**
     * 普通连续录像
    */
    EM_SUB_NORMAL_COTINUE_RECORD(1, "普通连续录像"),
    /**
     * 事件触发录像
    */
    EM_SUB_EVENT_TRIGGER_RECORD(2, "事件触发录像"),
    /**
     * 计划定时录像
    */
    EM_SUB_PLAN_REGULAR_RECORD(3, "计划定时录像"),
    /**
     * 事件触发录像和定时计划录像
    */
    EM_SUB_EVENT_TRIGGER_AND_PLAN_REGULAR_RECORD(4, "事件触发录像和定时计划录像");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_SUB_MODE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_SUB_MODE enumType : EM_SUB_MODE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_SUB_MODE enumType : EM_SUB_MODE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

