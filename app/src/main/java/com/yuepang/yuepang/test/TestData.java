package com.yuepang.yuepang.test;

import com.yuepang.yuepang.model.TopicInfo;
import com.yuepang.yuepang.model.TopicItemInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xugh on 2019/3/18.
 */

public class TestData {


    public static List<TopicInfo> gettops() {
        List<TopicInfo> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TopicInfo info = new TopicInfo();
            info.setId(i);
            info.setHeader("测");
            info.setTitle("测试数据第" + i + "条");
            info.setContentSt("Msg部分测试数据第" + i + "条");
            info.setTime(i + "小时");
            list.add(info);
        }
        return list;
    }


    public static List<TopicItemInfo> gettopicItem(int id) {
        List<TopicItemInfo> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TopicItemInfo info = new TopicItemInfo();
            info.setId(id);
            info.setName("海伦");
            info.setName("你好我叫海伦");
            list.add(info);
        }
        return list;
    }

}
