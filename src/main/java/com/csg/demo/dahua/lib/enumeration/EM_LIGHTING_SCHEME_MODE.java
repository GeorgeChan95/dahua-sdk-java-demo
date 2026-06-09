package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 补光灯方案
*/
public enum EM_LIGHTING_SCHEME_MODE
{
    /**
     * 未知类型
    */
    EM_LIGHTING_SCHEME_MODE_UNKNOWN(0, "未知类型"),
    /**
     * 柔光方案
    */
    EM_LIGHTING_SCHEME_MODE_MIX(1, "柔光方案"),
    /**
     * 白光方案
    */
    EM_LIGHTING_SCHEME_MODE_WHITE(2, "白光方案"),
    /**
     * 支持柔光双色方案
    */
    EM_LIGHTING_SCHEME_MODE_NORMAL(3, "支持柔光双色方案"),
    /**
     * 红外补光方案
    */
    EM_LIGHTING_SCHEME_MODE_INFRARED(4, "红外补光方案"),
    /**
     * 智能补光方案
    */
    EM_LIGHTING_SCHEME_MODE_AIMODE(5, "智能补光方案"),
    /**
     * 关闭夜视补光方案
    */
    EM_LIGHTING_SCHEME_MODE_OFF(6, "关闭夜视补光方案");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_LIGHTING_SCHEME_MODE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_LIGHTING_SCHEME_MODE enumType : EM_LIGHTING_SCHEME_MODE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_LIGHTING_SCHEME_MODE enumType : EM_LIGHTING_SCHEME_MODE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

