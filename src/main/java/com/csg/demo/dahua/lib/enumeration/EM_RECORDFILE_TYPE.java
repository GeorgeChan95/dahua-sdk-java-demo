package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 文件类型
*/
public enum EM_RECORDFILE_TYPE
{
    /**
     * 普通录像
    */
    EM_RECORDFILE_TYPE_NORMAL(0, "普通录像"),
    /**
     * 报警录像
    */
    EM_RECORDFILE_TYPE_ALARM(1, "报警录像"),
    /**
     * 移动检测录像
    */
    EM_RECORDFILE_TYPE_MOVE_DETECT(2, "移动检测录像"),
    /**
     * 卡号录像
    */
    EM_RECORDFILE_TYPE_CARD(3, "卡号录像"),
    /**
     * 图片
    */
    EM_RECORDFILE_TYPE_PIC(4, "图片"),
    /**
     * 音频
    */
    EM_RECORDFILE_TYPE_AUDIO(5, "音频");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_RECORDFILE_TYPE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_RECORDFILE_TYPE enumType : EM_RECORDFILE_TYPE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_RECORDFILE_TYPE enumType : EM_RECORDFILE_TYPE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

