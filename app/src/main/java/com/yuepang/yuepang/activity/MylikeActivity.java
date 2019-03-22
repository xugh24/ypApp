package com.yuepang.yuepang.activity;

import com.yuepang.yuepang.R;

/**
 * Created by xugh on 2019/3/18.
 *
 * 我的喜好页面
 */

public class MylikeActivity extends BaseActivity {
    @Override
    protected String getMyRTitle() {
        return "确定";
    }

    @Override
    protected String getMyTittle() {
        return "喜好";
    }

    @Override
    protected int getContentViewId() {
        return R.layout.mylike_ly;
    }

    @Override
    public void clikRt() {
        finish();
    }
}
