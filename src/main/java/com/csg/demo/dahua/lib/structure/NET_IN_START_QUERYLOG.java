package com.csg.demo.dahua.lib.structure;

import com.csg.demo.dahua.lib.NetSDKLib;

/**
 * @author 251823
 * @description CLIENT_StartQueryLog 输入参数
 * @date 2023/06/12 09:18:50
 */
public class NET_IN_START_QUERYLOG extends NetSDKLib.SdkStructure {
    public int              dwSize;
	/**
	 * 查询日志类型 {@link com.csg.demo.dahua.lib.enumeration.DH_LOG_QUERY_TYPE}
	 */
    public int              emLogType;
	/**
	 * 查询日志的开始时间
	 */
    public NET_TIME         stuStartTime = new NET_TIME();
	/**
	 * 查询日志的结束时间
	 */
    public NET_TIME         stuEndTime = new NET_TIME();
	/**
	 * 对emLogType进行扩展分类查询方式;TRUE 表明使用szLogTypeEx参数,FALSE使用emLogType
	 */
    public int              bLogTypeExFlag;
	/**
	 * szLogTypeEx数组真实长度，最大64个
	 */
    public int              nLogTypeExNum;
	/**
	 * 报警主机日志类型扩展透传数组,如果需要查询的类型超过64个,则分组多次查询
	 */
    public byte[]           szLogTypeEx = new byte[64 * 128];
	/**
	 * 查询结果排序方式 {@link EM_RESULT_ORDER_TYPE}
	 */
    public int              emResultOrder;
	/**
	 * 是否使用UTC时间
	 */
    public int              bSendByUTCTime;
	/**
	 * UTC查询开始时间
	 */
    public NET_TIME         stuStartTimeRealUTC = new NET_TIME();
	/**
	 * UTC查询结束时间
	 */
    public NET_TIME         stuEndTimeRealUTC = new NET_TIME();
    /**
     * 是否使用 DetailKey 字段
    */
    public int              bUseDetailKey;
    /**
     * 是否使用 DetailValue 字段
    */
    public int              bUseDetailValue;
    /**
     * 用来查询日志详细信息中的Key值,可以是详细信息的顶级成员,也可以是其次级或多级成员,"*"表示对Detail的所有Key都查询
    */
    public byte[]           szDetailKey = new byte[64];
    /**
     * 用来查询日志详细信息中的Value值,字符串和整形值匹配,字符串前后都可以加"*"用于模糊查询,前后都加*表示有指定字符串即可,比如"Alarm*"表示查询以Alarm开头的内容
    */
    public byte[]           szDetailValue = new byte[64];

	public NET_IN_START_QUERYLOG() {
		this.dwSize = this.size();
	}
}

