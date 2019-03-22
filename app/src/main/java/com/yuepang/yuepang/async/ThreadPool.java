package com.yuepang.yuepang.async;

import android.os.Process;

import com.yuepang.yuepang.Util.LogUtils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ThreadPool {
    // ==========================================================================
    // Constants
    // ==========================================================================
    public static final int PRIORITY_DEFAULT = 10;

    public static final int PRIORITY_LOWEST = Integer.MAX_VALUE;

    private static final int CORE_POOL_SIZE = 9;

    // ==========================================================================
    // Fields
    // ==========================================================================
    private static List<Workgroup> sWorkgroups = new LinkedList<Workgroup>();

    private static List<Workgroup> sTaskSharedWorkgroups = new LinkedList<Workgroup>();

    private static int sAllocatedPoolSize = 0;

    private static Integer sPoolSize = Integer.valueOf(0);

    private static int sPriorityThreshold = PRIORITY_LOWEST;

    private static Set<Integer> sPriorityLocks = new HashSet<Integer>(CORE_POOL_SIZE);

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
    public static boolean registerWorkgroup(Workgroup workgroup) {
        synchronized (sWorkgroups) {
            int maxWorkers = workgroup.maxWorkers();
            if (sAllocatedPoolSize + maxWorkers <= CORE_POOL_SIZE) {
                sAllocatedPoolSize += maxWorkers;
                if (!sWorkgroups.contains(workgroup)) {
                    sWorkgroups.add(workgroup);
                }
            } else {
                LogUtils.e("Register " + workgroup + " in " + ThreadPool.class + " failed, allocated "
                        + sAllocatedPoolSize + ", limit " + CORE_POOL_SIZE + ", request " + maxWorkers);
                return false;
            }
        }

        synchronized (sTaskSharedWorkgroups) {
            if (workgroup.isTaskShared() && !sTaskSharedWorkgroups.contains(workgroup)) {
                sTaskSharedWorkgroups.add(workgroup);
            }
        }
        return true;
    }

    public static boolean isOverloaded() {
        synchronized (sPoolSize) {
            return sPoolSize > CORE_POOL_SIZE;
        }
    }

    /**
     * @deprecated 貌似有问题
     * @param priority
     */
    private static void lockPriority(int priority) {
        synchronized (sPriorityLocks) {
            sPriorityLocks.add(priority);
            sPriorityThreshold = Math.min(sPriorityThreshold, priority);
        }
    }

    /**
     * @deprecated 貌似有问题
     * @param priority
     */
    private static void unlockPriority(int priority) {
        synchronized (sPriorityLocks) {
            sPriorityLocks.remove(priority);
            sPriorityThreshold = PRIORITY_LOWEST;
            for (Integer i : sPriorityLocks) {
                sPriorityThreshold = Math.min(sPriorityThreshold, i);
            }
        }
    }

    // ==========================================================================
    // Inner/Nested Classes
    // ==========================================================================
    public static abstract class Workgroup {

        private final WorkerThread[] mWorkers;

        private Integer mWorkersCount;

        private int mFirstEmptyIndex;

        public Workgroup() {
            mWorkers = new WorkerThread[maxWorkers() * 2];
            mWorkersCount = Integer.valueOf(0);
            mFirstEmptyIndex = 0;
        }

        public abstract int maxWorkers();

        public abstract boolean isTaskShared();

        protected abstract boolean enqueueTask(BackgroundTask<?> task);

        public abstract BackgroundTask<?> dequeueTask();

        public boolean isInQueue(Object id) {
            return false;
        }

        /**
         * 是否始终存活
         * 
         * @return
         */
        public boolean keepAlive() {
            return false;
        }

        /**
         * 该工作组的优先级。值越低代表优先级越高。较高优先级的工作组有权暂停/恢复较低优先级工作组的工作执行。
         * 
         * @return 代表优先级的整数值。注意：如果不是常量，可能会导致意想不到的后果。
         */
        public int getPriority() {
            return PRIORITY_DEFAULT;
        }

        public final boolean addTask(BackgroundTask<?> task) {
            boolean res = enqueueTask(task);
            if (res) {
                int handle = 0;
                synchronized (mWorkers) {
                    if (mWorkersCount < maxWorkers() && !isInQueue(task.mQueueId)) {
                        // 可以创建新的专职线程来加速执行
                        mWorkersCount++;
                        synchronized (sPoolSize) {
                            sPoolSize++;
                        }
                        handle = 1;
                        LogUtils.d("+ worker(" + sPoolSize + ") in " + Workgroup.this + "(" + mWorkersCount + ")");
                    } else if (isTaskShared()) {
                        synchronized (sPoolSize) {
                            if (sPoolSize < CORE_POOL_SIZE) {
                                // 专职线程数已达阈值，但线程池未满，可创建新的辅助线程来加速执行
                                sPoolSize++;
                                handle = 2;
                                LogUtils.d("+ worker(" + sPoolSize + ") in FREE");
                            }
                        }
                    }
                }
                switch (handle) {
                case 1:
                    createAndStartWorker();
                    break;

                case 2:
                    new WorkerThread(null, -1, getPriority()).start();
                    break;
                }
            }
            return res;
        }

        public void cancelExecutingTasks() {
            for (WorkerThread worker : mWorkers) {
                if (worker != null) {
                    final BackgroundTask<?> currentTask = worker.getCurrentTask();
                    if (null != currentTask) {
                        currentTask.cancel();
                    }
                }
            }
        }

        public boolean cancelExecutingTasks(boolean greedyMatch, Object... matchParams) {
            boolean res = false;
            for (WorkerThread worker : mWorkers) {
                if (worker != null) {
                    final BackgroundTask<?> currentTask = worker.getCurrentTask();
                    if (null != currentTask && currentTask.match(matchParams)) {
                        currentTask.cancel();
                        res = true;
                        if (!greedyMatch) {
                            break;
                        }
                    }
                }
            }
            return res;
        }

        public final void pauseLowerPriorityWork() {
            LogUtils.d("Pause " + getPriority() + ", " + this);
            lockPriority(getPriority());
        }

        public final void resumeLowerPriorityWork() {
            LogUtils.d("Resume " + getPriority() + ", " + this);
            unlockPriority(getPriority());
        }

        private boolean createAndStartWorker() {
            boolean ret = false;
            if (mFirstEmptyIndex < mWorkers.length) {
                ret = true;
                WorkerThread worker = new WorkerThread(Workgroup.this, mFirstEmptyIndex, getPriority());
                mWorkers[mFirstEmptyIndex] = worker;
                for (; ++mFirstEmptyIndex < mWorkers.length;) {
                    if (null == mWorkers[mFirstEmptyIndex]) {
                        break;
                    }
                }
                worker.start();
            }
            return ret;
        }

        private boolean removeWorker(int index) {
            synchronized (mWorkers) {
                if (index < mWorkers.length && null != mWorkers[index]) {
                    mWorkers[index] = null;
                    mFirstEmptyIndex = Math.min(mFirstEmptyIndex, index);
                    mWorkersCount--;
                    LogUtils.d("  worker " + index + " turn FREE");
                    return true;
                } else {
                    return false;
                }
            }
        }

    }

    private static class WorkerThread extends Thread {

        private Workgroup mWorkgroup;

        private final int mIndex;

        private BackgroundTask<?> mCurrentTask = null;

        private int mPriority;

        public WorkerThread(Workgroup workgroup, int index, int priority) {
            mWorkgroup = workgroup;
            mIndex = index;
            mPriority = priority;
        }

        @Override
        public void run() {
            // 默认以BACKGROUND优先级运行
            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
            boolean keepAlive = true;
            while (true) {
                boolean isAssitantWorker = false;
                if (null != mWorkgroup) {
                    if (mPriority > sPriorityThreshold) {
                        // 该工作组优先级低于线程池当前设定阈值
                        try {
                            sleep(500);
                        } catch (InterruptedException e) {
                            LogUtils.e(e);
                        }
                        // 暂不工作，稍后再试
                        continue;
                    }
                    // 该工作线程是专职线程，从其专职的任务队列中取任务
                    mCurrentTask = mWorkgroup.dequeueTask();
                    if (null == mCurrentTask) {
                        // 专职任务队列已空，线程自动转换身份为辅助线程，退出其所在工作组
                        mWorkgroup.removeWorker(mIndex);
                        mWorkgroup = null;
                        // 从共享任务队列中取任务
                        isAssitantWorker = true;
                    }
                } else {
                    // 该工作线程是一个辅助线程，从共享任务队列中取任务
                    isAssitantWorker = true;
                }

                if (isAssitantWorker) {
                    if (isOverloaded()) {
                        // 线程池超载，辅助线程自动死亡
                        break;
                    } else {
                        // 从共享任务队列中取任务
                        mCurrentTask = pollSharedTask();
                    }
                }

                if (null != mCurrentTask) {
                    keepAlive = true;
                    if (mCurrentTask.shouldCancel()) {
                        continue;
                    }
                    // 执行任务
                    mCurrentTask.execute();
                } else {
                    // 没有任何剩余任务可处理
                    if (keepAlive) {
                        // 为避免线程的频繁创建和销毁，我们等待一段时间后再检查一次任务队列
                        try {
                            sleep(500);
                        } catch (InterruptedException e) {
                            LogUtils.e(e);
                        }
                        keepAlive = mWorkgroup == null ? false : mWorkgroup.keepAlive();
                        continue;
                    } else {
                        // 等待一段时间后依然没有任何剩余任务可处理，自然死亡
                        break;
                    }
                }
            }
            // 线程结束，退出所在工作组，线程池大小减1
            synchronized (sPoolSize) {
                sPoolSize--;
                LogUtils.d("- worker(" + sPoolSize + ")");
            }
        }

        private BackgroundTask<?> pollSharedTask() {
            BackgroundTask<?> task = null;
            try {
                for (int i = 0; i < sTaskSharedWorkgroups.size(); i++) {
                    Workgroup workgroup = sTaskSharedWorkgroups.get(i);
                    if (workgroup.getPriority() > sPriorityThreshold) {
                        // 该工作组优先级低于线程池当前设定阈值，换一个工作组
                        continue;
                    }
                    task = workgroup.dequeueTask();
                    if (null != task) {
                        break;
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                LogUtils.e(e);
            }
            return task;
        }

        public BackgroundTask<?> getCurrentTask() {
            return mCurrentTask;
        }

    }

}
