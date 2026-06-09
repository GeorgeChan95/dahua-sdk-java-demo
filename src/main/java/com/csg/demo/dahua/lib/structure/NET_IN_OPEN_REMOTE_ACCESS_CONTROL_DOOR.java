package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * CLIENT_OpenRemoteAccessControlDoor 输入参数
*/
public class NET_IN_OPEN_REMOTE_ACCESS_CONTROL_DOOR extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 设备通道号
    */
    public int              nChannelID;
    /**
     * 远程用户ID
    */
    public byte[]           szUserID = new byte[16];
    /**
     * 开门方式,参见枚举定义 {@link com.csg.demo.dahua.lib.NetSDKLib.EM_OPEN_DOOR_TYPE}
    */
    public int              emOpenDoorType;
    /**
     * 门禁通道号
    */
    public int              nDoorIndex;
    /**
     * 兼容字段
    */
    public byte[]           szShortNumber = new byte[16];
    /**
     * 兼容字段
    */
    public byte[]           szOpenDoorType = new byte[16];
    /**
     * 远程权限验证, 0:远程验证正确, 1:远程验证错误
    */
    public int              nRemoteCheckCode;
    /**
     * 开门方向, 1：朝进门方向开门 2：朝出门方向开门
    */
    public int              nDirection;
    /**
     * 目标设备标识符。智能锁场景下可以填SN号, 透明转发目标，无此字段表示不转发
    */
    public byte[]           szTarget = new byte[32];

    public NET_IN_OPEN_REMOTE_ACCESS_CONTROL_DOOR() {
        this.dwSize = this.size();
    }
}

