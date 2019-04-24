package com.yuepang.yuepang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.Util.SysUtils;
import com.yuepang.yuepang.dialog.CallDialog;
import com.yuepang.yuepang.dialog.MapDialog;
import com.yuepang.yuepang.location.LatLng;
import com.yuepang.yuepang.model.MerchantInfo;

/**
 * 商家详情页
 */

public class MerchantDetailActivity extends BaseActivity {

    public final static String MERCHANTINFO = "MerchantInfo";

    @BindView(id = R.id.mer_pic)
    private ImageView ivMer;

    @BindView(id = R.id.mer_title)
    private TextView tvName;

    @BindView(id = R.id.btn_buy, click = true)
    private Button btnBuy;

    @BindView(id = R.id.mer_msg)
    private TextView tvMsg;

    @BindView(id = R.id.mer_loction, click = true)
    private TextView tvloction;

    @BindView(id = R.id.mer_tel, click = true)
    private TextView tvTel;

    private MerchantInfo merchantInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        merchantInfo = getIntent().getParcelableExtra(MERCHANTINFO);
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
        return R.layout.merchantdetails_ly;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == btnBuy) {
            PayActivity.toPay(this,merchantInfo);
        } else if (v == tvTel) {
            showCallDialog(merchantInfo.getTel());
        } else if (tvloction == v) {
            showMapDialog(merchantInfo);
        }
    }

    public static void toMerActivity(Context context, MerchantInfo info) {
        Intent intent1 = new Intent(context, MerchantDetailActivity.class);
        intent1.putExtra(MerchantDetailActivity.MERCHANTINFO, info);
        context.startActivity(intent1);
    }
}
