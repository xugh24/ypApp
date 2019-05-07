package com.yuepang.yuepang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.android.common.annotation.view.BindView;
import com.android.common.annotation.view.BindViewByTag;
import com.android.common.annotation.view.OnClickView;
import com.android.common.utils.LogUtils;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.protocol.AddLikeProtocol;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的喜好页面
 */

public class MylikeActivity extends BaseActivity implements LoadCallBack {

    @BindView(id = R.id.chb_cate, click = true)
    private CheckBox chbCate;// 美食
    @BindView(id = R.id.chb_movie, click = true)
    private CheckBox chbMovie;// 电影
    @BindView(id = R.id.chb_recreation, click = true)
    private CheckBox chbRecreation;//娱乐
    @BindView(id = R.id.chb_group, click = true)
    private CheckBox chbGroup;// 团购
    @BindView(id = R.id.chb_hotel, click = true)
    private CheckBox chbhotel;// 旅馆
    @BindView(id = R.id.chb_travel, click = true)
    private CheckBox chbTravel;// 旅游
    @BindView(id = R.id.chb_ktv, click = true)
    private CheckBox chbktv;
    @BindView(id = R.id.chb_pet, click = true)
    private CheckBox chbpet;

    private List<CheckBox> boxs;

    private List<Integer> newList;


    @Override
    protected int getContentViewId() {
        return R.layout.mylike_ly;
    }

    @OnClickView({R.id.ll_cate, R.id.ll_movie, R.id.ll_recreation, R.id.ll_group,
            R.id.ll_travel, R.id.ll_hotel, R.id.ll_ktv, R.id.ll_pet, R.id.btn_sub})
    private String clik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boxs = new ArrayList<>();
        newList = new ArrayList<>();
        boxs.add(chbCate);
        boxs.add(chbMovie);
        boxs.add(chbRecreation);
        boxs.add(chbhotel);
        boxs.add(chbTravel);
        boxs.add(chbpet);
        boxs.add(chbGroup);
        boxs.add(chbktv);
        if (getUserInfo().getFavorite().size() > 0) {
            for (int i : getUserInfo().getFavorite()) {
                for (CheckBox box : boxs) {
                    int tag = Integer.valueOf((String) box.getTag());
                    if (i == tag) {
                        box.setChecked(true);
                    }
                }
            }
        }
    }

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

    private void sub() {// 上传喜好数据
        StringBuilder stringBuilder = new StringBuilder();
        newList.clear();
        for (CheckBox checkBox : boxs) {
            if (checkBox.isChecked()) {
                int temp = Integer.valueOf((String) checkBox.getTag());
                newList.add(temp);
                int tag = 0;
                if (temp == 1) {
                    tag = 0;
                } else {
                    tag = temp - 4;
                }
                if (stringBuilder.length() > 0) {
                    stringBuilder.append("####" + tag);
                } else {
                    stringBuilder.append(tag);
                }
            }
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
        if (callType.equals(CallType.SUCCESS)) {
            showToastSafe("保存成功");
            getUserInfo().getFavorite().clear();
            getUserInfo().getFavorite().addAll(newList);
        }


    }
}
