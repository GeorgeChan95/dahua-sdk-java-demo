package com.csg.demo.dahua.lib.structure;


import com.csg.demo.dahua.lib.NetSDKLib;

/** 
* @author 291189
* @description  门禁控制-呼梯(对应CLIENT_ControlDevice接口的 DH_CTRL_ACCESS_CALL_LIFT 命令) 
* @date 2022/10/19 14:01:08
*/
public class NET_CTRL_ACCESS_CALL_LIFT extends NetSDKLib.SdkStructure {
    public			int            dwSize;
/** 
通道号
*/
    public			int            nChannelID;
/** 
起始地址(楼层+房间号)
*/
    public			byte[]         szSrcFloor = new byte[16];
/** 
目标地址(楼层+房间号)
*/
    public			byte[]         szDestFloor = new byte[16];
/** 
呼叫电梯命令 {@link com.csg.demo.dahua.lib.enumeration.EM_CALLLIFT_CMD}
*/
    public			int            emCallLiftCmd;
/** 
呼叫电梯的方式 {@link com.csg.demo.dahua.lib.enumeration.EM_CALLLIFT_ACTION}
*/
    public			int            emCallLiftAction;
/** 
用户ID
*/
    public			byte[]         szUserID = new byte[16];
/** 
呼叫电梯个数
*/
    public			int            nCallLiftCount;
/** 
呼叫电梯号
*/
    public			int[]          nCallLiftNo = new int[16];
/** 
呼梯类型 {@link com.csg.demo.dahua.lib.enumeration.EM_LIFT_CALLER_TYPE}
*/
    public			int            emLiftCaller;
    /**
     * 多楼层信息数量
    */
    public int              nMultiFloorNum;
    /**
     * 多楼层信息,当emCallLiftAction取值EM_CALLLIFT_ACTION_MULTI_FLOOR_ACCESS此字段生效,定制使用,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_MULTI_FLOOR}
    */
    public NET_MULTI_FLOOR[] stuMultiFloor = new NET_MULTI_FLOOR[128];

public NET_CTRL_ACCESS_CALL_LIFT(){
		this.dwSize=this.size();
}
}

