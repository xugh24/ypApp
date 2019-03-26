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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.AnnotateUtil;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.widget.SDKLoadingDialog;

import java.util.ArrayList;
import java.util.List;


/**
 * Android 基类
 */

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    private RelativeLayout rlBar;// 顶部控制bar

    private ImageView ivBack; // 返回按钮

    private TextView tvTitle; // 中间的title

    private TextView tvRtitle; // 右边的文字说明

    private TextView tvLeftTitle;// 左边的文字说明

    private ImageView ivStar;// 收藏星星页面使用

    private LinearLayout llMain; // 主View

    protected View contentView;

    public static List<Activity> activities = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activities.add(this);
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
        rlBar.setVisibility(isShowBar() ? View.VISIBLE : View.GONE);
        ivBack.setVisibility(isShowBack() ? View.VISIBLE : View.GONE);
        if (getContentViewId() != -1) {
            contentView = View.inflate(this, getContentViewId(), null);
            AnnotateUtil.initBindView(this, contentView);// 绑定子类View
            llMain.addView(contentView, -1, -1);
        }
    }

    /**
     * 绑定UI
     */
    private void bindView() {
        rlBar = findViewById(R.id.rl_title);
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
        tvTitle = findViewById(R.id.tv_title);
        tvRtitle = findViewById(R.id.tv_r_title);
        tvRtitle.setOnClickListener(this);
        llMain = findViewById(R.id.main_ly);
        ivStar = findViewById(R.id.iv_star);
        ivStar.setOnClickListener(this);
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
        } else {
            tvRtitle.setVisibility(View.GONE);
        }
    }

    protected boolean isShowBack() {
        return true;
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

            case R.id.iv_star:

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

    SDKLoadingDialog mLoadingDialog;

    public synchronized void showLoadingDialogSafe(final boolean setCancelable) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dismissLoadingDialogSafe();
                mLoadingDialog = new SDKLoadingDialog(BaseActivity.this);
                mLoadingDialog.setCancelable(setCancelable);
                if (!isFinishing()) {
                    mLoadingDialog.show();
                }
            }
        });
    }

    public synchronized void dismissLoadingDialogSafe() {
        if (null == mLoadingDialog) {
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (null != mLoadingDialog) {
                    mLoadingDialog.dismiss();
                    mLoadingDialog = null;
                }
            }
        });
    }


    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activities.remove(this);
        dismissLoadingDialogSafe();
    }
}
