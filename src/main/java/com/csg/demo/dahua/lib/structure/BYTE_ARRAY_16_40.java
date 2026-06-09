package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;

public class BYTE_ARRAY_16_40 extends NetSDKLib.SdkStructure
{
    public BYTE_ARRAY_40[]  obj_16 = new BYTE_ARRAY_40[16];       
    public BYTE_ARRAY_16_40() {
        for (int i = 0; i < obj_16.length; i++) {
            obj_16[i] = new BYTE_ARRAY_40();
        }
    }
}

