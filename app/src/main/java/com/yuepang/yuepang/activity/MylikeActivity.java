package com.yuepang.yuepang.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.android.common.annotation.view.BindViewByTag;
import com.android.common.annotation.view.OnClickView;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.protocol.AddLikeProtocol;

/**
 * 我的喜好页面
 */

public class MylikeActivity extends BaseActivity implements LoadCallBack {

    @BindViewByTag
    private CheckBox chbCate;// 美食
    @BindViewByTag
    private CheckBox chbMovie;// 电影
    @BindViewByTag
    private CheckBox chbRecreation;//娱乐
    @BindViewByTag
    private CheckBox chbGroup;// 团购
    @BindViewByTag
    private CheckBox chbhotel;// 旅馆
    @BindViewByTag
    private CheckBox chbTravel;// 旅游
    @BindViewByTag
    private CheckBox chbktv;
    @BindViewByTag
    private CheckBox chbpet;


    @Override
    protected int getContentViewId() {
        return R.layout.mylike_ly;
    }

    @OnClickView({R.id.ll_cate, R.id.ll_movie, R.id.ll_recreation, R.id.ll_group,
            R.id.ll_travel, R.id.ll_hotel, R.id.ll_ktv, R.id.ll_pet, R.id.btn_sub})
    private String clik;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_cate:
                chbCate.setChecked(chbCate.isChecked() ? false : true);
                break;
            case R.id.ll_movie:
                chbMovie.setChecked(chbMovie.isChecked() ? false : true);
                break;
            case R.id.ll_recreation:
                chbRecreation.setChecked(chbRecreation.isChecked() ? false : true);
                break;
            case R.id.ll_group:
                chbGroup.setChecked(chbGroup.isChecked() ? false : true);
                break;
            case R.id.ll_travel:
                chbTravel.setChecked(chbTravel.isChecked() ? false : true);
                break;
            case R.id.ll_hotel:
                chbhotel.setChecked(chbhotel.isChecked() ? false : true);
                break;
            case R.id.ll_ktv:
                chbktv.setChecked(chbktv.isChecked() ? false : true);
                break;
            case R.id.ll_pet:
                chbpet.setChecked(chbpet.isChecked() ? false : true);
            case R.id.btn_sub:
                sub();
                break;
        }
    }

    private void sub() {
        StringBuilder stringBuilder = new StringBuilder();
        if (chbCate.isChecked()) {
            stringBuilder.append("1");
        }
        if (chbMovie.isChecked()) {
            stringBuilder.append("####2");
        }
        if (chbRecreation.isChecked()) {
            stringBuilder.append("####3");
        }
        if (chbGroup.isChecked()) {
            stringBuilder.append("####4");
        }
        if (chbhotel.isChecked()) {
            stringBuilder.append("####5");
        }
        if (chbTravel.isChecked()) {
            stringBuilder.append("####6");
        }
        if (chbktv.isChecked()) {
            stringBuilder.append("####7");
        }
        if (chbCate.isChecked()) {
            stringBuilder.append("####8");
        }
        new AddLikeProtocol(this, this, stringBuilder.toString()).request();

    }

    @Override
    public String getMyTittle() {
        return "喜好";
    }

    public static void toThisActivity(Context context) {
        Intent intent4 = new Intent(context, MylikeActivity.class);
        context.startActivity(intent4);
    }

    @Override
    public void loadCallBack(CallType callType, int CODE, String msg, Object info) {

    }
}
