package com.yuepang.yuepang.async;

import android.os.Process;
import java.util.LinkedList;

public class CommonTaskExecutor extends ThreadPool.Workgroup {
    // ==========================================================================
    // Constants
    // ==========================================================================

    // ==========================================================================
    // Fields
    // ==========================================================================
    private static CommonTaskExecutor sInstance;

    private LinkedList<CommonTask> mTaskQueue;

    // ==========================================================================
    // Constructors
    // ==========================================================================
    private CommonTaskExecutor() {
        mTaskQueue = new LinkedList<CommonTask>();
        ThreadPool.registerWorkgroup(this);
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
    private static synchronized CommonTaskExecutor getInstance() {
        if (null == sInstance) {
            sInstance = new CommonTaskExecutor();
        }
        return sInstance;
    }

    public static synchronized void execute(final Runnable r) {
        execute(r, null);
    }

    public static synchronized void execute(final Runnable r, final AsyncCallback cb) {
        getInstance().addTask(new CommonTask(r, cb));
    }

    @Override
    public int maxWorkers() {
        return 5;
    }

    @Override
    public boolean isTaskShared() {
        return true;
    }

    @Override
    protected boolean enqueueTask(BackgroundTask<?> task) {
        if (task instanceof CommonTask) {
            synchronized (mTaskQueue) {
                return mTaskQueue.add((CommonTask) task);
            }
        } else {
            return false;
        }
    }

    @Override
    public BackgroundTask<?> dequeueTask() {
        synchronized (mTaskQueue) {
            return mTaskQueue.poll();
        }
    }

    public static synchronized boolean cancel(Runnable r) {
        return getInstance().cancelInner(r);
    }

    private boolean cancelInner(Runnable r) {
        synchronized (mTaskQueue) {
            // Task is not currently executed, remove it from task queue
            if (mTaskQueue == null || mTaskQueue.size() == 0) {
                return false;
            }
            for (CommonTask task : mTaskQueue) {
                if (task.match(r)) {
                    mTaskQueue.remove(task);
                    return true;
                }
            }

            return cancelExecutingTasks(false, r);
        }
    }

    // ==========================================================================
    // Inner/Nested Classes
    // ==========================================================================
    // public static enum Priority {
    // NORMAL,
    // IMPORTANT,
    // UNIMPORTANT,
    // @Deprecated
    // INSTANT
    // }
    //

    public static class CommonTask extends BackgroundTask<Void> {

        private Runnable mRunnable;

        private AsyncCallback mCb;

        public CommonTask(Runnable r) {
            this(r, null);
        }

        public CommonTask(Runnable r, AsyncCallback cb) {
            mRunnable = r;
            mCb = cb;
        }

        @Override
        protected Void doInBackground() {
            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
            mRunnable.run();
            return null;
        }

        @Override
        protected void onDoneInBackground(Void result) {
            if (null != mCb) {
                mCb.onDoneInBackground();
            }
        }

        @Override
        protected boolean match(Object... params) {
            return params != null && params.length > 0 && (params[0] instanceof Runnable)
                    && (params[0] == mRunnable);
        }

    }

}
