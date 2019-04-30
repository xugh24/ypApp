package com.android.common.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.common.utils.LogUtils;

/**
 * Created by xugh on 2019/4/24.
 * <p>
 * Fragment 实现懒加载 功能
 */

public abstract class BaseFragment extends Fragment {

    protected View rootView;
    private boolean isInitView = false;
    private boolean isVisible = false; // 对用户可见

    public BaseFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.e("---Fragment--onCreate---"+getClass().getName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initbeforeView();// 初始化配置
        rootView = initView(inflater, container);
        initafterView();//
        isInitView = true;
        isCanLoadData();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e("---Fragment--onDestroy---"+getClass().getName());
    }

    /**
     * 初始化参数在View 创建后执行
     */
    protected abstract void initafterView();

    protected abstract View initView(LayoutInflater inflater, ViewGroup container);

    /**
     * 配置初始化参数在View
     */
    protected abstract void initbeforeView();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见，获取该标志记录下来
        if (isVisibleToUser) {//如果用户可见
            isVisible = true; // 保存可见值
            isCanLoadData();  // 是否加载数据
        } else {
            isVisible = false;// 保存不可见
        }
    }

    private void isCanLoadData() {
        LogUtils.e("---Fragment--isCanLoadData---"+getClass().getName());
        //所以条件是view初始化完成并且对用户可见
        if (isInitView && isVisible) {
            initData();
            /**
             * 加载要显示的数据
             */
            //防止重复加载数据
            isInitView = false;
            isVisible = false;
        }
    }

    /**
     * 加载网络数据
     */
    protected abstract void initData();


    /**
     * 显示Fragment
     */
    public abstract void onShow();

    /**
     * 隐藏View
     */
    public abstract void onHide();


}
