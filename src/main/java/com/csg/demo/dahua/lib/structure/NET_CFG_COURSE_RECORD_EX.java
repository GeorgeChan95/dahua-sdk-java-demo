package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * IVSS产品首次提交，录像计划扩展配置，跟StorageScheduleGlobal配置关联。(NET_EM_CFG_COURSE_RECORD_EX)
*/
public class NET_CFG_COURSE_RECORD_EX extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 是否继承全局的存储计划配置(StorageScheduleGlobal)，true表示继承，false表示不继承
    */
    public int              bIsGlobal;
    /**
     * 预录时间，为零时表示关闭,最大预录时间从RocordEX中获取MaxPreRecordTime,单位秒
    */
    public int              nPreRecord;
    /**
     * 录像断网续传功能使能,是否支持从Record能力集中取得
    */
    public int              bEnable;
    /**
     * 最长的上传录像时间，如果断网时间小于这个值 就传断网时间内的录像，如果大于这个值就传这么长时间内的录像,单位：秒
    */
    public int              nMaxRecordTime;
    /**
     * 事件发生后的录像延时时间范围[1,300]
    */
    public int              nRecordLatch;
    /**
     * 录像类型,参见枚举定义 {@link com.csg.demo.dahua.lib.enumeration.EM_SUB_MODE}
    */
    public int              emSubMode;
    /**
     * 事件类型对应的日程ID
    */
    public int              nTimeSectionID;
    /**
     * 手动录像打包时长,单位分钟范围[1,30]
    */
    public int              nManulRecordLength;
    /**
     * 设置文件的保留天数，超过时间的将被删除。0~365，天为单位，0表示永不过期。
    */
    public int              nFileHoldTime;

    public NET_CFG_COURSE_RECORD_EX() {
        this.dwSize = this.size();
    }
}

