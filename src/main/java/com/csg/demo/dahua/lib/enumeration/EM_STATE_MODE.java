package com.csg.demo.dahua.lib.enumeration;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 文件当前状态
*/
public enum EM_STATE_MODE
{
    /**
     * 未知
    */
    EM_STATE_MODE_UNKNOWN(0, "未知"),
    /**
     * 等待上传下载
    */
    EM_STATE_MODE_PREPARING(1, "等待上传下载"),
    /**
     * 上传下载中
    */
    EM_STATE_MODE_LOADING(2, "上传下载中"),
    /**
     * 上传下载完成
    */
    EM_STATE_MODE_SUCCEEDED(3, "上传下载完成"),
    /**
     * 上传下载失败
    */
    EM_STATE_MODE_FAILED(4, "上传下载失败"),
    /**
     * 上传下载取消
    */
    EM_STATE_MODE_CANCELLED(5, "上传下载取消");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_STATE_MODE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_STATE_MODE enumType : EM_STATE_MODE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_STATE_MODE enumType : EM_STATE_MODE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

