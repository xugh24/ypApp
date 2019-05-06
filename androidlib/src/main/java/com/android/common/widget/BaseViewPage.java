package com.android.common.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;

import com.android.common.inter.ViewPagechangeInter;
import com.android.common.utils.LogUtils;


/**
 * Created by xugh on 2019/4/26.
 */

public class BaseViewPage extends ViewPager {

    private int currentPosition;// 当前的位置

    private int lastPosition;// 上一个位置

    private int count;// 子view的个数

    private ViewPagechangeInter inter;


    /**
     * @param count page页数
     */
    @SuppressLint("ResourceType")
    public BaseViewPage(Context context, int count) {
        super(context);
        this.count = count;
        setId(12);
        lastPosition = -1;
        currentPosition = 0;
        setOffscreenPageLimit(count > 10 ? 10 : count);
        init();
    }

    private void init() {
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                LogUtils.e("onPageSelected" + position);
                if (position != currentPosition && inter != null) {
                    inter.hide(currentPosition);
                    // 设置新的当前展示ID
                    lastPosition = currentPosition;
                    currentPosition = position;
                    inter.show(position);
                    inter.pageChanged(lastPosition, currentPosition);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setCurrentItem(0);
    }

    public void addInter(ViewPagechangeInter inter) {
        this.inter = inter;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public int getLastPosition() {
        return lastPosition;
    }

}
