package com.yuepang.yuepang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;

/**
 * Created by xugh on 2019/3/19.
 */

public class PayActivity extends BaseActivity {

    public static final String DISCOUNT = "discount";


    @BindView(id = R.id.ed_price)
    private EditText edprice;

    @BindView(id = R.id.tv_discount)
    private TextView tvdiscount;

    @BindView(id = R.id.btn_buy)
    private Button btnPay;

    private float discount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() != null) {
            discount = getIntent().getFloatExtra(DISCOUNT, 1.0f);
            if (discount < 0 || discount > 1.0f) {
                discount = 1.0f;
            }
        }
    }

    @Override
    protected String getMyRTitle() {
        return null;
    }

    @Override
    protected String getMyTittle() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.pay_ly;
    }
}
