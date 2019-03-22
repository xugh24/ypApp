package com.yuepang.yuepang.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuepang.yuepang.Util.AnnotateUtil;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.widget.LoadingFrame;

/**
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    protected View contentView;

    protected LoadingFrame loadingFrame;



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
        loadingFrame = new LoadingFrame((BaseActivity) getActivity()) {
            @Override
            public boolean load(View loadingView) {
                return getData();
            }

            @Override
            public View createLoadedView() {
                initView();
                return contentView;
            }
        };
        loadingFrame.show();
        return loadingFrame;
    }

    protected abstract boolean getData();

    protected abstract void initView();

    public abstract void show();

    public abstract void hide();


    public abstract int getLyId();

    public BaseFragment() {

    }

    @Override
    public void onClick(View v) {

    }
}
