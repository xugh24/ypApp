package com.android.common.widget;

import android.content.Context;
import com.android.common.async.AsyncContentLoader;
import com.android.common.async.UIBackgroundTask;
import com.android.common.utils.LogUtils;
import static com.android.common.widget.BaseLoadingFrame.LoadingFrameState.STATE_LOADED;
import static com.android.common.widget.BaseLoadingFrame.LoadingFrameState.STATE_LOADING;
import static com.android.common.widget.BaseLoadingFrame.LoadingFrameState.STATE_UNLOADED;


/**
 * Created by xugh on 2019/4/24.
 */

public abstract class BaseLoadingFrame extends BaseFirstLoadingFrame {


    private LoadingTask mCurrentLoadingTask;

    private LoadingFrameState mState = STATE_LOADING;

    public BaseLoadingFrame(Context context) {
        super(context);
        mState = STATE_UNLOADED;//  初始化状态
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
                        showView(ViewType.LOADING_VIEW);
                    }
                    startNewTask();
                    break;
                case STATE_LOADING:  // Do nothing
                    LogUtils.d("Current state is LOADING, do nothing!");
                    showView(ViewType.LOADING_VIEW);
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

    public void reset() {
        synchronized (this) {
            mState = STATE_UNLOADED;
        }
    }

    /**
     * 取消当前请求线程
     */
    private void startNewTask() {
        if (mCurrentLoadingTask != null) {
            mCurrentLoadingTask.cancel();
        }
        mCurrentLoadingTask = new LoadingTask();
        AsyncContentLoader.getInstance().addTask(mCurrentLoadingTask);
    }

    protected abstract void onRefresh();

    public void show() {
        show(true);
    }

    // ==========================================================================
    // Inner/Nested Classes
    // ==========================================================================
    class LoadingTask extends UIBackgroundTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground() {
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
            return load();
        }

        @Override
        protected void onProgressUpdate(Void... progress) {

        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (!result) {
                mState = STATE_UNLOADED;
                showView(ViewType.OFFLINE_VIEW);
                return;
            }
            removeLoadedView();// 移除上一次的View
            createNewLoadedView();
            addLoadeView();
            showLoadeView();
            mState = STATE_LOADED;
        }

        @Override
        protected boolean match(Object... params) {
            return false;
        }
    }

    protected abstract boolean load();

    public enum LoadingFrameState {
        STATE_UNLOADED, STATE_LOADING, STATE_LOADED
    }

}
