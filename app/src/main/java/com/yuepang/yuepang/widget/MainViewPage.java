package com.yuepang.yuepang.widget;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.common.inter.ViewHolderClick;
import com.android.common.widget.BaseViewHolder;
import com.android.common.widget.ViewPageLayout;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.CreateTopicActivity;
import com.yuepang.yuepang.adapter.MyFragmentPagerAdapter;

/**
 * Created by xugh on 2019/4/26.
 */

public class MainViewPage extends ViewPageLayout {
    MyFragmentPagerAdapter mfpa;
    FragmentActivity context;

    public MainViewPage(FragmentActivity context, ViewHolderClick listener) {
        super(context, listener);
        this.context = context;
        mfpa = new MyFragmentPagerAdapter(context.getSupportFragmentManager(), getCount()); //new myFragmentPagerAdater记得带上两个参数
        setAdapter(mfpa);
    }

    @Override
    protected void creatChildView(BaseViewHolder holder, int i) {
        holder.getView(R.id.ll_bar, true);
        ((ImageView) (holder.getView(R.id.iv, false))).setImageResource(getivSrcById(i));
        ((TextView) (holder.getView(R.id.tv_name, false))).setText(getTxtById(i));
    }

    public void selIv(int i, boolean sel) {
        getTabView().getHolder(i).getView(R.id.iv).setSelected(sel);
    }

    private int getivSrcById(int i) {
        switch (i) {
            case 0:
                return R.drawable.bar1;
            case 1:
                return R.drawable.bar2;
            case 2:
                return R.drawable.bar3;
            case 3:
                return R.drawable.bar4;
        }
        return R.drawable.bar1;
    }

    @Override
    protected int getCount() {
        return 4;
    }


    @Override
    public int getTabLayId() {
        return R.layout.tabview;
    }

    @Override
    public void pageChanged(int lastPosition, int position) {
        switch (position) {
            case 0:
                mfpa.getItem(position).setRightTitle(null);
                break;
            case 1:
                mfpa.getItem(position).setRightTitle(null);
                break;
            case 2:
                mfpa.getItem(position).setTvLeftTitle(null);
                mfpa.getItem(position).setRightTitle("创建");
                break;
            case 3:
                mfpa.getItem(position).setTvLeftTitle(null);
                mfpa.getItem(position).setRightTitle(null);
                break;
        }
        mfpa.getItem(position).setTitle(getTxtById(position));
        selIv(position, true);
        if (lastPosition != -1) {
            selIv(lastPosition, false);
        }
    }

    public String getTxtById(int i) {
        switch (i) {
            case 0:
                return "精选";
            case 1:
                return "商家";
            case 2:
                return "话题";
            case 3:
                return "我的";
        }
        return "精选";
    }

    public void onClikLeft() {
        switch (getCurrentPosition()) {
            case 0:
                mfpa.getItem(0).onClikLeft();
                break;
            case 1:
                mfpa.getItem(1).onClikLeft();
                break;
        }
    }

    public void onClikRight() {
        if (getCurrentPosition() == 2) {
            CreateTopicActivity.toThisActivity((Activity) getContext());
        }
    }

    public void refreshTop() {
        mfpa.getItem(2).onShow();
    }
}
