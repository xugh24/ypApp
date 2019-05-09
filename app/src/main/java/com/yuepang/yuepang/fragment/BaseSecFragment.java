package com.yuepang.yuepang.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.common.annotation.view.AnnotateBindViewUtil;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.activity.MainActivity;

/**
 * Fragment 基类处理方法
 */

public abstract class BaseSecFragment extends com.android.common.activity.BaseFragment implements View.OnClickListener {

    protected View contentView;// 需要返回的主view

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        if (contentView == null) {// 创建View
            contentView = inflater.inflate(getLyId(), container, false); // 绑定基类View
            AnnotateBindViewUtil.initBindView(this, contentView, this);
        }
        return contentView;
    }

    /**
     * 请求数据
     */
    @Override
    protected abstract void initData() ;

    /**
     * onAttach 在 creatView 前调用 再onshow 后调用
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public void onShow() {
        LogUtils.e("onshow");
    }

    @Override
    public void onHide() {
        LogUtils.e("onHide");
    }


    public abstract int getLyId();


    public MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }

    /**
     * 接受外部点击右边文字的事件
     */
    public void onclikRight() {

    }

    /**
     * 接受外部点击左边文字的事件
     */
    public void onClikLeft() {

    }

    public void setTitle(String title) {
        if (getActivity() != null) {
            getMainActivity().getBarTitle().setTitle(title);
        }
    }

    public void setRightTitle(String title) {
        if (getActivity() != null) {
            getMainActivity().getBarTitle().setRightTitle(title);
        }
    }

    public void setTvLeftTitle(String title) {
        if (getActivity() != null) {
            getMainActivity().getBarTitle().setTvLeftTitle(title);
        }
    }


}
