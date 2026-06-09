package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_RadiometryAttachTemper 出参
*/
public class NET_OUT_RADIOMETRY_ATTACH_TEMPER extends NetSDKLib.SdkStructure
{
    public int              dwSize;

    public NET_OUT_RADIOMETRY_ATTACH_TEMPER() {
        this.dwSize = this.size();
    }
}

