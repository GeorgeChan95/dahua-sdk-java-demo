package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 人脸目标识别防假参数配置, 对应 NET_CFG_FACE_ANTIFAKE_STRATEGY_INFO
*/
public class NET_CFG_FACE_ANTIFAKE_STRATEGY_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 防假等级和防假模式映射关系个数
    */
    public int              nMapInfoNum;
    /**
     * 防假等级和防假模式映射关系,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_FACE_ANTIFAKE_STRATEGY_MAP_INFO}
    */
    public NET_FACE_ANTIFAKE_STRATEGY_MAP_INFO[] stuMapInfo = new NET_FACE_ANTIFAKE_STRATEGY_MAP_INFO[4];
    /**
     * 防假等级, 0:不开启防假（对应之前关闭防假）63:防假低等级 127:防假中等级（对应之前高防假）255:最高等级防假）
    */
    public int              nLevel;

    public NET_CFG_FACE_ANTIFAKE_STRATEGY_INFO() {
        this.dwSize = this.size();
        for(int i = 0; i < stuMapInfo.length; i++){
            stuMapInfo[i] = new NET_FACE_ANTIFAKE_STRATEGY_MAP_INFO();
        }
    }
}

