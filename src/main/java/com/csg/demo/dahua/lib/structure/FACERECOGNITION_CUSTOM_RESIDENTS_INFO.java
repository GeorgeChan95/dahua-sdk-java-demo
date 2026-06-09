package com.csg.demo.dahua.lib.structure;
import com.sun.jna.Pointer;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 居民信息
*/
public class FACERECOGNITION_CUSTOM_RESIDENTS_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 绑定房屋信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.FACERECOGNITION_CUSTOM_HOUSE_INFO}
    */
    public FACERECOGNITION_CUSTOM_HOUSE_INFO[] stuHouse = new FACERECOGNITION_CUSTOM_HOUSE_INFO[8];
    /**
     * 绑定房屋信息个数
    */
    public int              nHouse;
    /**
     * 绑定房屋信息最大个数
    */
    public int              nMaxHouseNewNum;
    /**
     * 绑定房屋信息, 内存由用户申请释放，大小为sizeof(FACERECOGNITION_CUSTOM_HOUSE_INFO)*nHouseNewNum,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.FACERECOGNITION_CUSTOM_HOUSE_INFO}
    */
    public Pointer          pstuHouseNew;
    /**
     * 绑定房屋信息实际返回个数(下发时以此为准)
    */
    public int              nRetHouseNewNum;
    /**
     * 保留字段
    */
    public byte[]           szReserved = new byte[2036-NetSDKLib.POINTERSIZE];

    public FACERECOGNITION_CUSTOM_RESIDENTS_INFO() {
        for(int i = 0; i < stuHouse.length; i++){
            stuHouse[i] = new FACERECOGNITION_CUSTOM_HOUSE_INFO();
        }
    }
}

