package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 指码流传输的服务类型
*/
public enum EM_STREAM_TRANSMISSION_SERVICE_TYPE
{
    /**
     * 未知
    */
    EM_STREAM_TRANSMISSION_SERVICE_TYPE_UNKNOWN(0, "未知"),
    /**
     * TCP
    */
    EM_STREAM_TRANSMISSION_SERVICE_TYPE_TCP(1, "TCP"),
    /**
     * UDP
    */
    EM_STREAM_TRANSMISSION_SERVICE_TYPE_UDP(2, "UDP"),
    /**
     * MCAST
    */
    EM_STREAM_TRANSMISSION_SERVICE_TYPE_MCAST(3, "MCAST"),
    /**
     * AUTO
    */
    EM_STREAM_TRANSMISSION_SERVICE_TYPE_AUTO(4, "AUTO");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_STREAM_TRANSMISSION_SERVICE_TYPE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_STREAM_TRANSMISSION_SERVICE_TYPE enumType : EM_STREAM_TRANSMISSION_SERVICE_TYPE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_STREAM_TRANSMISSION_SERVICE_TYPE enumType : EM_STREAM_TRANSMISSION_SERVICE_TYPE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

