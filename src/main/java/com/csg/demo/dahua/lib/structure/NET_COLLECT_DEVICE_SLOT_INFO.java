package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 采集设备的槽位信息
*/
public class NET_COLLECT_DEVICE_SLOT_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 槽位标识,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_COLLECT_DEVICE_SLOT_ID_INFO}
    */
    public NET_COLLECT_DEVICE_SLOT_ID_INFO stuSlotID = new NET_COLLECT_DEVICE_SLOT_ID_INFO();
    /**
     * 槽位状态, 0:槽位上未接入设备, 1:槽位接入设备，槽位门未锁定, 2:槽位接入设备，槽位门已解锁, 3:槽位接入设备，未成功登陆状态, 4:槽位接入设备，登陆锁定状态, 5:槽位接入设备并成功登陆，设备未绑定执法人, 6:槽位接入设备并成功登陆，采集数据中, 7:槽位接入设备并成功登陆，数据采集异常, 8:槽位接入设备并成功登陆，升级中, 9:槽位接入设备并成功登陆，设备升级失败, 10:槽位接入设备并成功登陆，设备采集结束, 11:槽位接入设备并成功登陆，采集手持日志中, 12:槽位接入设备存储超时，进入保护状态, 13:槽位接入设备并成功登陆，设备升级成功
    */
    public int              nState;
    /**
     * 槽位是否开启数据优先采集
    */
    public int              bPriorityCollectEnable;
    /**
     * 槽位数据采集带宽,单位字节
    */
    public int              nCollectBindwith;
    /**
     * 槽位锁定状态, 0:槽位门锁定, 1:槽位门未锁定
    */
    public int              nLock;
    /**
     * 被采集设备的序列号
    */
    public byte[]           szSerial = new byte[32];
    /**
     * 被采集设备名称，对已登录的状态有效
    */
    public byte[]           szName = new byte[64];
    /**
     * 0~100:表示被采集设备当前电量百分比, -1:表示电量信息未获取到
    */
    public int              nBattery;
    /**
     * 表示连续登陆被采集设备的失败次数，nState为3和4时有效
    */
    public int              nLoginTryCount;
    /**
     * 登陆锁定剩余时间，单位是秒，nState为4时有效
    */
    public int              nLoginLockLeftSeconds;
    /**
     * 升级进度百分比，范围0-100
    */
    public int              nProcess;
    /**
     * 采集的视频文件信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_COLLOCT_FILE_INFO}
    */
    public NET_COLLOCT_FILE_INFO stuVideoFileInfo = new NET_COLLOCT_FILE_INFO();
    /**
     * 采集的音频文件信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_COLLOCT_FILE_INFO}
    */
    public NET_COLLOCT_FILE_INFO stuAudioFileInfo = new NET_COLLOCT_FILE_INFO();
    /**
     * 采集的图片文件信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_COLLOCT_FILE_INFO}
    */
    public NET_COLLOCT_FILE_INFO stuPicFileInfo = new NET_COLLOCT_FILE_INFO();
    /**
     * 采集设备的槽位扩展信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_COLLECT_DEVICE_SLOT_INFO_EX}
    */
    public Pointer          pstuInfoEx;
    /**
     * 保留字节
    */
    public byte[]           szResvered = new byte[256-NetSDKLib.POINTERSIZE];

    public NET_COLLECT_DEVICE_SLOT_INFO() {
    }
}

