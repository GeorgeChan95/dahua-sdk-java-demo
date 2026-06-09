package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 车辆类型流量
*/
public class NET_VEHICLE_TYPE_FLOW extends NetSDKLib.SdkStructure
{
    /**
     * 铲车交通量(辆/单位时间)
    */
    public int              nShovelLoaderVehicles;
    /**
     * 禽畜车交通量(辆/单位时间)
    */
    public int              nPoultryVehicles;
    /**
     * 押运车交通量(辆/单位时间)
    */
    public int              nEscortVehicles;
    /**
     * 泵车交通量(辆/单位时间)
    */
    public int              nPumpTruckVehicles;
    /**
     * 吊车交通量(辆/单位时间)
    */
    public int              nCraneVehicles;
    /**
     * 推土车交通量(辆/单位时间)
    */
    public int              nBulldozerVehicles;
    /**
     * 挖掘车交通量(辆/单位时间)
    */
    public int              nExcavatorVehicles;
    /**
     * 不载人敞篷三轮车交通量(辆/单位时间)
    */
    public int              nNoMannedConvertibleTricycleVehicles;
    /**
     * 载人敞篷三轮车交通量(辆/单位时间)
    */
    public int              nMannedConvertibleTricycleVehicles;
    /**
     * 厢式三轮车交通量(辆/单位时间)
    */
    public int              nVanTricycleVehicles;
    /**
     * 自行车交通量(辆/单位时间)
    */
    public int              nBicycleVehicles;
    /**
     * 校车交通量(辆/单位时间)
    */
    public int              nSchoolBusVehicles;
    /**
     * 二轮车交通量(辆/单位时间)
    */
    public int              nTwocycleVehicles;
    /**
     * 普通罐车交通量(辆/单位时间)
    */
    public int              nNormalTankTrunkVehicles;
    /**
     * 吸污车交通量(辆/单位时间)
    */
    public int              nSuctionSewageTruckVehicles;
    /**
     * 粉粒物料车交通量(辆/单位时间)
    */
    public int              nPowerLotVehicleVehicles;
    /**
     * 工程车交通量(辆/单位时间)
    */
    public int              nMachineshopTruckVehicles;
    /**
     * 拖拉机交通量(辆/单位时间)
    */
    public int              nTractorVehicles;
    /**
     * 消防车交通量(辆/单位时间)
    */
    public int              nFireEngineVehicles;
    /**
     * 环卫车交通量(辆/单位时间)（包括洒水车、垃圾车、清扫车等）
    */
    public int              nWateringCarVehicles;
    /**
     * 救护车交通量(辆/单位时间)
    */
    public int              nAmbulanceVehicles;
    /**
     * 警车交通量((辆/单位时间)
    */
    public int              nPoliceVehicles;
    /**
     * 出租车交通量(辆/单位时间)
    */
    public int              nTaxiVehicles;
    /**
     * 槽罐车车交通量(辆/单位时间)
    */
    public int              nSlotTankCarVehicles;
    /**
     * 渣土车交通量(辆/单位时间)
    */
    public int              nDregsCarVehicles;
    /**
     * 混凝土搅拌车交通量(辆/单位时间)
    */
    public int              nConcreteMixerTruckVehicles;
    /**
     * 挂车交通量(辆/单位时间)
    */
    public int              nTrailerVehicles;
    /**
     * 二轮电瓶车交通量(辆/单位时间)
    */
    public int              nElectricbikeVehicles;
    /**
     * 客车交通量(辆/单位时间)
    */
    public int              nPassengerCarVehicles;
    /**
     * 大货车交通量(辆/单位时间)
    */
    public int              nLargeTruckVehicles;
    /**
     * 中货车交通量(辆/单位时间)
    */
    public int              nMidTruckVehicles;
    /**
     * 轿车交通量(辆/单位时间)
    */
    public int              nSaloonCarVehicles;
    /**
     * 面包车交通量(辆/单位时间)
    */
    public int              nMicrobusVehicles;
    /**
     * 小货车交通量(辆/单位时间)
    */
    public int              nMicroTruckVehicles;
    /**
     * 三轮车交通量(辆/单位时间)
    */
    public int              nTricycleVehicles;
    /**
     * 摩托车交通量(辆/单位时间)
    */
    public int              nMotorcycleVehicles;
    /**
     * 行人交通量(辆/单位时间)
    */
    public int              nPasserbyVehicles;
    /**
     * 公交车交通量(辆/单位时间)
    */
    public int              nBusVehicles;
    /**
     * MPV交通量(辆/单位时间)
    */
    public int              nMPVVehicles;
    /**
     * 中客车交通量(辆/单位时间)
    */
    public int              nMidPassengerCarVehicles;
    /**
     * 微型轿车交通量(辆/单位时间)
    */
    public int              nMiniCarriageVehicles;
    /**
     * 油罐车交通量(辆/单位时间)
    */
    public int              nOilTankTruckVehicles;
    /**
     * 皮卡车交通量(辆/单位时间)
    */
    public int              nPickupVehicles;
    /**
     * SUV交通量(辆/单位时间)
    */
    public int              nSUVVehicles;
    /**
     * SUV或者MPV交通量(辆/单位时间)
    */
    public int              nSuvMpvVehicles;
    /**
     * 危化品车交通量(辆/单位时间)
    */
    public int              nTankCarVehicles;
    /**
     * 未知车辆交通量(辆/单位时间)
    */
    public int              nUnknownVehicles;
    /**
     * 保留字节
    */
    public byte[]           szReserverd = new byte[176];

    public NET_VEHICLE_TYPE_FLOW() {
    }
}

