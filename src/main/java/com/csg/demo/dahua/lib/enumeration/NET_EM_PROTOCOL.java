package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 接入设备协议类型
*/
public enum NET_EM_PROTOCOL
{
    /**
     * 未知
    */
    NET_NET_EM_PROTOCOL_UNKNOWN(-1, "未知"),
    /**
     * 默认大华设备
    */
    NET_NET_EM_PROTOCOL_DEFAULT(0, "默认大华设备"),
    /**
     * 海康设备
    */
    NET_NET_EM_PROTOCOL_OTHER(1, "海康设备");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    NET_EM_PROTOCOL(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (NET_EM_PROTOCOL enumType : NET_EM_PROTOCOL.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (NET_EM_PROTOCOL enumType : NET_EM_PROTOCOL.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

