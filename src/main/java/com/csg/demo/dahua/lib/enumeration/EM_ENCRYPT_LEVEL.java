package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 加密等级
*/
public enum EM_ENCRYPT_LEVEL
{
    /**
     * 未知
    */
    EM_ENCRYPT_LEVEL_UNKNOWN(0, "未知"),
    /**
     * 不加密
    */
    EM_ENCRYPT_LEVEL_NONE(1, "不加密"),
    /**
     * 加密I帧前256字节
    */
    EM_ENCRYPT_LEVEL_IFRAME256(2, "加密I帧前256字节"),
    /**
     * 加密整个I帧
    */
    EM_ENCRYPT_LEVEL_IFRAME_WHOLE(3, "加密整个I帧"),
    /**
     * 对I帧, 音频帧及其他辅助帧进行加密
    */
    EM_ENCRYPT_LEVEL_IAXFRAME(4, "对I帧, 音频帧及其他辅助帧进行加密");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_ENCRYPT_LEVEL(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_ENCRYPT_LEVEL enumType : EM_ENCRYPT_LEVEL.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_ENCRYPT_LEVEL enumType : EM_ENCRYPT_LEVEL.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

