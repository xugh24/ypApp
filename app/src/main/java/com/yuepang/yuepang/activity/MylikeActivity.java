package com.yuepang.yuepang.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.yuepang.yuepang.R;

/**
 * 我的喜好页面
 */

public class MylikeActivity extends BaseActivity {

    private LinearLayout llcate;// 美食

    private LinearLayout llmovie;// 电影

    private LinearLayout llrecreation; // 娱乐

    private LinearLayout llgroup; // 团购

    private LinearLayout llhotel; // 酒店

    private LinearLayout lltravel; // 旅行

    private LinearLayout llktv; // ktv

    private LinearLayout llpet; //宠物

    private CheckBox chbCate;// 美食


    private CheckBox chbMovie;// 电影

    private CheckBox chbRecreation;//娱乐


    private CheckBox chbGroup;// 团购


    private CheckBox chbhotel;// 旅馆

    private CheckBox chbTravel;

    private CheckBox chbktv;

    private CheckBox chbpet;

    private Button btn_sub;


    @Override
    public String getMyTittle() {
        return "喜好";
    }

    @Override
    protected int getContentViewId() {
        return R.layout.mylike_ly;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
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
                break;


        }
    }

    public static void toThisActivity(Context context) {
        Intent intent4 = new Intent(context, MylikeActivity.class);
        context.startActivity(intent4);
    }
}
