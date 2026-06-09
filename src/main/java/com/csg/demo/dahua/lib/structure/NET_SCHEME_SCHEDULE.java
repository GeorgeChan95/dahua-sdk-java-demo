package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 灯光方案按时间段切换
*/
public class NET_SCHEME_SCHEDULE extends NetSDKLib.SdkStructure
{
    /**
     * 是否生效 （按时间表生效后，原LightingMode不再生效）
    */
    public int              bEnable;
    /**
     * 生效的时间段，表示一周七天(数组下标0对应周日，后依次为周一到周六),参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_TIME_SECTION_BY_WEEK}
    */
    public NET_TIME_SECTION_BY_WEEK[] stuInfo = new NET_TIME_SECTION_BY_WEEK[7];
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_SCHEME_SCHEDULE() {
        for(int i = 0; i < stuInfo.length; i++){
            stuInfo[i] = new NET_TIME_SECTION_BY_WEEK();
        }
    }
}

