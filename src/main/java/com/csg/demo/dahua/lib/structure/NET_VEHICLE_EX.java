package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 交通拓展信息
*/
public class NET_VEHICLE_EX extends NetSDKLib.SdkStructure
{
    /**
     * 车辆年款（给视图库用）
    */
    public byte[]           szBrandYearText = new byte[64];
    /**
     * 车型置信度，[0-100]取值越到越可靠
    */
    public byte             byCategoryConfidence;
    /**
     * 车长表述方式,0:无车长,1:小于carLength,2:大于carlength,3:显示carlength,4:显示大/中/小车
    */
    public byte             byCarLenMode;
    /**
     * 车长,单位(分米);当carLenMode为4时,0代表小车,1代表中型车,2代表大车
    */
    public byte             byCarLength;
    /**
     * 车标置信度，[0-100]取值越到越可靠
    */
    public byte             byVehicleSignConfidence;
    /**
     * 属主id，比如多个*人*脸*物体从属于一个非机动车物体，则多个*人*脸*和非机动车填写相同属主id
    */
    public int              nBelongID;
    /**
     * 车辆姿态，平台用与判定抓拍车辆是否去做二次分析，如是车头则去二次分析挂件等信息.0: 未知，1: 车头，2: 车侧，3: 车尾
    */
    public int              nVehiclePosture;
    /**
     * 车辆物件个数
    */
    public int              nAttachmentNum;
    /**
     * 车辆物件信息,参见结构体定义 {@link com.csg.demo.dahua.lib.NetSDKLib.EVENT_COMM_ATTACHMENT}
    */
    public NetSDKLib.EVENT_COMM_ATTACHMENT[] stuAttachment = new NetSDKLib.EVENT_COMM_ATTACHMENT[8];
    /**
     * 车窗包围盒，0~8191,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_RECT_EX}
    */
    public NET_RECT_EX      stuCarWindowBoundingBox = new NET_RECT_EX();
    /**
     * 预留字节
    */
    public byte[]           bReserved = new byte[1024];

    public NET_VEHICLE_EX() {
        for(int i = 0; i < stuAttachment.length; i++){
            stuAttachment[i] = new NetSDKLib.EVENT_COMM_ATTACHMENT();
        }
    }
}

