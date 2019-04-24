/*
 */
package com.android.common.async;
import java.util.LinkedList;

public class AsyncContentLoader extends ThreadPool.Workgroup {
    // ==========================================================================
    // Constants
    // ==========================================================================

    // ==========================================================================
    // Fields
    // ==========================================================================
    private static AsyncContentLoader sInstance;

    private LinkedList<UIBackgroundTask<?, ?, ?>> mTaskQueue;

    // ==========================================================================
    // Constructors
    // ==========================================================================
    private AsyncContentLoader() {
        mTaskQueue = new LinkedList<UIBackgroundTask<?, ?, ?>>();
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
    public static synchronized AsyncContentLoader getInstance() {
        if (null == sInstance) {
            sInstance = new AsyncContentLoader();
        }
        return sInstance;
    }

    @Override
    public int maxWorkers() {
        return 1;
    }

    @Override
    public boolean isTaskShared() {
        return true;
    }

    @Override
    protected boolean enqueueTask(BackgroundTask<?> task) {
        if (task instanceof UIBackgroundTask) {
            synchronized (mTaskQueue) {
                return mTaskQueue.add((UIBackgroundTask<?, ?, ?>) task);
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

    // ==========================================================================
    // Inner/Nested Classes
    // ==========================================================================

}
