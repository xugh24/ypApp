package com.android.common.async;

import android.os.Handler;
import android.os.Message;

import com.android.common.utils.LogUtils;


public abstract class UIBackgroundTask<Params, Progress, Result> extends BackgroundTask<Result> {
    // ==========================================================================
    // Constants
    // ==========================================================================

    // ==========================================================================
    // Fields
    // ==========================================================================
    private static InternalHandler sHandler = new InternalHandler();

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
    @Override
    protected  void onDoneInBackground(Result result) {
        sHandler.obtainMessage(InternalHandler.MESSAGE_POST_RESULT, new BackgroundTaskResult(this, result))
                .sendToTarget();
    }

    @Override
    protected final void onCancelledInBackground() {



    }

    private void finish(Result result) {
        if (Status.CANCELLED != mStatus) {
            onPostExecute(result);
            mStatus = Status.FINISHED;
        } else {
            onCancelled();
        }
    }

    public final void publishProgress(Progress... progress) {
        if (Status.RUNNING == mStatus) {
            sHandler.obtainMessage(InternalHandler.MESSAGE_POST_PROGRESS, new BackgroundTaskResult(this, progress))
                    .sendToTarget();
        } else {
            LogUtils.e("Cannot publish progress when the task is in " + mStatus.name() + " status!");
        }
    }

    protected abstract void onProgressUpdate(Progress... progress);

    protected abstract void onPostExecute(Result result);

    protected void onCancelled() {
    }

    protected abstract boolean match(Object... params);

    // ==========================================================================
    // Inner/Nested Classes
    // ==========================================================================
    private static class InternalHandler extends Handler {

        public static final int MESSAGE_POST_RESULT = 0x01;
        public static final int MESSAGE_POST_PROGRESS = 0x02;
        public static final int MESSAGE_POST_CANCEL = 0x03;

        @SuppressWarnings("unchecked")
        @Override
        public void handleMessage(Message msg) {
            BackgroundTaskResult result = (BackgroundTaskResult) msg.obj;
            switch (msg.what) {
            case MESSAGE_POST_RESULT:
                // There is only one result
                result.mTask.finish(result.mData[0]);
                break;
            case MESSAGE_POST_PROGRESS:
                result.mTask.onProgressUpdate(result.mData);
                break;
            case MESSAGE_POST_CANCEL:
                result.mTask.onCancelled();
                break;
            }
        }
    }

    @SuppressWarnings("rawtypes")
    private static class BackgroundTaskResult {
        final UIBackgroundTask mTask;
        final Object[] mData;

        BackgroundTaskResult(UIBackgroundTask task, Object... data) {
            mTask = task;
            mData = data;
        }
    }

}
