package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 船只查询规则
*/
public enum NET_EM_BOAT_SEARCH_RULE
{
    /**
     * 未知
    */
    NET_EM_BOAT_SEARCH_RULE_UNKNOWN(0, "未知"),
    /**
     * 船只拌线
    */
    NET_EM_BOAT_SEARCH_RULE_CROSS_LINE_DETECTION(1, "船只拌线"),
    /**
     * 船只入侵
    */
    NET_EM_BOAT_SEARCH_RULE_CROSS_REGION_DETECTION(2, "船只入侵"),
    /**
     * 船只停留
    */
    NET_EM_BOAT_SEARCH_RULE_PARKING_DETECTION(3, "船只停留"),
    /**
     * 钓鱼检测
    */
    NET_EM_BOAT_SEARCH_RULE_FISHING_DETECTION(4, "钓鱼检测");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    NET_EM_BOAT_SEARCH_RULE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (NET_EM_BOAT_SEARCH_RULE enumType : NET_EM_BOAT_SEARCH_RULE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (NET_EM_BOAT_SEARCH_RULE enumType : NET_EM_BOAT_SEARCH_RULE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

