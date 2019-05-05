package com.yuepang.yuepang.test;


import com.yuepang.yuepang.model.AreaInfo;
import com.yuepang.yuepang.model.GoodInfo;
import com.yuepang.yuepang.model.MerchantInfo;
import com.yuepang.yuepang.model.RecordInfo;
import com.yuepang.yuepang.model.TopicInfo;
import com.yuepang.yuepang.model.TopicItemInfo;

import java.util.ArrayList;
import java.util.List;

/**
 */

public class TestData {


    /**
     * 订单测试数据
     */
    public static List<RecordInfo> getRecInfos() {
        List<RecordInfo> list = new ArrayList<>();
        RecordInfo info1 = new RecordInfo();
        info1.setMerchantName("商家1");
        info1.setPrice(200);
        info1.setTime(System.currentTimeMillis());
        RecordInfo info2 = new RecordInfo();
        info2.setMerchantName("商家2");
        info2.setPrice(300);
        info2.setTime(System.currentTimeMillis());
        RecordInfo info3 = new RecordInfo();
        info3.setMerchantName("商家3");
        info3.setPrice(400);
        info3.setTime(System.currentTimeMillis());
        RecordInfo info4 = new RecordInfo();
        info4.setMerchantName("商家4");
        info4.setPrice(400);
        info4.setTime(System.currentTimeMillis());
        list.add(info1);
        list.add(info2);
        list.add(info3);
        list.add(info4);
        return list;
    }


    /**
     * 商圈测试数据
     */
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
        MerchantInfo info2 = new MerchantInfo();
        info2.setDetails("测试商家介绍2xxxxxxxxxx");
        info2.setDiscount(0.9f);
        info2.setLongitude(116.36043915298258);
        info2.setLatitude(40.034671107747194);
        info2.setName("测试商家名称2");
        info2.setLocation("测试商家地址2");
        info2.setTel("01234567");
        MerchantInfo info3 = new MerchantInfo();
        info3.setDetails("测试商家介绍2xxxxxxxxxx");
        info3.setDiscount(1.0f);
        info3.setLongitude(116.36043915298258);
        info3.setLatitude(40.034671107747194);
        info3.setName("测试商家名称3");
        info3.setLocation("测试商家地址3");
        info3.setTel("54301234567");
        MerchantInfo info4 = new MerchantInfo();
        info4.setDetails("测试商家介绍2xxxxxxxxxx");
        info4.setDiscount(0.8f);
        info4.setLongitude(116.36043915298258);
        info4.setLatitude(40.034671107747194);
        info4.setName("测试商家名称4");
        info4.setLocation("测试商家地址4");
        info4.setTel("2301234567");
        list.add(info);
        list.add(info1);
        list.add(info2);
        list.add(info3);
        list.add(info4);
        return list;
    }

    public static List<GoodInfo> getGoods() {
        List<GoodInfo> list = new ArrayList<>();
        GoodInfo info1 = new GoodInfo();
        info1.setTitle("商品名称1xxxxxx");
        info1.setMsg("测试商品的介绍1xxxxx");
        info1.setInfo(getMerinfos().get(0));
        GoodInfo info2 = new GoodInfo();
        info2.setTitle("商品名称2xxxxxx");
        info2.setMsg("测试商品的介绍2xxxxx");
        info1.setInfo(getMerinfos().get(0));
        GoodInfo info3 = new GoodInfo();
        info3.setTitle("商品名称3xxxxxx");
        info3.setMsg("测试商品的介绍3xxxxx");
        info1.setInfo(getMerinfos().get(1));
        GoodInfo info4 = new GoodInfo();
        info4.setTitle("商品名称4xxxxxx");
        info4.setMsg("测试商品的介绍4xxxxx");
        info1.setInfo(getMerinfos().get(1));
        list.add(info1);
        list.add(info2);
        list.add(info3);
        list.add(info4);
        return list;

    }

}
