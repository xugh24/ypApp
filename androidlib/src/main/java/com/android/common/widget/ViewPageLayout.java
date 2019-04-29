package com.android.common.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.common.adapter.BaseFragmentPagerAdapter;
import com.android.common.inter.ViewHolderClick;
import com.android.common.inter.ViewPagechangeInter;

/**
 * Created by xugh on 2019/4/26.
 */

public abstract class ViewPageLayout extends LinearLayout implements ViewPagechangeInter {

    private LinearLayout lltag;

    private BaseViewPage baseViewPage;

    private ViewPageTab viewPageTab;

    private ViewHolderClick listener;

    private BaseFragmentPagerAdapter adapter;

    @SuppressLint("ResourceType")
    public ViewPageLayout(Context context, ViewHolderClick listener) {
        super(context);
        this.listener = listener;// 设置切换回调
        baseViewPage = new BaseViewPage(context, getCount());
        baseViewPage.addInter(this);
        setOrientation(VERTICAL);
        lltag = new LinearLayout(context);
        lltag.setOrientation(HORIZONTAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.weight = 1;
        addView(baseViewPage, lp);
        LinearLayout.LayoutParams taglp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(lltag, taglp);
        initTabView();
    }

    private void initTabView() {
        viewPageTab = new ViewPageTab(getContext(), getCount(), getTabLayId());
        addTabView();
        initChildView();
    }

    public void setAdapter(BaseFragmentPagerAdapter adapter) {
        this.adapter = adapter;
        baseViewPage.setAdapter(adapter);
        setCurrentItem(0);
        show(0);
        pageChanged(-1, 0);
    }

    protected void initChildView() {
        for (int i = 0; i < getCount(); i++) {
            creatChildView(viewPageTab.getHolder(i), i);
            viewPageTab.getHolder(i).setListener(listener);
        }
    }

    protected abstract void creatChildView(BaseViewHolder holder, int i);

    private void addTabView() {
        LinearLayout.LayoutParams taglp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        taglp.weight = 1;
        for (int i = 0; i < getCount(); i++) {
            if (viewPageTab.getHolder(i).getmConvertView() != null) {
                lltag.addView(viewPageTab.getHolder(i).getmConvertView(), taglp);
            }
        }
    }

    public void setCurrentItem(int position) {
        baseViewPage.setCurrentItem(position);
    }

    /**
     * 获得当前的标签
     */
    public int getCurrentPosition() {
        return baseViewPage.getCurrentPosition();
    }

    public ViewPageTab getTabView() {
        return viewPageTab;
    }

    protected abstract int getCount();

    public abstract int getTabLayId();

    @Override
    public void hide(int position) {
        adapter.hide(position);
    }

    @Override
    public void show(int position) {
        adapter.show(position);
    }

    @Override
    public abstract void pageChanged(int lastPosition, int position);
}
