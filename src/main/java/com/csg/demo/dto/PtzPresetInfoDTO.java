package com.csg.demo.dto;

import lombok.Data;

/**
 * 云台预置点信息返回对象。
 */
@Data
public class PtzPresetInfoDTO {
    /** 预置点编号。 */
    private int index;

    /** 预置点名称。 */
    private String name;

    /** 水平坐标，范围通常为 0~3599，表示 0~359.9 度。 */
    private int horizontal;

    /** 垂直坐标，范围通常为 -1800~1800，表示 -180.0~180.0 度。 */
    private int vertical;

    /** 变倍位置，范围通常为 0~128。 */
    private int zoom;
}
