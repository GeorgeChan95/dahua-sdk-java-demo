package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 水位状态
*/
public enum NET_EM_WATER_STATUS
{
    /**
     * 未知
    */
    NET_EM_WATER_STATUS_UNKNOWN(0, "未知"),
    /**
     * 正常
    */
    NET_EM_WATER_STATUS_NORMAL(1, "正常"),
    /**
     * 高于上限阈值
    */
    NET_EM_WATER_STATUS_ABOVE_UPPER_THRESHOLD(2, "高于上限阈值"),
    /**
     * 低于下限阈值
    */
    NET_EM_WATER_STATUS_BELOW_LOWER_THRESHOLD(3, "低于下限阈值");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    NET_EM_WATER_STATUS(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (NET_EM_WATER_STATUS enumType : NET_EM_WATER_STATUS.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (NET_EM_WATER_STATUS enumType : NET_EM_WATER_STATUS.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

