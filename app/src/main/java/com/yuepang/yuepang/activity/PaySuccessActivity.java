package com.yuepang.yuepang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.common.annotation.view.BindViewByTag;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.control.UserCentreControl;
import com.yuepang.yuepang.model.PayItem;

/**
 * Created by xugh on 2019/4/3.
 */

public class PaySuccessActivity extends BaseActivity {

    @BindViewByTag
    private TextView tvTel;//手机号
    @BindViewByTag
    private TextView tvOrderId;//订单金额
    @BindViewByTag
    private TextView tvMedName;// 商家名称
    @BindViewByTag
    private TextView tvPrice;// 支付金额
    @BindViewByTag(click = true)
    private Button btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PayItem payItem = getIntent().getParcelableExtra(PayActivity.PAYITEM);
        LogUtils.e("payItem  ===="+payItem);
        tvTel.setText("充值账号："+UserCentreControl.getInstance().getInfo().getTel());
        tvMedName.setText("商家名称："+payItem.getMerchantName());
        tvOrderId.setText("订  单  号："+payItem.getOrderIdst());
        tvPrice.setText("支付金额："+payItem.getPrice() / 100 + "元");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        finish();
    }

    @Override
    public String getMyTittle() {
        return "支付成功";
    }

    @Override
    protected int getContentViewId() {
        return R.layout.pay_success_ly;
    }
}
