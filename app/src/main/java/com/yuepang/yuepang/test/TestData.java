package com.yuepang.yuepang.test;


import com.yuepang.yuepang.model.AreaInfo;
import com.yuepang.yuepang.model.RecordInfo;
import com.yuepang.yuepang.model.TopicInfo;
import com.yuepang.yuepang.model.TopicItemInfo;

import java.util.ArrayList;
import java.util.List;

/**
 */

public class TestData {


    public static List<RecordInfo> getRecInfos() {
        List<RecordInfo> list = new ArrayList<>();
        RecordInfo info1 = new RecordInfo();
        info1.setOrderId("2019040201");
        info1.setMerchantName("商家1");
        info1.setPrice(200);
        info1.setTime(System.currentTimeMillis());
        RecordInfo info2 = new RecordInfo();
        info2.setOrderId("2019040201");
        info2.setMerchantName("商家2");
        info2.setPrice(300);
        info2.setTime(System.currentTimeMillis());
        RecordInfo info3 = new RecordInfo();
        info3.setOrderId("2019040201");
        info3.setMerchantName("商家3");
        info3.setPrice(400);
        info3.setTime(System.currentTimeMillis());
        RecordInfo info4 = new RecordInfo();
        info4.setOrderId("2019040201");
        info4.setMerchantName("商家4");
        info4.setPrice(400);
        info4.setTime(System.currentTimeMillis());
        list.add(info1);
        list.add(info2);
        list.add(info3);
        list.add(info4);
        return list;
    }


    public static List<AreaInfo> getinfos() {
        List<AreaInfo> list = new ArrayList<>();
        AreaInfo info1 = new AreaInfo();
        info1.setId(1);
        info1.setName("商圈1");
        AreaInfo info2 = new AreaInfo();
        info2.setId(2);
        info2.setName("商圈2");
        AreaInfo info3 = new AreaInfo();
        info3.setId(3);
        info3.setName("商圈3");
        AreaInfo info4 = new AreaInfo();
        info4.setId(4);
        info4.setName("商圈4");
        list.add(info1);
        list.add(info2);
        list.add(info3);
        list.add(info4);
        return list;
    }

}
