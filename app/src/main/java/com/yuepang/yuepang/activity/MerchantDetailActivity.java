package com.yuepang.yuepang.activity;

import android.net.wifi.p2p.WifiP2pManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;

/**
 * Created by xugh on 2019/3/19.
 *
 * 商家详情页
 */

public class MerchantDetailActivity extends BaseActivity {

    @BindView(id = R.id.mer_pic)
    private ImageView ivMer;

    @BindView(id = R.id.mer_title)
    private TextView tvName;

    @BindView(id = R.id.btn_buy, click = true)
    private Button btnBuy;


    @BindView(id = R.id.mer_msg)
    private TextView tvMsg;

    @BindView(id = R.id.mer_loction)
    private TextView tvloction;

    @BindView(id = R.id.mer_tel)
    private TextView tvTel;

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
            startActivity(PayActivity.class);
        }
    }
}
