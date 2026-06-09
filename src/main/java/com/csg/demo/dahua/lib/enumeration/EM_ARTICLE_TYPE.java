package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 检测异物类型
*/
public enum EM_ARTICLE_TYPE
{
    /**
     * 未知
    */
    EM_ARTICLE_TYPE_UNKNOWN(0, "未知"),
    /**
     * 锚杆
    */
    EM_ARTICLE_TYPE_ANCHORROD(1, "锚杆"),
    /**
     * 木头
    */
    EM_ARTICLE_TYPE_WOOD(2, "木头"),
    /**
     * 钢丝
    */
    EM_ARTICLE_TYPE_WIRE(3, "钢丝"),
    /**
     * 大块物
    */
    EM_ARTICLE_TYPE_BULK(4, "大块物"),
    /**
     * 钢筋
    */
    EM_ARTICLE_TYPE_STEELBAR(5, "钢筋"),
    /**
     * 绳子
    */
    EM_ARTICLE_TYPE_ROPE(6, "绳子"),
    /**
     * 铁块
    */
    EM_ARTICLE_TYPE_IRONBLOCK(7, "铁块");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_ARTICLE_TYPE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_ARTICLE_TYPE enumType : EM_ARTICLE_TYPE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_ARTICLE_TYPE enumType : EM_ARTICLE_TYPE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

