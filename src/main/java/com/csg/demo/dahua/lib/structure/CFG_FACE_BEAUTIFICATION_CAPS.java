package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 结构化支持人脸美颜具体参数的值
*/
public class CFG_FACE_BEAUTIFICATION_CAPS extends NetSDKLib.SdkStructure
{
    /**
     * 支持的模型配置类型个数
    */
    public int              nModelTypesNum;
    /**
     * 支持的模型配置类型,"Manual": 手动,"Auto": 自动
    */
    public BYTE_ARRAY_16[]  szModelTypes = new BYTE_ARRAY_16[32];
    /**
     * 支持的美颜风格配置类型个数
    */
    public int              nSadeispTypesNum;
    /**
     * 支持的美颜风格配置类型 ,"General": 普通风格  ,"Advance": 高级风格
    */
    public BYTE_ARRAY_16[]  szSadeispTypes = new BYTE_ARRAY_16[32];
    /**
     * 支持可调节的图像参数个数
    */
    public int              nImageParamsNum;
    /**
     * 支持可调节的图像参数
    */
    public BYTE_ARRAY_16[]  szImageParams = new BYTE_ARRAY_16[32];
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1020];

    public CFG_FACE_BEAUTIFICATION_CAPS() {
        for(int i = 0; i < szModelTypes.length; i++){
            szModelTypes[i] = new BYTE_ARRAY_16();
        }
        for(int i = 0; i < szSadeispTypes.length; i++){
            szSadeispTypes[i] = new BYTE_ARRAY_16();
        }
        for(int i = 0; i < szImageParams.length; i++){
            szImageParams[i] = new BYTE_ARRAY_16();
        }
    }
}

