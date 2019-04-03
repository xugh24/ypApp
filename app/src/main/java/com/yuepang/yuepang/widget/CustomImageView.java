package com.yuepang.yuepang.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.yuepang.yuepang.Util.LogUtils;


public class CustomImageView extends ImageView {
    // ==========================================================================
    // Constants
    // ==========================================================================
    private static final boolean FADE_IN_ENABLED = true;

    // ==========================================================================
    // Fields
    // ==========================================================================
    private Animation mFadeInAnim;

    private long mUIThreadId;

    private boolean mLayoutRequestFromSetImage;

    // ==========================================================================
    // Constructors
    // ==========================================================================
    public CustomImageView(Context context) {
        super(context);
        mUIThreadId = android.os.Process.myTid();
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mUIThreadId = android.os.Process.myTid();
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mUIThreadId = android.os.Process.myTid();
    }

    // ==========================================================================
    // Getters
    // ==========================================================================

    // ==========================================================================
    // Setters
    // ==========================================================================

    // ==========================================================================
    // Methods
    // ==========================================================================
    public void fadeIn() {
        if (FADE_IN_ENABLED) {
            synchronized (this) {
                if (null != mFadeInAnim) {
                    clearAnimation();
                }
                mFadeInAnim = getFadeInAnimation();
                startAnimation(mFadeInAnim);
            }
        }
    }

    protected Animation getFadeInAnimation() {
            Animation anim;
            // Create a fade in alpha animation
            anim = new AlphaAnimation(0f, 1f);
            anim.setDuration(300);
            anim.setInterpolator(new AccelerateInterpolator());
            return anim;
    }

    public void cancelAnimation() {
        if (FADE_IN_ENABLED) {
            cancelAnimationInner();
            if (android.os.Process.myTid() == mUIThreadId) {
                invalidate();
            } else {
                postInvalidate();
            }
        }
    }

    private void cancelAnimationInner() {
        if (FADE_IN_ENABLED) {
            synchronized (this) {
                if (null != mFadeInAnim) {
                    clearAnimation();

                    Drawable d = CustomImageView.this.getDrawable().getCurrent();
                    if (null != d) {
                        d.setAlpha(255);
                    }
                    mFadeInAnim = null;
                }
            }
        }
    }

    @Override
    public void requestLayout() {
        if (mLayoutRequestFromSetImage) {
        } else {
            super.requestLayout();
        }
    }

    @Override
    public void setImageDrawable(final Drawable d) {
        Drawable oldDrb = super.getDrawable();
        if (oldDrb == d) {
            return;
        }
        cancelAnimationInner();
        try {
            mLayoutRequestFromSetImage = true;
            if (oldDrb instanceof AnimationDrawable) {
                ((AnimationDrawable) oldDrb).stop();
            }
            super.setImageDrawable(d);
            mLayoutRequestFromSetImage = false;
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    public void setImageResource(int resId) {
        cancelAnimationInner();
        super.setImageResource(resId);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        Drawable d = getDrawable();
        if (d instanceof AnimationDrawable) {
            if (visibility == View.VISIBLE) {
                ((AnimationDrawable) d).start();
            } else {
                ((AnimationDrawable) d).stop();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() instanceof BitmapDrawable) {
            if (((BitmapDrawable) getDrawable()).getBitmap() != null
                    && ((BitmapDrawable) getDrawable()).getBitmap().isRecycled()) {
                // LogUtils.e("------------------detected recycled bitmap!-----------------");
                return;
            }
        }
        super.onDraw(canvas);
    }

}
