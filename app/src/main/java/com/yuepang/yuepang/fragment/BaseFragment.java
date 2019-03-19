package com.yuepang.yuepang.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.AnnotateUtil;

/**
 * Created by xugh on 2019/3/6.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    protected View contentView;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (contentView == null) {
            contentView = inflater.inflate(getLyId(), container, false);
            AnnotateUtil.initBindView(this, contentView);
        }
        return contentView;
    }



    public abstract int getLyId();

    public BaseFragment() {

    }

    @Override
    public void onClick(View v) {

    }
}
