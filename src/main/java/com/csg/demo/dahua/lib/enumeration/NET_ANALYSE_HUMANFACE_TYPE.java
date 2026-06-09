package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 视频支持的目标类型
*/
public enum NET_ANALYSE_HUMANFACE_TYPE
{
    /**
     * 未知类型
    */
    NET_ANALYSE_HUMANFACE_TYPE_UNKNOWN(0, "未知类型"),
    /**
     * 普通目标
    */
    NET_ANALYSE_HUMANFACE_TYPE_NORMAL(1, "普通目标"),
    /**
     * 眼部遮挡
    */
    NET_ANALYSE_HUMANFACE_TYPE_HIDEEYE(2, "眼部遮挡"),
    /**
     * 鼻子遮挡
    */
    NET_ANALYSE_HUMANFACE_TYPE_HIDENOSE(3, "鼻子遮挡"),
    /**
     * 嘴部遮挡
    */
    NET_ANALYSE_HUMANFACE_TYPE_HIDEMOUTH(4, "嘴部遮挡"),
    /**
     * 相邻目标 (注，ATM，尾随)
    */
    NET_ANALYSE_HUMANFACE_TYPE_ADJACENTFACE(5, "相邻目标 (注，ATM，尾随)"),
    /**
     * 异常目标
    */
    NET_ANALYSE_HUMANFACE_TYPE_ABNORMALFACE(6, "异常目标"),
    /**
     * 头盔目标(如摩托车盔)
    */
    NET_ANALYSE_HUMANFACE_TYPE_HELMETFACE(7, "头盔目标(如摩托车盔)");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    NET_ANALYSE_HUMANFACE_TYPE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (NET_ANALYSE_HUMANFACE_TYPE enumType : NET_ANALYSE_HUMANFACE_TYPE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (NET_ANALYSE_HUMANFACE_TYPE enumType : NET_ANALYSE_HUMANFACE_TYPE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

