package com.yuepang.yuepang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.common.annotation.view.BindViewByTag;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.MerchantInfo;
import com.yuepang.yuepang.model.PayItem;
import com.yuepang.yuepang.protocol.AddOrderProtocol;

import static com.yuepang.yuepang.activity.MerchantDetailActivity.MERCHANTINFO;

/**
 * 支付页面
 */

public class PayActivity extends BaseActivity implements LoadCallBack<PayItem> {

    public final static String PAYITEM = "payItem";
    private MerchantInfo info;

    @BindViewByTag
    private EditText edprice;//可以打折的金额
    @BindViewByTag
    private EditText edNodisPrice;// 无法打折的金额
    @BindViewByTag
    private LinearLayout linearLayout;

    @BindViewByTag(click = true)
    private Button btnPay; // 支付按钮

    @BindViewByTag
    private CheckBox chx;// 复选框

    @BindViewByTag
    private TextView tvDis;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        info = getIntent().getParcelableExtra(MERCHANTINFO);
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
        if (info.getDiscount() == 1) {
            tvDis.setText("商家无折扣");
        } else {
            tvDis.setText("商家折扣:" + info.getDiscount() + "折");
        }
    }

    @Override
    public void onClick(View v) {
        pay();
    }

    private void pay() {
        String price1 = edprice.getText().toString();// 获得需要打折的金额
        String price2 = edNodisPrice.getText().toString();// 获得不需要打折的金额
        int p1 = 0;
        int p2 = 0;
        int price = 0;
        if (TextUtils.isEmpty(price1) && TextUtils.isEmpty(price2)) {
            showToastSafe("请输入支付金额");
            return;
        }
        if (!TextUtils.isEmpty(price1) && price1.startsWith("0")) {
            showToastSafe("请输入正确的支付金额");
            return;
        }
        if (!TextUtils.isEmpty(price2) && price2.startsWith("0")) {
            showToastSafe("请输入正确的支付金额");
            return;
        }
        if (!TextUtils.isEmpty(price1)) {
            p1 = Integer.valueOf(price1);
        }
        if (!TextUtils.isEmpty(price2)) {
            p2 = Integer.valueOf(price2);
        }
        price = (int) (p1 * 100 * info.getDiscount()) + p2 * 100;
        new AddOrderProtocol(this, this, 1, price).request();
    }

    @Override
    public String getMyRTitle() {
        return null;
    }

    @Override
    public String getMyTittle() {
        return "商家名称";
    }

    @Override
    protected int getContentViewId() {
        return R.layout.pay_ly;
    }



    @Override
    public void loadCallBack(CallType callType, int CODE, String msg, PayItem payItem) {
        if (callType.equals(CallType.SUCCESS)) {
            Intent intent = new Intent();
            intent.putExtra(PAYITEM, payItem);
            intent.setClass(this, PaySuccessActivity.class);
            finish();
            startActivity(intent);
        }
    }

    /**
     * 进入支付页面方法
     */
    public static void toPay(Context context, MerchantInfo merchantInfo) {
        Intent intent = new Intent();
        intent.putExtra(MERCHANTINFO, merchantInfo);
        intent.setClass(context, PayActivity.class);
        context.startActivity(intent);
    }
}
