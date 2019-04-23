package com.android.common.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Created by xugh on 2019/4/15.
 */

public class BaseFragmentActivity extends FragmentActivity {

    public ActivityManage manage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manage = new ActivityManage(this);
        manage.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        manage.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manage.onDestroy();
    }
}
