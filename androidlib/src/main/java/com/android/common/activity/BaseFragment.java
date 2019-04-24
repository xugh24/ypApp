package com.android.common.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xugh on 2019/4/24.
 * <p>
 * Fragment 实现懒加载 功能
 */

public abstract class BaseFragment extends Fragment {

    protected View rootView;
    private boolean isInitView = false;
    private boolean isVisible = false; // 对用户可见

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = initView(inflater,container);
        init();
        isInitView = true;
        isCanLoadData();
        return rootView;
    }

    protected abstract View initView(LayoutInflater inflater, ViewGroup container);

    protected abstract void init();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见，获取该标志记录下来
        if (isVisibleToUser) {
            isVisible = true;
            isCanLoadData();
        } else {
            isVisible = false;
        }
    }


    private void isCanLoadData() {
        //所以条件是view初始化完成并且对用户可见
        if (isInitView && isVisible) {
            /**
             * 加载要显示的数据
             */
            //防止重复加载数据
            isInitView = false;
            isVisible = false;
        }
    }





    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            onHide();
        } else {
            onShow();
        }
    }

    /**
     * 显示Fragment
     */
    protected abstract void onShow();

    /**
     * 隐藏View
     */
    public abstract void onHide();


}
