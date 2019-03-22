package com.yuepang.yuepang.activity;

import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;

/**
 * Created by xugh on 2019/3/20.
 */

public class MerchantManageActivity extends BaseActivity {


    @Override
    protected String getMyRTitle() {
        return null;
    }

    @Override
    protected String getMyTittle() {
        return "设置";
    }

    @Override
    protected int getContentViewId() {
        return R.layout.merchant_manage_ly;
    }
}
