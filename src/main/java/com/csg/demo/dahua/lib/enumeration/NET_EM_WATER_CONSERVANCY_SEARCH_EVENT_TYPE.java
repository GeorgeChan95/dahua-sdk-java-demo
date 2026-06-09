package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 水利查询事件类型
*/
public enum NET_EM_WATER_CONSERVANCY_SEARCH_EVENT_TYPE
{
    /**
     * 未知
    */
    NET_EM_WATER_CONSERVANCY_SEARCH_EVENT_TYPE_UNKNOWN(0, "未知"),
    /**
     * 漂浮物检测
    */
    NET_EM_WATER_CONSERVANCY_SEARCH_EVENT_TYPE_FLOATING_OBJECT_DETECTION(1, "漂浮物检测"),
    /**
     * 虚拟水尺检测
    */
    NET_EM_WATER_CONSERVANCY_SEARCH_EVENT_TYPE_WATER_LEVEL_DETECTION(2, "虚拟水尺检测"),
    /**
     * 排水检测
    */
    NET_EM_WATER_CONSERVANCY_SEARCH_EVENT_TYPE_SEWAGE_DETECTION(3, "排水检测"),
    /**
     * 水体颜色检测
    */
    NET_EM_WATER_CONSERVANCY_SEARCH_EVENT_TYPE_WATER_COLOR_DETECTION(4, "水体颜色检测");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    NET_EM_WATER_CONSERVANCY_SEARCH_EVENT_TYPE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (NET_EM_WATER_CONSERVANCY_SEARCH_EVENT_TYPE enumType : NET_EM_WATER_CONSERVANCY_SEARCH_EVENT_TYPE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (NET_EM_WATER_CONSERVANCY_SEARCH_EVENT_TYPE enumType : NET_EM_WATER_CONSERVANCY_SEARCH_EVENT_TYPE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

