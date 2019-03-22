package com.yuepang.yuepang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.async.CommonTaskExecutor;

/**
 * Created by xugh on 2019/3/1.
 *
 * 闪屏页面
 */

public class SplshActivity extends BaseActivity {

    private final int TIME = 2 * 1000; // 首页展示时间 默认2s ， 可修改

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(TIME);// 延时
                    startActivity(FirstActivity.class);// 打开登录页面
                    finish(); // 关闭页面
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected String getMyRTitle() {
        return null;
    }

    @Override
    protected String getMyTittle() {
        return null;
    }

    @Override
    protected boolean isShowBar() {
        return false;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.splsh_ly;
    }
}
