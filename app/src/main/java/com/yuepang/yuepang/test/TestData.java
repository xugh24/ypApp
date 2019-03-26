package com.yuepang.yuepang.test;


import com.yuepang.yuepang.model.AreaInfo;
import com.yuepang.yuepang.model.TopicInfo;
import com.yuepang.yuepang.model.TopicItemInfo;

import java.util.ArrayList;
import java.util.List;

/**
 */

public class TestData {


    public static List<AreaInfo> getinfos() {
        List<AreaInfo> list = new ArrayList<>();
        AreaInfo info1 = new AreaInfo();
        info1.setId(1);
        info1.setName("商圈1");
        AreaInfo info2 = new AreaInfo();
        info2.setId(2);
        info2.setName("商圈1");
        AreaInfo info3 = new AreaInfo();
        info3.setId(3);
        info3.setName("商圈1");
        AreaInfo info4 = new AreaInfo();
        info4.setId(4);
        info4.setName("商圈1");
        list.add(info1);
        list.add(info2);
        list.add(info3);
        list.add(info4);
        return list;
    }

}
