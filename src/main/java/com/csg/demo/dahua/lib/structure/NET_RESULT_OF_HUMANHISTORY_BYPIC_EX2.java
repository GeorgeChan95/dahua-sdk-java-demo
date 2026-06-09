package com.csg.demo.dahua.lib.structure;
import com.csg.demo.dahua.lib.NetSDKLib;
/**
 * 人体历史库以图搜图主动上报结果信息扩展
*/
public class NET_RESULT_OF_HUMANHISTORY_BYPIC_EX2 extends NetSDKLib.SdkStructure
{
    /**
     * 候选人员信息,参见结构体定义 {@link com.csg.demo.dahua.lib.structure.NET_HUMANHISTORY_CANDIDATE_EX2}
    */
    public NET_HUMANHISTORY_CANDIDATE_EX2[] stuCandidateInfo = new NET_HUMANHISTORY_CANDIDATE_EX2[50];
    /**
     * 候选人员数量
    */
    public int              nCandidateNum;
    /**
     * 预留字节数
    */
    public byte[]           bReserved = new byte[1020];

    public NET_RESULT_OF_HUMANHISTORY_BYPIC_EX2() {
        for(int i = 0; i < stuCandidateInfo.length; i++){
            stuCandidateInfo[i] = new NET_HUMANHISTORY_CANDIDATE_EX2();
        }
    }
}

