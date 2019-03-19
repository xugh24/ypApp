package com.yuepang.yuepang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.AnnotateUtil;
import com.yuepang.yuepang.Util.LogUtils;


/**
 * Created by xugh on 2019/3/1.
 * <p>
 * Android 基类
 */

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    private LinearLayout llBar;// 顶部控制bar

    private ImageView ivBack; // 返回按钮

    private TextView tvTitle; // 中间的title

    private TextView tvRtitle; // 右边的文字说明

    private LinearLayout llMain; // 主View

    protected View contentView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        LogUtils.e("start activity = " + getClass().getName());
        LogUtils.e("测试同步");
        setContentView(R.layout.actionbar_ly);
        bindView();
        if (TextUtils.isEmpty(getMyTittle())) {
            tvTitle.setVisibility(View.INVISIBLE);
        } else {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(getMyTittle());
        }
        if (TextUtils.isEmpty(getMyRTitle())) {
            tvRtitle.setVisibility(View.INVISIBLE);
        } else {
            tvRtitle.setVisibility(View.VISIBLE);
            tvRtitle.setText(getMyRTitle());
        }
        if (isShowBar()) {
            llBar.setVisibility(View.VISIBLE);
        } else {
            llBar.setVisibility(View.GONE);
        }
        if (getContentViewId() != -1) {
            contentView = View.inflate(this, getContentViewId(), null);
            AnnotateUtil.initBindView(this, contentView);// 绑定子类View
            llMain.addView(contentView, -1, -1);
        }
    }

    /**
     *
     */
    private void bindView() {
        llBar = findViewById(R.id.title_ll);
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
        tvTitle = findViewById(R.id.tv_title);
        tvRtitle = findViewById(R.id.tv_r_title);
        tvRtitle.setOnClickListener(this);
        llMain = findViewById(R.id.main_ly);
    }

    public void setTitle(String title) {
        if (tvTitle != null) {
            tvTitle.setText(title);
            tvTitle.setVisibility(View.VISIBLE);
        }
    }

    public void setRightTitle(String title) {
        if (tvRtitle != null) {
            tvRtitle.setText(title);
            tvRtitle.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 是否展示bar
     */
    protected boolean isShowBar() {
        return true;
    }

    /**
     * 获得右侧文字
     */
    protected abstract String getMyRTitle();

    /**
     * 获得中间文字
     */
    protected abstract String getMyTittle();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_title:

                break;
            case R.id.tv_r_title:
                LogUtils.e("---");
                clikRt();
                break;
        }
    }

    public void clikRt() {
    }

    /**
     * 页面主View的id地址
     */
    protected abstract int getContentViewId();

    /**
     * 启动目Activity
     */
    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    public void showToastSafe(final CharSequence var1) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseActivity.this, var1, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
