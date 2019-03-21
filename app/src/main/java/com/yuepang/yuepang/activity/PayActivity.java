package com.yuepang.yuepang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;

/**
 * Created by xugh on 2019/3/19.
 */

public class PayActivity extends BaseActivity {

    public static final String DISCOUNT = "discount";

    @BindView(id = R.id.ed_price)
    private EditText edprice;

    @BindView(id = R.id.ed_nodisprice)
    private EditText edNodisPrice;

    @BindView(id = R.id.ll_nodis)
    private LinearLayout linearLayout;

    @BindView(id = R.id.btn_pay)
    private Button btnPay;

    @BindView(id = R.id.radio)
    private CheckBox chx;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    linearLayout.setVisibility(View.VISIBLE);
                } else {
                    linearLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected String getMyRTitle() {
        return null;
    }

    @Override
    protected String getMyTittle() {
        return "商家名称";
    }

    @Override
    protected int getContentViewId() {
        return R.layout.pay_ly;
    }
}
