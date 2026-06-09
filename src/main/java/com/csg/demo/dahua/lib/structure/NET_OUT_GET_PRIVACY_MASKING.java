package com.csg.demo.dahua.lib.structure;

import com.csg.demo.dahua.lib.NetSDKLib;

/**
 * @author 251823
 * @description CLIENT_GetPrivacyMasking 输出参数
 * @date 2022/07/21 16:59:51
 */
public class NET_OUT_GET_PRIVACY_MASKING extends NetSDKLib.SdkStructure {
	/**
	 * 结构体大小
	 */
    public int              dwSize;
	/**
	 * 总共有多少个遮档块
	 */
    public int              nTotal;
	/**
	 * 隐私遮挡块信息
	 */
    public NET_PRIVACY_MASKING_INFO[] stuPrivacyMaskingInfo = new NET_PRIVACY_MASKING_INFO[24];
	/**
	 * 返回的隐私遮挡数组数量
	 */
    public int              nPrivacyMasking;
    /**
     * 返回的隐私遮挡数组数量扩展
    */
    public int              nPrivacyMaskingEx;
    /**
     * 隐私遮挡块信息扩展,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_PRIVACY_MASKING_INFO}
    */
    public NET_PRIVACY_MASKING_INFO[] stuPrivacyMaskingInfoEx = new NET_PRIVACY_MASKING_INFO[32];

	public NET_OUT_GET_PRIVACY_MASKING() {
		for (int i = 0; i < stuPrivacyMaskingInfo.length; i++) {
			stuPrivacyMaskingInfo[i] = new NET_PRIVACY_MASKING_INFO();
		}
		for (int i = 0; i < stuPrivacyMaskingInfoEx.length; i++) {
			stuPrivacyMaskingInfoEx[i] = new NET_PRIVACY_MASKING_INFO();
		}
		this.dwSize = this.size();
	}
}

