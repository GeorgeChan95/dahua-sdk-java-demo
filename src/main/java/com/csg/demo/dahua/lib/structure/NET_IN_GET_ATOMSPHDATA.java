package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_GetAtomsphData接口入参
*/
public class NET_IN_GET_ATOMSPHDATA extends NetSDKLib.SdkStructure
{
    public int              dwSize;

    public NET_IN_GET_ATOMSPHDATA() {
        this.dwSize = this.size();
    }
}

