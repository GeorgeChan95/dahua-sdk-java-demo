package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 屏幕区域内容显示参数
*/
public class NET_REGIONAL_DATA extends NetSDKLib.SdkStructure
{
    /**
     * 区域起始X坐标，按起点所在格子坐标计算，例如第一格X=0；第二格X=1
    */
    public int              nRegionalStartX;
    /**
     * 区域起始Y坐标，按起点所在格子坐标计算，例如第一格Y=0；第二格Y=1
    */
    public int              nRegionalStartY;
    /**
     * 区域终点X坐标，按终点所在格子坐标计算，例如第一格X=0；第二格X=1
    */
    public int              nRegionalEndX;
    /**
     * 区域终点Y坐标，按终点所在格子坐标计算，例如第一格Y=0；第二格Y=1
    */
    public int              nRegionalEndY;
    /**
     * 预警时，屏幕显示的添加内容
    */
    public byte[]           szRegionalShowData = new byte[128];
    /**
     * 文字颜色：1-红色，2-绿色，3-黄色
    */
    public int              nRegionalFontColor;
    /**
     * 区域字体大小，单位px，一般为16或32
    */
    public int              nRegionalFontSize;
    /**
     * 屏幕区域字符格式：0-半角字符，1-全角字符
    */
    public int              nRegionalStrType;
    /**
     * 内码区域类型：0x01-图片，0x0E-字符串
    */
    public int              nRegionalInnerCodeArea;
    /**
     * 图片文件名，当 nRegionalInnerCodeArea == 0x01 时有效
    */
    public byte[]           szPictureFileName = new byte[64];
    /**
     * 字体移动方式：1=立即显示，26~33=移动方向，41=闪烁
    */
    public int              nRegionalShowMethod;
    /**
     * 字体移动速度：1最快，255最慢
    */
    public int              nRegionalMoveSpeed;
    /**
     * 页面切换时单页面停留时间（单位：秒，1-255）
    */
    public int              nRegionalStayTime;
    /**
     * 预留字段
    */
    public byte[]           szReserved = new byte[1020];

    public NET_REGIONAL_DATA() {
    }
}

