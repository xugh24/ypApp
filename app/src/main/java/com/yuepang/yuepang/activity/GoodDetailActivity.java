package com.yuepang.yuepang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.dialog.CallDialog;
import com.yuepang.yuepang.dialog.MapDialog;
import com.yuepang.yuepang.location.LatLng;
import com.yuepang.yuepang.model.GoodInfo;


/**
 * Created by xugh on 2019/4/2.
 */

public class GoodDetailActivity extends BaseActivity {

    public final static String GOODINFO = "goodInfo";

    private ImageView ivGood;// 商品图片

    private TextView goodTitle;// 商品标题

    private TextView goodMsg;// 商品介绍

    private TextView merName;// 商家名称

    private TextView merLoaction;// 商家地址

    private TextView merTel;// 商家电话

    private Button btnPay;// 支付按钮

    private GoodInfo info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        info = getIntent().getParcelableExtra(GOODINFO);
        goodTitle.setText(info.getTitle());
        goodMsg.setText(info.getMsg());
        merName.setText(info.getInfo().getName());
        merLoaction.setText(info.getInfo().getLocation());
        merTel.setText(info.getInfo().getTel());
    }

    @Override
    public String getMyTittle() {
        return "商品介绍";
    }

    @Override
    protected int getContentViewId() {
        return R.layout.good_ly;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == btnPay) {
            PayActivity.toPay(this,info.getInfo());
        } else if (merTel == v) {
            new CallDialog(this, info.getInfo().getTel()).show();
        } else if (merLoaction == v) {
            LatLng latLng = new LatLng(info.getInfo().getLatitude(), info.getInfo().getLongitude());
            new MapDialog(this, latLng, "西小口").show();
        }
    }
}
