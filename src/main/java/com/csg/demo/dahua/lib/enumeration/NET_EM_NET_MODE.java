package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * ,**************************用户密码接口Start************************************************,, 初始化方式
*/
public enum NET_EM_NET_MODE
{
    /**
     * 未知
    */
    NET_EM_NET_MODE_UNKNOWN(-1, "未知"),
    /**
     * 组播初始化
    */
    NET_EM_NET_MODE_MULTICAST(0, "组播初始化"),
    /**
     * 单播初始化
    */
    NET_EM_NET_MODE_UNICAST(1, "单播初始化");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    NET_EM_NET_MODE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (NET_EM_NET_MODE enumType : NET_EM_NET_MODE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (NET_EM_NET_MODE enumType : NET_EM_NET_MODE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

