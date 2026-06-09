package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_FindObjectMediaFindNearEvents 接口输入参数
*/
public class NET_IN_FIND_OBJECT_MEDIA_FIND_NEAR_EVENTS extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 通道号，从0开始
    */
    public int              nChannel;
    /**
     * 指定要查询的个数
    */
    public int              nCount;
    /**
     * 获取方式，0-获取GetTime两侧的事件；1-基于上一次查询结果往后查询，首次查询从索引位置0开始；2-基于上一次查询结果往前查询，首次查询返回空
    */
    public int              nGetMode;
    /**
     * 指定的时间点，UTC时间,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuGetTimeRealUTC = new NetSDKLib.NET_TIME();

    public NET_IN_FIND_OBJECT_MEDIA_FIND_NEAR_EVENTS() {
        this.dwSize = this.size();
    }
}

