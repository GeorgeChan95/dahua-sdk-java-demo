package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 订阅气象信息输入参数
*/
public class NET_IN_WEATHER_INFO extends NetSDKLib.SdkStructure
{
    public int              dwSize;
    /**
     * 云台通道
    */
    public int              nChannel;
    /**
     * 回调函数,参见回调函数定义 {@link com.csg.demo.dahua.lib.NetSDKLib.fWeatherInfoCallBack}
    */
    public NetSDKLib.fWeatherInfoCallBack cbWeatherInfo;
    /**
     * 用户数据
    */
    public Pointer          dwUser;

    public NET_IN_WEATHER_INFO() {
        this.dwSize = this.size();
    }
}

