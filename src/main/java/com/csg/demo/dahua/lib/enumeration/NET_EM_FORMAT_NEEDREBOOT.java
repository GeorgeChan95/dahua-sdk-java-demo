package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 格式化硬盘后是否需要重启
*/
public enum NET_EM_FORMAT_NEEDREBOOT
{
    /**
     * 未知
    */
    EM_FORMAT_NEEDREBOOT_UNKNOWN(0, "未知"),
    /**
     * 不需要重启
    */
    EM_FORMAT_NEEDREBOOT_NOREBOOT(1, "不需要重启"),
    /**
     * 需要重启, 调用CLIENT_ControlDevice格式化使用命令DH_CTRL_SDCARD或DH_CTRL_DISK后自动执行重启，, 使用命令DH_CTRL_FORMAT_PATITION需要客户端发起重启
    */
    EM_FORMAT_NEEDREBOOT_REBOOT(2, "需要重启, 调用CLIENT_ControlDevice格式化使用命令DH_CTRL_SDCARD或DH_CTRL_DISK后自动执行重启，, 使用命令DH_CTRL_FORMAT_PATITION需要客户端发起重启");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    NET_EM_FORMAT_NEEDREBOOT(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (NET_EM_FORMAT_NEEDREBOOT enumType : NET_EM_FORMAT_NEEDREBOOT.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (NET_EM_FORMAT_NEEDREBOOT enumType : NET_EM_FORMAT_NEEDREBOOT.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

