package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * NET_EM_CFG_PRIVACY_SET 设备界面隐私展示设置相关配置
*/
public class NET_CFG_PRIVACY_SET_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 编号隐私设置使能，使能之后对编号进行脱敏
    */
    public int              bNumPrivacyEnable;
    /**
     * 姓名隐私设置使能，使能之后对姓名进行脱敏
    */
    public int              bNamePrivacyEnable;
    /**
     * 身份证号隐私设置使能，使能之后对身份证号进行脱敏
    */
    public int              bIDPrivacyEnable;
    /**
     * 注册人脸保存使能。使能之后人员添加人脸保存图片到设备
    */
    public int              bRegisterFaceImageEnable;
    /**
     * 报警抓图使能开关，包括全部( 门超时报警、闯入报警、反潜报警、胁迫报警、防拆报警、非法超次报警、黑名单报警、外部报警）
    */
    public int              bAlarmEventSnapEnable;

    public NET_CFG_PRIVACY_SET_INFO() {
        this.dwSize = this.size();
    }
}

