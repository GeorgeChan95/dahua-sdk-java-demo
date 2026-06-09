package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 船只查询规则动作
*/
public enum NET_EM_BOAT_SEARCH_ACTION
{
    /**
     * 未知
    */
    NET_EM_BOAT_SEARCH_ACTION_UNKNOWN(0, "未知"),
    /**
     * 出现
    */
    NET_EM_BOAT_SEARCH_ACTION_APPEAR(1, "出现"),
    /**
     * 消失
    */
    NET_EM_BOAT_SEARCH_ACTION_DISAPPEAR(2, "消失"),
    /**
     * 进入
    */
    NET_EM_BOAT_SEARCH_ACTION_INSIDE(3, "进入"),
    /**
     * 穿越
    */
    NET_EM_BOAT_SEARCH_ACTION_CROSS(4, "穿越");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    NET_EM_BOAT_SEARCH_ACTION(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (NET_EM_BOAT_SEARCH_ACTION enumType : NET_EM_BOAT_SEARCH_ACTION.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (NET_EM_BOAT_SEARCH_ACTION enumType : NET_EM_BOAT_SEARCH_ACTION.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

