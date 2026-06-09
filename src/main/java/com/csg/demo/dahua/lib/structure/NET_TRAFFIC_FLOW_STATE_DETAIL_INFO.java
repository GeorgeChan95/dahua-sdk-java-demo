package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 交通流量详细信息
*/
public class NET_TRAFFIC_FLOW_STATE_DETAIL_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 车道类型,参见枚举定义 {@link com.csg.demo.dahua.lib.NetSDKLib.EM_TRAFFICCAR_LANE_TYPE}
    */
    public int              emType;
    /**
     * 机动和非机动车总数获取方式，0：Vehicles字段有效，1：VehiclesMulti字段有效
    */
    public int              nVehiclesMultiFlag;
    /**
     * 地点代码
    */
    public byte[]           szMachineAddress = new byte[256];
    /**
     * 设备编号
    */
    public byte[]           szMachineName = new byte[256];
    /**
     * 多线圈场景下机动和非机动车总数
    */
    public byte[]           szVehiclesMulti = new byte[64];
    /**
     * 此条记录中是否包含车流量统计数据，-1：未知 0：不包含 1：包含
    */
    public int              nVehicleFlag;
    /**
     * 此条记录中是否包含行人流量统计数据，-1：未知 0：不包含 1：包含
    */
    public int              nHumanFlag;
    /**
     * 空间占有率统计信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_SPACE_OCCUPY_RATIO_INFO}
    */
    public NET_SPACE_OCCUPY_RATIO_INFO[] stuSpaceOccupyRatioMulti = new NET_SPACE_OCCUPY_RATIO_INFO[32];
    /**
     * 空间占有率统计信息有效个数
    */
    public int              nSpaceOccupyRatioMultiNum;
    /**
     * 使能的统计类型，bit0-机动车 bit1-非机动车 bit2-人
    */
    public int              nStatTypeEx;
    /**
     * 左转非机动车车辆总数(辆/单位时间)
    */
    public int              nNonMotorLeftVehicles;
    /**
     * 右转非机动车车辆总数(辆/单位时间)
    */
    public int              nNonMotorRightVehicles;
    /**
     * 直行非机动车车辆总数(辆/单位时间)
    */
    public int              nNonMotorStraightVehicles;
    /**
     * 多截面流量统计信息有效个数
    */
    public int              nMultiSectionsFlowInfoNum;
    /**
     * 多截面流量统计信息，部分子字段已于外部实现，后续子字段新增至此,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_MULTI_SECTIONS_FLOW_INFO}
    */
    public NET_MULTI_SECTIONS_FLOW_INFO[] stuMultiSectionsFlowInfo = new NET_MULTI_SECTIONS_FLOW_INFO[32];
    /**
     * 最后一辆车到停车线的距离，单位：米
    */
    public double           dbFinalVehicleDist;
    /**
     * 位速度百分比
    */
    public int[]            nDisplaceSpeed = new int[8];
    /**
     * 位速度百分比有效个数
    */
    public int              nDisplaceSpeedNum;
    /**
     * 位速度数值有效个数
    */
    public int              nDisplaceSpeedValueNum;
    /**
     * 位速度数值
    */
    public float[]          fDisplaceSpeedValue = new float[8];
    /**
     * 大车平均速度（km/h）车长<12米 -1表示速度无效
    */
    public double           dbLargeVehicleAvgSpeed;
    /**
     * 中型车平均速度（km/h）车长<9米
    */
    public double           dbMediumVehicleAvgSpeed;
    /**
     * 小车平均速度（km/h）车长<6米
    */
    public double           dbSmallVehicleAvgSpeed;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1000];

    public NET_TRAFFIC_FLOW_STATE_DETAIL_INFO() {
        for(int i = 0; i < stuSpaceOccupyRatioMulti.length; i++){
            stuSpaceOccupyRatioMulti[i] = new NET_SPACE_OCCUPY_RATIO_INFO();
        }
        for(int i = 0; i < stuMultiSectionsFlowInfo.length; i++){
            stuMultiSectionsFlowInfo[i] = new NET_MULTI_SECTIONS_FLOW_INFO();
        }
    }
}

