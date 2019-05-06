package com.yuepang.yuepang.interFace;

import com.yuepang.yuepang.model.AreaInfo;
import com.yuepang.yuepang.model.GoodInfo;
import com.yuepang.yuepang.model.MerchantInfo;

import java.util.List;

/**
 * Created by xugh on 2019/4/3.
 * 商圈信息的数据没有独立的请求页面，需要通过接口来传递
 */

public interface AreaInterFace {
    /**
     * 获得当前商圈信息的通知接口
     * @param infos
     */
    public void callAreaInfo( List<GoodInfo> infos);
}
