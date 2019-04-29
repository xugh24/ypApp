package com.android.common.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;


/**
 * Created by xugh on 2019/4/24.
 */

public abstract class BaseFirstLoadingFrame extends RelativeLayout {


    private View mUnloadedView;

    private View mLoadingView;

    private View mLoadedView;

    private View mOfflineView;


    public BaseFirstLoadingFrame(Context context) {
        super(context);
        for (ViewType viewType : ViewType.values()) { // 初始化各种状态的View
            initView(viewType);
        }
    }

    /**
     * 初始化预加载View
     */
    private void initView(BaseFirstLoadingFrame.ViewType viewType) {
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
        }
    }

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
     * 初始化未加载的View
     */
    private void initUnloadedView(BaseFirstLoadingFrame.ViewType viewType) {
        mUnloadedView = createUnloadedView();
        addChildrenView(mUnloadedView, viewType);
    }

    protected void createNewLoadedView() {
        mLoadedView = createLoadedView();//
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

    protected void addLoadeView() {
        if (mLoadedView.getParent() != null) {
            ((ViewGroup) mLoadedView.getParent()).removeView(mLoadedView);
        }
        addChildrenView(mLoadedView, ViewType.CONTENT_VIEW);
    }

    protected void showLoadeView() {
        if (getLoadingViewDelayedTime() > 0) {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    showView(ViewType.CONTENT_VIEW);
                }
            }, getLoadingViewDelayedTime());
        } else {
            showView(ViewType.CONTENT_VIEW);
        }
    }

    /**
     * 获得loadingView延时隐藏的显示的时间 子类如果没有重写这个方法不会延时，
     *
     * @return
     */

    protected int getLoadingViewDelayedTime() {
        return -1;
    }


    /**
     * 移除主上一次加载完成的View
     */
    protected void removeLoadedView() {
        if (null != mLoadedView) {
            ViewParent vp = mLoadedView.getParent();
            if (vp instanceof ViewGroup) {
                ((ViewGroup) vp).removeView(mLoadedView);
            }
            mLoadedView = null;
        }
    }

    /**
     *
     */
    public void showView(ViewType viewType) {
        setVisibility(mUnloadedView, viewType);
        setVisibility(mLoadingView, viewType);
        setVisibility(mLoadedView, viewType);
        setVisibility(mOfflineView, viewType);
    }

    private void setVisibility(View view, ViewType viewType) {
        if (view != null) {
            if (view.getTag().equals(viewType)) {
                view.setVisibility(VISIBLE);
            } else {
                view.setVisibility(GONE);
            }
        }
    }


    /**
     * 创建未开始View
     */
    protected abstract View createUnloadedView();

    /**
     * 创建无内容View
     */
    protected abstract View createOfflineView();

    /**
     * 创建展示中的View
     */
    protected abstract View createLoadingView();

    protected abstract View createLoadedView();


    public enum ViewType {
        UNLOADED_VIEW, OFFLINE_VIEW, LOADING_VIEW, CONTENT_VIEW
    }
}
