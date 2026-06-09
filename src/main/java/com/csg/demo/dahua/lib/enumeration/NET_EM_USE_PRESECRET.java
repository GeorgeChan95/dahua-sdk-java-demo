package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 是否使用预置密码初始化
*/
public enum NET_EM_USE_PRESECRET
{
    /**
     * 不使用预置密码
    */
    NET_EM_USE_PRESECRET_UNUSE(0, "不使用预置密码"),
    /**
     * 使用预置密码，此时pwd无效
    */
    NET_EM_USE_PRESECRET_USE(1, "使用预置密码，此时pwd无效"),
    /**
     * 使用相机登录密码，此时pwd无效
    */
    NET_EM_USE_PRESECRET_USE_CAMERA(2, "使用相机登录密码，此时pwd无效"),
    /**
     * 使用IOT设备的登录密码，此时pwd无效
    */
    NET_EM_USE_PRESECRET_USE_IOT(3, "使用IOT设备的登录密码，此时pwd无效");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    NET_EM_USE_PRESECRET(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (NET_EM_USE_PRESECRET enumType : NET_EM_USE_PRESECRET.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (NET_EM_USE_PRESECRET enumType : NET_EM_USE_PRESECRET.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

