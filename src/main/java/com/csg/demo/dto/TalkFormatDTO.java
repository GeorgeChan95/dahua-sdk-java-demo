package com.csg.demo.dto;

import lombok.Data;

/**
 * 设备支持的语音对讲编码格式。
 */
@Data
public class TalkFormatDTO {
    /** SDK 编码类型值。 */
    private int encodeType;

    /** 编码名称，如 G711A、G711U。 */
    private String name;

    /** 采样位数。 */
    private int audioBit;

    /** 采样率。 */
    private int sampleRate;

    /** 打包周期，单位毫秒。 */
    private int packetPeriod;
}
