package com.android.common.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

/**
 * Created by xugh on 2019/4/20.
 * <p>
 * 管理 adater 通用优化代码
 */

public abstract class AdapterManage extends RecyclerView.OnScrollListener implements AbsListView.OnScrollListener {

    private boolean isLoadMoreData = true;// 是否需要加载内容

    private View mMoreView;// 加载更过的演示View

    public AdapterManage() {
        mMoreView = getMoreView();
        mMoreView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, /* mVActivity.dip2px(35) */
                ViewGroup.LayoutParams.WRAP_CONTENT));
    }


    private View getView() {
        return null;
    }

    protected abstract View getMoreView();


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

    }


}
