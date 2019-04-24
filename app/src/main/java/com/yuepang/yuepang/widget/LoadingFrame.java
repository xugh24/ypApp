package com.yuepang.yuepang.widget;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.common.widget.BaseLoadingFrame;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.SysUtils;

/**
 * Created by xugh on 2019/4/24.
 */

public class LoadingFrame extends BaseLoadingFrame {


    public LoadingFrame(Context context) {
        super(context);
    }

    @Override
    protected void onRefresh() {

    }

    /**
     * 加载无内容页面
     */
    @Override
    protected View createNoContentView() {
        return View.inflate(getContext(), R.layout.no_content_layout, null);
    }

    /**
     * 加载无网络页面
     */
    @Override
    protected View createOfflineView() {
        return View.inflate(getContext(), R.layout.no_content_layout, null);
    }

    @Override
    protected View createLoadingView() {
        final CustomImageView progress = new CustomImageView(getContext());
        progress.setImageResource(R.drawable.ic_loading);
        Animation mRotateAnimation = new RotateAnimation(0.0f, 720.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateAnimation.setFillAfter(true);
        mRotateAnimation.setInterpolator(new LinearInterpolator());
        mRotateAnimation.setDuration(1200);
        mRotateAnimation.setRepeatCount(Animation.INFINITE);
        mRotateAnimation.setRepeatMode(Animation.RESTART);
        progress.setAnimation(mRotateAnimation);
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        layout.addView(progress, lp);
        mTextTips = new TextView(getContext());
        int padding = SysUtils.dip2px(getContext(), 5);
        mTextTips.setPadding(padding, 0, padding, 0);
        mTextTips.setGravity(Gravity.CENTER_HORIZONTAL);
        mTextTips.setTextColor(0xffffffff);
        mTextTips.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        mTextTips.setBackgroundResource(R.drawable.bg_loading_tips);
        mTextTips.setVisibility(View.INVISIBLE);
        lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.topMargin = SysUtils.dip2px(getContext(), 10);
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        int margin = SysUtils.dip2px(getContext(), 20);
        lp.leftMargin = margin;
        lp.rightMargin = margin;
        layout.addView(mTextTips, lp);
        return layout;
    }

    @Override
    protected Boolean load(View mLoadingView) {
        return null;
    }

    @Override
    protected CharSequence getNoContentText() {
        return null;
    }

    @Override
    protected View createLoadedView() {
        return null;
    }

    @Override
    protected View createUnloadedView() {
        return null;
    }
}
