package com.yuepang.yuepang.activity;

import android.widget.TextView;

import com.yuepang.yuepang.R;

/**
 * Created by xugh on 2019/3/18.
 */

public class MerchantActivity extends BaseActivity {

    private TextView tvAdd;

    private TextView tvCreate;

    @Override
    protected String getMyRTitle() {
        return null;
    }

    @Override
    protected String getMyTittle() {
        return "商圈信息";
    }

    @Override
    protected int getContentViewId() {
        return R.layout.merchant_ly;
    }
}
