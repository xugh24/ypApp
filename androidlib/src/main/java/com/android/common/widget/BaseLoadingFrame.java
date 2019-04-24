package com.android.common.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.common.async.AsyncContentLoader;
import com.android.common.async.UIBackgroundTask;
import com.android.common.utils.LogUtils;
import static com.android.common.widget.BaseLoadingFrame.LoadingFrameState.STATE_LOADED;
import static com.android.common.widget.BaseLoadingFrame.LoadingFrameState.STATE_LOADING;
import static com.android.common.widget.BaseLoadingFrame.LoadingFrameState.STATE_UNLOADED;

/**
 * Created by xugh on 2019/4/24.
 */

public abstract class BaseLoadingFrame extends RelativeLayout {

    private View mUnloadedView;

    private View mLoadingView;

    private View mLoadedView;

    private View mOfflineView;

    private View mNoContentView;

    private LoadingFrameState mState; // View的状态标志位

    protected TextView mTextTips;

    private LoadingTask mCurrentLoadingTask;

    public BaseLoadingFrame(Context context) {
        super(context);
        mState = STATE_UNLOADED;//  初始化状态
        for (ViewType viewType : ViewType.values()) { // 初始化各种状态的View
            initView(viewType);
        }
    }

    /**
     * 初始化预加载View
     */
    private void initView(ViewType viewType) {
        switch (viewType) {
            case UNLOADED_VIEW:
                initUnloadedView(viewType);
                break;
            case LOADING_VIEW:
                initLoadingView(viewType);
                break;
            case OFFLINE_VIEW:
                initOfflineView(viewType);
                break;
            case CONTENT_VIEW:
                initNoContentView(viewType);
                break;
        }
    }

    /**
     * 获取数据加载View
     *
     * @param showLoadingView 是否需要展示加载中View
     */
    public void show(boolean showLoadingView) {
        synchronized (this) {
            switch (mState) {
                case STATE_UNLOADED://如果状态为未加载，把状态标记为正在加载
                    mState = STATE_LOADING;
                    if (showLoadingView) {
                        removeLoadedView();// 移除加载完成视图
                        hideOfflineView();//隐藏离线视图
                        hideNoContentView();// 
                        showLoadingView();// 显示加载中的view
                    }
                    cancelCurrentTask();
                    mCurrentLoadingTask = new LoadingTask();
                    AsyncContentLoader.getInstance().addTask(mCurrentLoadingTask);
                    break;
                case STATE_LOADING:  // Do nothing
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

    /**
     * 取消当前请求线程
     */
    private void cancelCurrentTask() {
        if (mCurrentLoadingTask != null) {
            mCurrentLoadingTask.cancel();
        }
    }

    private void showLoadingView() {
        if (null != mLoadingView) {// 显示正在加载的视图
            // 设置提示文字
            if (isShowLoading()) {
                setLoadingText("加载中");
            }
            mLoadingView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏无内容视图
     */
    private void hideNoContentView() {
        if (null != mNoContentView) {
            mNoContentView.setVisibility(View.GONE);
        }
    }

    /**
     * 隐藏离线视图
     */
    private void hideOfflineView() {
        if (null != mOfflineView) {
            mOfflineView.setVisibility(View.GONE);
        }
    }

    /**
     * 移除主上一次加载完成的View
     */
    private void removeLoadedView() {
        if (null != mLoadedView) {
            ViewParent vp = mLoadedView.getParent();
            if (vp instanceof ViewGroup) {
                ((ViewGroup) vp).removeView(mLoadedView);
            }
            mLoadedView = null;
        }
    }

    protected abstract void onRefresh();

    public boolean isShowLoading() {
        return true;
    }


    public void setLoadingText(final String tips) {
        if (!TextUtils.isEmpty(tips) && null != mTextTips) {
            mTextTips.setText(tips);
            mTextTips.setVisibility(View.VISIBLE);
        }
    }




    protected abstract View createNoContentView();

    protected abstract View createOfflineView();

    /**
     * 创建展示中的View
     */
    protected abstract View createLoadingView();


    /**
     * 初始化离线View
     */
    private void initOfflineView(ViewType viewType) {
        mOfflineView = createOfflineView();
        addChildrenView(mOfflineView, viewType);
        mOfflineView.setVisibility(View.GONE);
    }

    /**
     * 初始化加载中的View
     */
    private void initLoadingView(ViewType viewType) {
        mLoadingView = createUnloadedView();
        addChildrenView(mLoadingView, viewType);
    }

    /**
     * 初始化无主内容的View
     */
    private void initNoContentView(ViewType viewType) {
        mNoContentView = createNoContentView();
        if (null != mNoContentView) {
            mNoContentView.setVisibility(View.GONE);
        }
        addChildrenView(mNoContentView, viewType);
    }

    /**
     * 初始化未加载的View
     */
    private void initUnloadedView(ViewType viewType) {
        mUnloadedView = createUnloadedView();
        addChildrenView(mUnloadedView, viewType);
    }

    /**
     * 添加各种状态的View
     */
    private void addChildrenView(View view, ViewType viewType) {
        if (view != null) {
            view.setTag(viewType);
            addView(view, new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
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
            if (hasContent()) {
                removeLoadedView();
                removeLoadedView();
                mLoadedView = createLoadedView();
                hideNoContentView();
            } else {
                showNoContentView();
                mLoadedView = mNoContentView;
            }
            removeLoadedView();
            addLoadeView();
            showLoadeView();
            if (hasContent()) {
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

    private void showLoadeView() {
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
    }

    private void addLoadeView() {
        if (mLoadedView != null) {
            mLoadedView.setVisibility(View.VISIBLE);
            addView(mLoadedView, new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
    }

    private void showNoContentView() {
        if (mNoContentView != null) {
            if (!TextUtils.isEmpty(getNoContentText())) {
                ((TextView) mNoContentView.findViewWithTag("azplg_no_content_layout_txt_no_content")).setText(getNoContentText());
            }
            mNoContentView.setVisibility(View.VISIBLE);
        }
    }

    protected abstract Boolean load(View mLoadingView);

    protected abstract CharSequence getNoContentText();

    protected abstract View createLoadedView();

    private boolean hasContent() {
        return true;
    }

    /**
     * 创建未开始View
     */
    protected abstract View createUnloadedView();

    public enum LoadingFrameState {
        STATE_UNLOADED, STATE_LOADING, STATE_LOADED
    }

    public enum ViewType {
        UNLOADED_VIEW, OFFLINE_VIEW, LOADING_VIEW, CONTENT_VIEW
    }

    /**
     * 获得loadingView延时隐藏的显示的时间 子类如果没有重写这个方法不会延时，
     *
     * @return
     */

    protected int getLoadingViewDelayedTime() {
        return -1;
    }
}
