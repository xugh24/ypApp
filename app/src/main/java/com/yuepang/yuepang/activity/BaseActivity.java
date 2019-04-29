package com.yuepang.yuepang.activity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.common.activity.BaseFragmentActivity;
import com.android.common.annotation.view.AnnotateBindViewUtil;
import com.android.common.utils.LogUtils;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.control.UserCentreControl;
import com.yuepang.yuepang.dialog.CallDialog;
import com.yuepang.yuepang.dialog.MapDialog;
import com.yuepang.yuepang.location.LatLng;
import com.yuepang.yuepang.model.MerchantInfo;
import com.yuepang.yuepang.model.UserInfo;
import com.yuepang.yuepang.ui.ActionBarTitle;
import com.yuepang.yuepang.widget.SDKLoadingDialog;


/**
 * Android 基类
 */

public abstract class BaseActivity extends BaseFragmentActivity implements View.OnClickListener {

    private RelativeLayout rlBar;// 顶部控制bar

    private LinearLayout llMain; // 主View

    protected View contentView;

    private ActionBarTitle barTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        LogUtils.e("start activity = " + getClass().getName());
        setContentView(R.layout.actionbar_ly);// 设置框架View
        rlBar = findViewById(R.id.rl_title);// 初始化titleView的父view
        llMain = findViewById(R.id.main_ly);// 初始 面view的主View；
        initBarView();//
        initbefore();
        initContentView();
    }

    /**
     * 初始化前数据view前
     */
    protected void initbefore() {

    }

    private void initBarView() {
        barTitle = new ActionBarTitle(this);
        rlBar.setVisibility(isShowBar() ? View.VISIBLE : View.GONE);
        rlBar.addView(barTitle.getBarView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        barTitle.init();
    }

    private void initContentView() {
        if (getContentViewId() != -1) {
            contentView = View.inflate(this, getContentViewId(), null);
            AnnotateBindViewUtil.initBindView(this, contentView, this);
        } else {
            if (getContentView() != null) {
                contentView = getContentView();
            }
        }
        llMain.addView(contentView, -1, -1);
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
    public String getMyRTitle() {
        return null;
    }

    /**
     * 获得标题栏中间的文字
     */
    public abstract String getMyTittle();

    public String getLeftTitle() {
        return null;
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 点击左边文字
     */
    public void clickLeftTv() {
    }

    /**
     * 点击右边文字
     */
    public void clickRt() {

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
        if (!TextUtils.isEmpty(var1)) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(BaseActivity.this, var1, Toast.LENGTH_SHORT).show();
                }
            });
        }

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

    public void showCallDialog(String tel) {
        new CallDialog(this, tel).show();
    }

    public void showMapDialog(MerchantInfo info) {
        LatLng latLng = new LatLng(info.getLatitude(), info.getLongitude());
        new MapDialog(this, latLng, "西小口").show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissLoadingDialogSafe();
    }

    public UserInfo getUserInfo() {
        return UserCentreControl.getInstance().getInfo();
    }

    public View getContentView() {
        return null;
    }

    public ActionBarTitle getBarTitle() {
        return barTitle;
    }
}
