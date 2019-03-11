/*
 * File Name: BackgroundTask.java 
 * History:
 * Created by Siyang.Miao on 2012-2-20
 */
package com.yuepang.yuepang.async;

import com.yuepang.yuepang.Util.LogUtils;

public abstract class BackgroundTask<Result> {
    // ==========================================================================
    // Constants
    // ==========================================================================

    // ==========================================================================
    // Fields
    // ==========================================================================
    public volatile Status mStatus = Status.PENDING;

    public Object mQueueId;
    // ==========================================================================
    // Constructors
    // ==========================================================================

    // ==========================================================================
    // Getters
    // ==========================================================================

    // ==========================================================================
    // Setters
    // ==========================================================================

    // ==========================================================================
    // Methods
    // ==========================================================================
    public void cancel() {
        mStatus = Status.CANCELLED;
    }

    public boolean shouldCancel() {
        return mStatus == Status.CANCELLED;
    }

    public boolean execute() {
        boolean res = false;
        switch (mStatus) {
        case RUNNING:
            LogUtils.e("Cannot execute task: the task is already running.");
            break;

        case FINISHED:
            LogUtils.e("Cannot execute task: the task has already been executed "
                    + "(a task can be executed only once)");
            break;

        case PENDING:
            mStatus = Status.RUNNING;
            Result result = doInBackground();
            if (Status.CANCELLED != mStatus) {
                onDoneInBackground(result);
                mStatus = Status.FINISHED;
            } else {
                onCancelledInBackground();
            }
            res = true;
            break;

        case CANCELLED:
            onCancelledInBackground();
            break;

        default:
            break;
        }
        return res;
    }

    protected abstract Result doInBackground();

    protected abstract void onDoneInBackground(Result result);

    protected void onCancelledInBackground() {
    }

    protected abstract boolean match(Object... params);

    // ==========================================================================
    // Inner/Nested Classes
    // ==========================================================================
    /**
     * Indicates the current status of the task. Each status will be set only once during the lifetime of a task.
     */
    public enum Status {
        /**
         * Indicates that the task has not been executed yet.
         */
        PENDING,
        /**
         * Indicates that the task is running.
         */
        RUNNING,
        /**
         * Indicates that the task has finished.
         */
        FINISHED,
        /**
         * Indicates that the task is canceled.
         */
        CANCELLED,
    }

}
