package com.yuepang.yuepang.activity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;

/**
 * 我的喜好页面
 */

public class MylikeActivity extends BaseActivity {

    @BindView(id = R.id.ll_cate, click = true)
    private LinearLayout llcate;// 美食

    @BindView(id = R.id.ll_movie, click = true)
    private LinearLayout llmovie;// 电影

    @BindView(id = R.id.ll_recreation, click = true)
    private LinearLayout llrecreation; // 娱乐

    @BindView(id = R.id.ll_group, click = true)
    private LinearLayout llgroup; // 团购

    @BindView(id = R.id.ll_hotel, click = true)
    private LinearLayout llhotel; // 酒店

    @BindView(id = R.id.ll_travel, click = true)
    private LinearLayout lltravel; // 旅行

    @BindView(id = R.id.ll_ktv, click = true)
    private LinearLayout llktv; // ktv

    @BindView(id = R.id.ll_pet, click = true)
    private LinearLayout llpet; //宠物

    @BindView(id = R.id.chb_cate)
    private CheckBox chbCate;// 美食


    @BindView(id = R.id.chb_movie)
    private CheckBox chbMovie;// 电影

    @BindView(id = R.id.chb_recreation)
    private CheckBox chbRecreation;//娱乐


    @BindView(id = R.id.chb_group)
    private CheckBox chbGroup;// 团购


    @BindView(id = R.id.chb_hotel)
    private CheckBox chbhotel;// 旅馆

    @BindView(id = R.id.chb_travel)
    private CheckBox chbTravel;

    @BindView(id = R.id.chb_ktv)
    private CheckBox chbktv;

    @BindView(id = R.id.chb_pet)
    private CheckBox chbpet;

    @BindView(id = R.id.chb_cate, click = true)
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
}
