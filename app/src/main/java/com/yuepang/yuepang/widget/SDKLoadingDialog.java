package com.yuepang.yuepang.widget;

import android.app.Dialog;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.activity.BaseActivity;

public class SDKLoadingDialog extends Dialog {

    public SDKLoadingDialog(BaseActivity activity) {
        super(activity);
        getWindow().setBackgroundDrawableResource(R.drawable.nothing);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout loading_progressBar = new LinearLayout(getContext());
        CustomImageView progress = new CustomImageView(activity);
        progress.setImageResource(R.drawable.ic_loading);
        Animation mRotateAnimation = new RotateAnimation(0.0f, 720.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateAnimation.setFillAfter(true);
        mRotateAnimation.setInterpolator(new LinearInterpolator());
        mRotateAnimation.setDuration(1200);
        mRotateAnimation.setRepeatCount(Animation.INFINITE);
        mRotateAnimation.setRepeatMode(Animation.RESTART);
        progress.setAnimation(mRotateAnimation);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
        loading_progressBar.addView(progress, params);
        setContentView(loading_progressBar);
    }

}