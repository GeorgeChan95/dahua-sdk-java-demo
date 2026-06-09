package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 代理功能
*/
public enum EM_CENTRA_PROXY_ACTION
{
    /**
     * 未知
    */
    EM_CENTRA_PROXY_ACTION_UNKNOWN(-1, "未知"),
    /**
     * 代理开锁功能
    */
    EM_CENTRA_PROXY_ACTION_UNBLANKING(0, "代理开锁功能"),
    /**
     * 代理视频预览功能
    */
    EM_CENTRA_PROXY_ACTION_PRIVIEW(1, "代理视频预览功能"),
    /**
     * 访客机数据传输功能
    */
    EM_CENTRA_PROXY_ACTION_DATA_TRANSMIT(2, "访客机数据传输功能");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_CENTRA_PROXY_ACTION(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_CENTRA_PROXY_ACTION enumType : EM_CENTRA_PROXY_ACTION.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_CENTRA_PROXY_ACTION enumType : EM_CENTRA_PROXY_ACTION.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

