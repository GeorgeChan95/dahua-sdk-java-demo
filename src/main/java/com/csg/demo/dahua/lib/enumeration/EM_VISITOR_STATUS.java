package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 访客状态
*/
public enum EM_VISITOR_STATUS
{
    /**
     * 未知
    */
    EM_VISITOR_STATUS_UNKNOWN(-1, "未知"),
    /**
     * 成功
    */
    EM_VISITOR_STATUS_AUDIT(0, "成功"),
    /**
     * 失败
    */
    EM_VISITOR_STATUS_RESERVATION(1, "失败"),
    /**
     * 在访
    */
    EM_VISITOR_STATUS_VISITING(2, "在访"),
    /**
     * 离访
    */
    EM_VISITOR_STATUS_DEPARTURE_VISIT(3, "离访");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_VISITOR_STATUS(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_VISITOR_STATUS enumType : EM_VISITOR_STATUS.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_VISITOR_STATUS enumType : EM_VISITOR_STATUS.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

