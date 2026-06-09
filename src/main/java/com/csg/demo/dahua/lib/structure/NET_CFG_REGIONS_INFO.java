package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 检测区域配置信息
*/
public class NET_CFG_REGIONS_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 检测区顶点数
    */
    public int              nDetectRegionNum;
    /**
     * 区域类型, 0:未知, 1: 上方区域, 2: 下方区域, 3: 左方区域, 4: 右方区域, 5: 前方区域, 6: 后方区域
    */
    public int              nRegionType;
    /**
     * 检测区,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.POINTCOORDINATE}
    */
    public NetSDKLib.POINTCOORDINATE[] stuDetectRegion = new NetSDKLib.POINTCOORDINATE[20];
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_CFG_REGIONS_INFO() {
        for(int i = 0; i < stuDetectRegion.length; i++){
            stuDetectRegion[i] = new NetSDKLib.POINTCOORDINATE();
        }
    }
}

