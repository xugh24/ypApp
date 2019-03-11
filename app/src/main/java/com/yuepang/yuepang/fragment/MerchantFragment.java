package com.yuepang.yuepang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.LogUtils;

/**
 * Created by xugh on 2019/3/10.
 */

public class MerchantFragment extends BaseFragment

{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_ly, container, false);
        LogUtils.e("----cccccccc-onCreate");
        return view;
    }
}
