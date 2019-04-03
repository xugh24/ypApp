package com.yuepang.yuepang.test;


import com.yuepang.yuepang.model.AreaInfo;
import com.yuepang.yuepang.model.MerchantInfo;
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

    public static List<MerchantInfo> getMerinfos() {
        List<MerchantInfo> list = new ArrayList<>();
        MerchantInfo info = new MerchantInfo();
        info.setDetails("测试商家介绍1xxxxxxxxxx");
        info.setDiscount(0.7f);
        info.setLongitude(116.35424366614518);
        info.setLatitude(40.041263057322254);
        info.setName("测试商家名称");
        info.setLocation("测试商家地址");
        info.setTel("1234567");
        MerchantInfo info1 = new MerchantInfo();
        info1.setDetails("测试商家介绍2xxxxxxxxxx");
        info1.setDiscount(0.8f);
        info1.setLongitude(116.36043915298258);
        info1.setLatitude(40.034671107747194);
        info1.setName("测试商家名称2");
        info1.setLocation("测试商家地址2");
        info1.setTel("01234567");
        list.add(info);
        list.add(info1);
        return list;
    }

}
