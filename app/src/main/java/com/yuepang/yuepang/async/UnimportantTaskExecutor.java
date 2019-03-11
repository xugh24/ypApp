/*
 * File Name: InconsequentiaExecutor.java 
 * History:
 * Created by Siyang.Miao on 2012-5-25
 */
package com.yuepang.yuepang.async;

import android.os.Process;

import com.yuepang.yuepang.Util.LogUtils;

import java.util.LinkedList;

public class UnimportantTaskExecutor extends ThreadPool.Workgroup {
    // ==========================================================================
    // Constants
    // ==========================================================================

    // ==========================================================================
    // Fields
    // ==========================================================================
    private static UnimportantTaskExecutor sInstance;

    private LinkedList<BackgroundTask<?>> mTaskQueue;

    // ==========================================================================
    // Constructors
    // ==========================================================================
    private UnimportantTaskExecutor() {
        mTaskQueue = new LinkedList<BackgroundTask<?>>();
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
    private static synchronized UnimportantTaskExecutor getInstance() {
        if (null == sInstance) {
            sInstance = new UnimportantTaskExecutor();
        }
        return sInstance;
    }

    public static synchronized void execute(final Runnable r) {
        execute(r, null);
    }

    public static synchronized void execute(final Runnable r, final AsyncCallback cb) {
        getInstance().addTask(new BackgroundTask<Void>() {

            @Override
            protected Void doInBackground() {
                Process.setThreadPriority(Process.THREAD_PRIORITY_LOWEST);
                r.run();
                return null;
            }

            @Override
            protected void onDoneInBackground(Void result) {
                if (null != cb) {
                    cb.onDoneInBackground();
                }
            }

            @Override
            protected boolean match(Object... params) {
                return false;
            }

        });
    }

    public static synchronized void execute(final Runnable r, final AsyncCallback cb, final long delayms) {
        getInstance().addTask(new BackgroundTask<Void>() {

            @Override
            protected Void doInBackground() {
                Process.setThreadPriority(Process.THREAD_PRIORITY_LOWEST);
                r.run();
                return null;
            }

            @Override
            protected void onDoneInBackground(Void result) {
                if (null != cb) {
                    try {
                        Thread.sleep(delayms);
                    } catch (InterruptedException e) {
                        LogUtils.e(e);
                    }
                    cb.onDoneInBackground();
                }
            }

            @Override
            protected boolean match(Object... params) {
                return false;
            }

        });
    }

    @Override
    public int maxWorkers() {
        return 0;
    }

    @Override
    public boolean isTaskShared() {
        return true;
    }

    @Override
    protected boolean enqueueTask(BackgroundTask<?> task) {
        synchronized (mTaskQueue) {
            return mTaskQueue.add(task);
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
