package com.yuepang.yuepang.test;

import com.yuepang.yuepang.model.TopicInfo;

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

}
