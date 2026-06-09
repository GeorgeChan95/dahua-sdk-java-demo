package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 初始化方式
*/
public enum NET_EM_BIND_CLIENT_ADDRWAY
{
    /**
     * 未知
    */
    NET_EM_BIND_CLIENT_ADDRWAY_UNKNOWN(-1, "未知"),
    /**
     * 仅修改IP阶段绑定网卡，默认方式
    */
    NET_EM_BIND_CLIENT_ADDRWAY_DEFAULT(0, "仅修改IP阶段绑定网卡，默认方式"),
    /**
     * 仅修改网关阶段绑定网卡
    */
    NET_EM_BIND_CLIENT_ADDRWAY_GATEWAY(1, "仅修改网关阶段绑定网卡"),
    /**
     * 修改IP、网关阶段都绑定网卡
    */
    NET_EM_BIND_CLIENT_ADDRWAY_BOTH(2, "修改IP、网关阶段都绑定网卡");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    NET_EM_BIND_CLIENT_ADDRWAY(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (NET_EM_BIND_CLIENT_ADDRWAY enumType : NET_EM_BIND_CLIENT_ADDRWAY.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (NET_EM_BIND_CLIENT_ADDRWAY enumType : NET_EM_BIND_CLIENT_ADDRWAY.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

