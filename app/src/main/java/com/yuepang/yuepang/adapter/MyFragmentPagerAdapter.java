package com.yuepang.yuepang.adapter;

import android.support.v4.app.FragmentManager;
import com.android.common.activity.BaseFragment;
import com.android.common.adapter.BaseFragmentPagerAdapter;
import com.yuepang.yuepang.fragment.BaseSecFragment;
import com.yuepang.yuepang.fragment.HandpickSecFragment;
import com.yuepang.yuepang.fragment.MerchantSecFragment;
import com.yuepang.yuepang.fragment.MineSecFragment;
import com.yuepang.yuepang.fragment.TopicSecFragment;


/**
 */

public class MyFragmentPagerAdapter extends BaseFragmentPagerAdapter {

    HandpickSecFragment handpickSecFragment;

    MerchantSecFragment merchantSecFragment;

    TopicSecFragment topicSecFragment;

    MineSecFragment mineSecFragment;

    public MyFragmentPagerAdapter(FragmentManager fm, int count) {
        super(fm, count);
        handpickSecFragment = new HandpickSecFragment();
        mineSecFragment = new MineSecFragment();
        topicSecFragment = new TopicSecFragment();
        merchantSecFragment = new MerchantSecFragment();
    }

    @Override
    public BaseSecFragment getItem(int position) {
        switch (position) {
            case 0:
                return handpickSecFragment;
            case 1:
                return merchantSecFragment;
            case 2:
                return topicSecFragment;
            case 3:
                return mineSecFragment;
        }
        return mineSecFragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
