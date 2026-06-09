package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 多截面流量统计信息
*/
public class NET_MULTI_SECTIONS_FLOW_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 截面ID
    */
    public int              nSectionID;
    /**
     * 断面ID
    */
    public int              nFractureSurfaceID;
    /**
     * 截面平均车速，单位km/h
    */
    public double           dbMeanSpeed;
    /**
     * 机动车流量
    */
    public int              nMotorVehicles;
    /**
     * 小车流量(辆/单位时间)
    */
    public int              nSmallVehicles;
    /**
     * 通过截面ID的按车辆类型统计交通量,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_VEHICLE_TYPE_FLOW}
    */
    public NET_VEHICLE_TYPE_FLOW stuVehicleTypeFlow = new NET_VEHICLE_TYPE_FLOW();
    /**
     * 时间占有率，即单位时间内通过断面的车辆所用时间的总和占单位时间的比例
    */
    public double           dbTimeOccupyRatio;
    /**
     * 车头时距，单位秒/辆
    */
    public double           dbTimeHeadway;
    /**
     * 车头间距，相邻车辆之间的距离，单位米/辆
    */
    public double           dbSpaceHeadway;
    /**
     * 非机动车流量(辆/单位时间)
    */
    public int              nMotoVehicles;
    /**
     * 中型车流量(辆/单位时间)
    */
    public int              nMediumVehicles;
    /**
     * 大车流量(辆/单位时间)
    */
    public int              nLargeVehicles;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[636];

    public NET_MULTI_SECTIONS_FLOW_INFO() {
    }
}

