package com.android.common.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.Toast;
import com.android.common.inter.FinishCallBack;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xugh on 2019/4/15.
 */


public class ActivityManage {

    public Activity activity;

    private FinishCallBack finishCallBack;

    private String finishTag;

    private int mThreadId;// 主线程编号

    private static List<Activity> activities = new ArrayList<>();

    public ActivityManage(Activity activity) {
        this.activity = activity;
    }


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        addActivty(activity);
        mThreadId = android.os.Process.myTid();
    }

    protected void onResume() {

    }

    public Context getApplicationContext(){
        return activity.getApplicationContext();
    }

    protected void onDestroy() {
        removeActivity(activity);
    }

    public static boolean addActivty(Activity activity) {
        if (activities != null) {
            return activities.add(activity);
        }
        return false;
    }


    /**
     * 移除当前acticity
     */
    public static boolean removeActivity(Activity activity) {
        if (activities != null) {
            return activities.remove(activity);
        }
        return false;
    }

    /**
     * toast 提示
     */
    public void showToast(int resId, final int duration) {
        showToast(activity.getString(resId), duration);
    }

    /**
     * toast 提示,默认为短提示
     */
    public void showToast(int resId) {
        showToast(activity.getString(resId), Toast.LENGTH_SHORT);
    }

    public void showToast(final String string, final int duration) {
        post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, string, duration).show();
            }
        });
    }

    /**
     * toast 提示,默认为短提示
     */
    public void showToast(String string) {
        showToast(string, Toast.LENGTH_SHORT);
    }

    /**
     * 关闭所有的activity
     */
    public static void finishAll() {
        List<Activity> list = new ArrayList<>(activities);
        for (Activity activity : list) {
            activity.finish();
        }
        activities.clear();
    }

    /**
     * 关闭所有的activity，排除掉每一种类型的activity
     */
    public static void finishAll(Class c){
        List<Activity> list = new ArrayList<>(activities);
        for (Activity activity : list) {
            if(!c.getName().equals(activity.getClass().getName())){
                activity.finish();
            }
        }
    }
    /**
     * 关闭所有的activity，排除掉某一个
     */
    public static void finishAll(Activity expactivity){
        List<Activity> list = new ArrayList<>(activities);
        for (Activity activity : list) {
            if(activity != expactivity){
                activity.finish();
            }
        }
    }


    public void post(Runnable r) {
        mHandler.post(r);
    }

    Handler mHandler = new Handler() {
    };

    public void setFinishCallBack(FinishCallBack finishCallBack) {
        this.finishCallBack = finishCallBack;
    }

    public void finish() {
        if (finishCallBack != null) {
            finishCallBack.finishCallBack(getFinishTag());
        }
    }

    public String getFinishTag() {
        return finishTag;
    }

    public void setFinishTag(String finishTag) {
        this.finishTag = finishTag;
    }
}
