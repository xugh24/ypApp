package com.yuepang.yuepang.activity;

import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;

/**
 * Created by xugh on 2019/3/20.
 */

public class MerchantManageActivity extends BaseActivity {

    @BindView(id = R.id.tv_attestation)
    private TextView tvAttestation;

    @BindView(id = R.id.tv_attestation)
    private TextView tvMerchant;

    @Override
    protected String getMyRTitle() {
        return null;
    }

    @Override
    protected String getMyTittle() {
        return "商家认证";
    }

    @Override
    protected int getContentViewId() {
        return R.layout.merchant_manage_ly;
    }
}