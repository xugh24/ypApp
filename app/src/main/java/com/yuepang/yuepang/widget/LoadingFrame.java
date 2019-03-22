/*
 * File Name: LoadingFrame.java
 * History:
 * Created by Siyang.Miao on 2011-9-22
 */
package com.yuepang.yuepang.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.Util.SysUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.async.AsyncContentLoader;
import com.yuepang.yuepang.async.UIBackgroundTask;


@SuppressLint("NewApi")
public abstract class LoadingFrame extends RelativeLayout {
    // ==========================================================================
    // Constants
    // ==========================================================================
    private static final String TAG = "LoadingFrame";
    private static final int STATE_UNLOADED = 0;
    private static final int STATE_LOADING = 1;
    private static final int STATE_LOADED = 2;

    // ==========================================================================
    // Fields
    // ==========================================================================

    private View mUnloadedView;

    private View mLoadingView;

    private View mLoadedView;

    private View mOfflineView;

    private View mNoContentView;

    private int mState;

    private LoadingTask mCurrentLoadingTask;

    private BaseActivity activity;

    protected TextView mTextTips;

    private String mTips;

    /**
     * 目前框架中的ViewPager在生成子视图时是一次性加载完的，而且PageGroup中调用loadPage、 createLoadedView等方法引用的position对象
     * 也从一开始就确定了，如果ViewPager被重新排序，那么还是旧的id,这个时间会发生索引错误 。为了解决这个问题，增加index属性，内部方法中需要
     * 调用方法获得index,每次viewPager视图刷新时，也应该重置index
     */
    private int index;

    // ==========================================================================
    // Constructors
    // ==========================================================================
    public LoadingFrame(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public LoadingFrame(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingFrame(BaseActivity activity) {
        super(activity);
        init();
    }

    public View getLoadedView() {
        return mLoadedView;
    }

    public CharSequence getCurrentTips() {
        if (mTextTips != null) {
            return mTextTips.getText();
        }
        return null;
    }

    // ==========================================================================
    // Setters
    // ==========================================================================


    public void setLoadingText(final String tips) {
        if (!TextUtils.isEmpty(tips) && null != mTextTips) {
            mTextTips.setText(tips);
            mTextTips.setVisibility(View.VISIBLE);
        }
    }

    // ==========================================================================
    // Methods
    // ==========================================================================

    /**
     *
     */
    private void init() {
        mState = STATE_UNLOADED;
        mUnloadedView = createUnloadedView();
        if (null != mUnloadedView) {
            mUnloadedView.setTag("mUnloadedView");
            addView(mUnloadedView, new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
        mLoadingView = createLoadingView();
        if (null != mLoadingView) {
            mLoadingView.setTag("mLoadingView");
            addView(mLoadingView, new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }

        mOfflineView = createOfflineView();
        if (null != mOfflineView) {
            mOfflineView.setTag("mOfflineView");
            mOfflineView.setVisibility(View.GONE);
            addView(mOfflineView, new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }

        mNoContentView = createNoContentView();
        if (null != mNoContentView) {
            mNoContentView.setVisibility(View.GONE);
            addView(mNoContentView, new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
    }

    public void show() {
        show(true);
    }

    public void show(boolean showLoadingView) {
        // mPath = PathAnalysis.getPath();
        // PathAnalysis.append(mLoader.getFrameUiNodeCode());
        synchronized (this) {
            switch (mState) {
                case STATE_UNLOADED:
                    // 把状态标记为正在加载
                    mState = STATE_LOADING;
                    if (showLoadingView) {
                        // 移除加载完成视图
                        if (null != mLoadedView) {
                            removeView(mLoadedView);
                            mLoadedView = null;
                        }
                        // 隐藏离线视图
                        if (null != mOfflineView) {
                            mOfflineView.setVisibility(View.GONE);
                        }

                        // 显示正在加载的视图
                        if (null != mLoadingView) {
                            // 设置提示文字
                            if (isShowLoading()) {
                                setLoadingText("加载中");
                            }
                            mLoadingView.setVisibility(View.VISIBLE);
                        }
                        if (null != mNoContentView) {
                            mNoContentView.setVisibility(View.GONE);
                        }
                    }
                    if (mCurrentLoadingTask != null) {
                        mCurrentLoadingTask.cancel();
                    }
                    mCurrentLoadingTask = new LoadingTask();
                    AsyncContentLoader.getInstance().addTask(mCurrentLoadingTask);
                    break;

                case STATE_LOADING:
                    // Do nothing
                    LogUtils.d("Current state is LOADING, do nothing!");
                    break;

                case STATE_LOADED:
                    LogUtils.d("Current state is LOADED, refresh!");
                    onRefresh();
                    break;
                default:
                    break;
            }
        }
    }

    public void restartAnimation() {
        if (null != mLoadingView) {
            mLoadingView.setVisibility(VISIBLE);
        }
    }

    public void reset() {
        synchronized (this) {
            mState = STATE_UNLOADED;
        }
    }

    public void resetNo() {
        synchronized (this) {
            if (null != mLoadingView) {
                mLoadingView.setVisibility(View.GONE);
            }
            if (null != mLoadedView) {
                mLoadedView.setVisibility(View.GONE);
            }
            if (null != mUnloadedView) {
                mUnloadedView.setVisibility(View.GONE);
            }

            mNoContentView.setVisibility(View.VISIBLE);

            mState = STATE_UNLOADED;
            boolean isInView = false;
            for (int i = 0; i < getChildCount(); i++) {
                if (getChildAt(i) == mNoContentView) {
                    isInView = true;
                    break;
                }
            }
            if (!isInView) {
                addView(mNoContentView, new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT));
            }
        }
    }

    /**
     *
     */
    public void resetYes() {
        synchronized (this) {
            if (null != mLoadingView) {
                mLoadingView.setVisibility(View.GONE);
            }
            if (null != mUnloadedView) {
                mUnloadedView.setVisibility(View.GONE);
            }
            if (null != mOfflineView) {
                mOfflineView.setVisibility(View.GONE);
            }
            if (null != mNoContentView) {
                mNoContentView.setVisibility(View.GONE);
            }
            if (null == mLoadedView || mLoadedView == mNoContentView) {
                mLoadedView = createLoadedView();
            }

            if (null != mLoadedView) {
                ViewParent vp = mLoadedView.getParent();
                if (vp instanceof ViewGroup) {
                    ((ViewGroup) vp).removeView(mLoadedView);
                }
                ViewGroup.LayoutParams lp = mLoadedView.getLayoutParams();
                if (lp == null) {
                    mLoadedView.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,
                            LayoutParams.FILL_PARENT));
                } else if (!(lp instanceof RelativeLayout.LayoutParams)) {
                    mLoadedView.setLayoutParams(new RelativeLayout.LayoutParams(lp.width, lp.height));
                }
                mLoadedView.setVisibility(View.VISIBLE);
                measureAndLayoutLoadedView(mLoadedView);
                addView(mLoadedView);
            }

            mState = STATE_LOADED;
        }
    }

    private void measureAndLayoutLoadedView(View loadedView) {
        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.AT_MOST);
        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.AT_MOST);
        loadedView.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        loadedView.layout(getPaddingLeft(), getPaddingTop(), getRight() - getLeft() - getPaddingLeft()
                - getPaddingRight(), getBottom() - getTop() - getPaddingTop() - getPaddingBottom());
    }

    // ==========================================================================
    // Inner/Nested Classes
    // ==========================================================================
    class LoadingTask extends UIBackgroundTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground() {
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
            return load(mLoadingView);
        }

        @Override
        protected void onProgressUpdate(Void... progress) {

        }

        @Override
        protected void onPostExecute(Boolean result) {

            if (!result) {
                mState = STATE_UNLOADED;
                if (null != mLoadingView) {
                    mLoadingView.setVisibility(View.GONE);
                    mOfflineView.setVisibility(View.VISIBLE);
                }
                return;
            }
            boolean hasContent = hasContent();
            if (hasContent) {
                if (mLoadedView != null)
                    removeView(mLoadedView);
                mLoadedView = createLoadedView();
                if (mNoContentView != null) {
                    mNoContentView.setVisibility(View.GONE);
                }
            } else {
                if (mNoContentView != null) {
                    if (!TextUtils.isEmpty(getNoContentText())) {
                        ((TextView) mNoContentView.findViewWithTag("azplg_no_content_layout_txt_no_content")).setText(getNoContentText());
                    }
                    mNoContentView.setVisibility(View.VISIBLE);
                }
                mLoadedView = mNoContentView;
            }
            if (null != mLoadedView) {
                ViewParent vp = mLoadedView.getParent();
                if (vp instanceof ViewGroup) {
                    ((ViewGroup) vp).removeView(mLoadedView);
                }
                ViewGroup.LayoutParams lp = mLoadedView.getLayoutParams();
                if (lp == null) {
                    mLoadedView.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                            LayoutParams.MATCH_PARENT));
                } else if (!(lp instanceof RelativeLayout.LayoutParams)) {
                    mLoadedView.setLayoutParams(new RelativeLayout.LayoutParams(lp.width, lp.height));
                }
                mLoadedView.setVisibility(View.VISIBLE);
                addView(mLoadedView, new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            }
            if (null != mLoadingView) {

                if (getLoadingViewDelayedTime() > 0) {
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mLoadingView.setVisibility(View.GONE);
                        }
                    }, getLoadingViewDelayedTime());
                } else {
                    mLoadingView.setVisibility(View.GONE);
                }
            }
            if (hasContent) {
                mState = STATE_LOADED;
            } else {
                mState = STATE_UNLOADED;
            }

        }

        @Override
        protected boolean match(Object... params) {
            return false;
        }

    }

    public View createUnloadedView() {
        return null;
    }


    /**
     * 供子类使用以便在必要情况下提供自定义无内容文本信息
     *
     * @return
     */
    public String getNoContentText() {
        return null;
    }

    public View createLoadingView() {
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
        int padding = SysUtils.dip2px(getContext(),5);
        mTextTips.setPadding(padding, 0, padding, 0);
        mTextTips.setGravity(Gravity.CENTER_HORIZONTAL);
        mTextTips.setTextColor(0xffffffff);
        mTextTips.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        mTextTips.setBackgroundResource(R.drawable.bg_loading_tips);
        mTextTips.setVisibility(View.INVISIBLE);
        lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.topMargin =  SysUtils.dip2px(getContext(),10);
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        int margin =  SysUtils.dip2px(getContext(),20);
        lp.leftMargin = margin;
        lp.rightMargin = margin;
        layout.addView(mTextTips, lp);
        return layout;
    }

    /**
     * loadview为false时的页面
     */
    public View createOfflineView() {
        View v = View.inflate(getContext(),R.layout.no_content_layout,null);
        ((ImageView) v.findViewWithTag("azplg_no_content_layout_img_icon")).setImageResource(R.drawable.img_expression_sad);
        return v;
    }


    /**
     * 加载失败页面
     */
    public View createNoContentView() {
        View v = View.inflate(getContext(),R.layout.no_content_layout,null);
        ((ImageView) v.findViewWithTag("azplg_no_content_layout_img_icon")).setImageResource(R.drawable.img_expression_sad);
        return v;
    }

    public abstract boolean load(View loadingView);

    public abstract View createLoadedView();

    public void onRefresh() {
    }

    public boolean hasContent() {
        return true;
    }

    public boolean isShowLoading() {
        return true;
    }

    /**
     * 获得loadingView延时隐藏的显示的时间 子类如果没有重写这个方法不会延时，只所以这样写是因为魅族4破手机在显示内容时会显示一下灰色的头，所以先想个临时的 办法，让loadingView慢点消失，这样用户就看不到了
     *
     * @return
     */

    protected int getLoadingViewDelayedTime() {
        return -1;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
