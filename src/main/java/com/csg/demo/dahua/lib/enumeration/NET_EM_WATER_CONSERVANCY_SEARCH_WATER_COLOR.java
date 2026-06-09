package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 水体颜色检测搜索颜色
*/
public enum NET_EM_WATER_CONSERVANCY_SEARCH_WATER_COLOR
{
    /**
     * 未知
    */
    NET_EM_WATER_CONSERVANCY_SEARCH_WATER_COLOR_UNKNOWN(0, "未知"),
    /**
     * 黑色
    */
    NET_EM_WATER_CONSERVANCY_SEARCH_WATER_COLOR_BLACK(1, "黑色"),
    /**
     * 红色
    */
    NET_EM_WATER_CONSERVANCY_SEARCH_WATER_COLOR_RED(2, "红色"),
    /**
     * 绿色
    */
    NET_EM_WATER_CONSERVANCY_SEARCH_WATER_COLOR_GREEN(3, "绿色"),
    /**
     * 黄色
    */
    NET_EM_WATER_CONSERVANCY_SEARCH_WATER_COLOR_YELLOW(4, "黄色"),
    /**
     * 查询全部
    */
    NET_EM_WATER_CONSERVANCY_SEARCH_WATER_COLOR_ALL(5, "查询全部");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    NET_EM_WATER_CONSERVANCY_SEARCH_WATER_COLOR(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (NET_EM_WATER_CONSERVANCY_SEARCH_WATER_COLOR enumType : NET_EM_WATER_CONSERVANCY_SEARCH_WATER_COLOR.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (NET_EM_WATER_CONSERVANCY_SEARCH_WATER_COLOR enumType : NET_EM_WATER_CONSERVANCY_SEARCH_WATER_COLOR.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

