package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 通道类型
*/
public enum NET_LOGIC_CHN_TYPE
{
    /**
     * 未知
    */
    LOGIC_CHN_UNKNOWN(0, "未知"),
    /**
     * 本地通道
    */
    LOGIC_CHN_LOCAL(1, "本地通道"),
    /**
     * 远程通道
    */
    LOGIC_CHN_REMOTE(2, "远程通道"),
    /**
     * 合成通道, 对于庭审设备包含画中画通道和混音通道
    */
    LOGIC_CHN_COMPOSE(3, "合成通道, 对于庭审设备包含画中画通道和混音通道"),
    /**
     * 模拟矩阵通道
    */
    LOGIC_CHN_MATRIX(4, "模拟矩阵通道"),
    /**
     * 级联通道
    */
    LOGIC_CHN_CASCADE(5, "级联通道");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    NET_LOGIC_CHN_TYPE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (NET_LOGIC_CHN_TYPE enumType : NET_LOGIC_CHN_TYPE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (NET_LOGIC_CHN_TYPE enumType : NET_LOGIC_CHN_TYPE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

