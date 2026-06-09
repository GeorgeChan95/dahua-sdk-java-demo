package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 节目内区域内容
*/
public class NET_DISPLAY_SHOW_REGIONAL_DATA extends NetSDKLib.SdkStructure
{
    /**
     * 机动车预警时文字内容
    */
    public byte[]           szRegionalContentVehicle = new byte[128];
    /**
     * 非机动车预警时文字内容
    */
    public byte[]           szRegionalContentNonMotor = new byte[128];
    /**
     * 行人预警时文字内容
    */
    public byte[]           szRegionalContentHuman = new byte[128];
    /**
     * 不预警时,屏幕提示内容
    */
    public byte[]           szRegionalReminderContent = new byte[64];
    /**
     * 区域字体大小,单位px,一般为16或32
    */
    public int              nRegionalFontSize;
    /**
     * 区域字体颜色:当为单双色颜色和七彩时,以掩码的形式传递;bit0:红灯;bit1:绿灯;bit2:黄灯.当为全彩时是RGB R的值:0-255
    */
    public int              nRegionalFontColor;
    /**
     * 预警时,屏幕显示添加内容,需要兼容Regional;Plate:车牌;Speed:速度;Distance:距离
    */
    public BYTE_ARRAY_32[]  szRegionalShowData = new BYTE_ARRAY_32[32];
    /**
     * 播报添加内容个数
    */
    public int              nRegionalShowDataNum;
    /**
     * 报警事件触发时,屏幕显示内容列表个数
    */
    public int              nRegionalContentItermsNum;
    /**
     * 报警事件触发时,屏幕显示内容列表,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_REGIONAL_CONTENTS_ITERMS}
    */
    public NET_REGIONAL_CONTENTS_ITERMS[] stuRegionalContentIterms = new NET_REGIONAL_CONTENTS_ITERMS[8];
    /**
     * 区域起始X坐标
    */
    public int              nRegionalStartX;
    /**
     * 区域起始Y坐标
    */
    public int              nRegionalStartY;
    /**
     * 区域终点X坐标
    */
    public int              nRegionalEndX;
    /**
     * 区域终点Y坐标
    */
    public int              nRegionalEndY;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_DISPLAY_SHOW_REGIONAL_DATA() {
        for(int i = 0; i < szRegionalShowData.length; i++){
            szRegionalShowData[i] = new BYTE_ARRAY_32();
        }
        for(int i = 0; i < stuRegionalContentIterms.length; i++){
            stuRegionalContentIterms[i] = new NET_REGIONAL_CONTENTS_ITERMS();
        }
    }
}

