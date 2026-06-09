package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 船只查询方向
*/
public enum NET_EM_BOAT_SEARCH_DIRECTION
{
    NET_EM_BOAT_SEARCH_DIRECTION_UNKNOW(0, ""),
    /**
     * 左到右
    */
    NET_EM_BOAT_SEARCH_DIRECTION_LEFT_TO_RIGHT(1, "左到右"),
    /**
     * 右到左
    */
    NET_EM_BOAT_SEARCH_DIRECTION_RIGHT_TO_LEFT(2, "右到左");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    NET_EM_BOAT_SEARCH_DIRECTION(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (NET_EM_BOAT_SEARCH_DIRECTION enumType : NET_EM_BOAT_SEARCH_DIRECTION.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (NET_EM_BOAT_SEARCH_DIRECTION enumType : NET_EM_BOAT_SEARCH_DIRECTION.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

