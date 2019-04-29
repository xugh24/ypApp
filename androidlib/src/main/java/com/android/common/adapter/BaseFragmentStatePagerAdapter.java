package com.android.common.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by xugh on 2019/4/26.
 * 数量超过限制后会销毁Fragment实例
 */

public class BaseFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    public BaseFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
