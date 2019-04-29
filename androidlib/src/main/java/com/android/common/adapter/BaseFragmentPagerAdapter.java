package com.android.common.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.android.common.activity.BaseFragment;

import java.util.List;

/**
 * Created by xugh on 2019/4/26.
 * 数量超过限制后不会会销毁Fragment实例
 */

public abstract class BaseFragmentPagerAdapter extends FragmentPagerAdapter {

    private BaseFragment fm;
    private FragmentTransaction transaction;
    private int count;

    public BaseFragmentPagerAdapter(FragmentManager fm, int count) {
        super(fm);
        this.count = count;
        transaction = fm.beginTransaction();
    }

    @Override
    public abstract BaseFragment getItem(int position);

    public int getCount() {
        return count;
    }

    public void show(int position) {
        if (position < getCount() && getItem(position) != null) {
            getItem(position).onShow();
        }
    }

    public void hide(int position) {
        if (position < getCount()) {
            getItem(position).onHide();
        }
    }
}
