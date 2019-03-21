package com.yuepang.yuepang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.AnnotateUtil;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.activity.MerchantActivity;
import com.yuepang.yuepang.activity.MerchantDetailActivity;

/**
 * Created by xugh on 2019/3/6.
 */

public class HandpickFragment extends BaseFragment {

    @BindView(id = R.id.merchant1_ly,click = true)
    private LinearLayout llmerchant1;

    @BindView(id = R.id.merchant2_ly,click = true)
    private LinearLayout llmerchant2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void initView() {

    }

    @Override
    public int getLyId() {
        return R.layout.handpick_ly;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.merchant1_ly:
                Intent intent1 = new Intent(getActivity(), MerchantDetailActivity.class);
                getActivity().startActivity(intent1);
                break;
            case R.id.merchant2_ly:
                Intent intent2 = new Intent(getActivity(), MerchantDetailActivity.class);
                getActivity().startActivity(intent2);
                break;
        }
    }
}
