package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 搜索设备接口子类型
*/
public enum EM_NET_DEVICE_DISCOVERY_SUBCLASSID_TYPE
{
    /**
     * 未知
    */
    EM_NET_DEVICE_DISCOVERY_SUBCLASSID_TYPE_UNKNOWN(-1, "未知"),
    /**
     * 私有标准规范
    */
    EM_NET_DEVICE_DISCOVERY_SUBCLASSID_TYPE_NONE(0, "私有标准规范"),
    /**
     * Onvif标准规范
    */
    EM_NET_DEVICE_DISCOVERY_SUBCLASSID_TYPE_ONVIF(1, "Onvif标准规范"),
    /**
     * UPnP标准规范
    */
    EM_NET_DEVICE_DISCOVERY_SUBCLASSID_TYPE_UPNP(2, "UPnP标准规范");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_NET_DEVICE_DISCOVERY_SUBCLASSID_TYPE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_NET_DEVICE_DISCOVERY_SUBCLASSID_TYPE enumType : EM_NET_DEVICE_DISCOVERY_SUBCLASSID_TYPE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_NET_DEVICE_DISCOVERY_SUBCLASSID_TYPE enumType : EM_NET_DEVICE_DISCOVERY_SUBCLASSID_TYPE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

