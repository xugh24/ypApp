package com.yuepang.yuepang.activity;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        merchantInfo = getIntent().getParcelableExtra(MERCHANTINFO);
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
        return R.layout.merchantdetails_ly;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == btnBuy) {
            Intent intent = new Intent();
            intent.putExtra(MERCHANTINFO,merchantInfo);
            intent.setClass(this,PayActivity.class);
            startActivity(intent);
        } else if (v == tvTel) {
            new CallDialog(this, merchantInfo.getTel()).show();
        } else if (tvloction == v) {
            LatLng latLng = new LatLng(merchantInfo.getLatitude(), merchantInfo.getLongitude());
            new MapDialog(this, latLng, "西小口").show();
        }
    }
}
