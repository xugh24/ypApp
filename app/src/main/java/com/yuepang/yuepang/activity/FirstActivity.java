package com.yuepang.yuepang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.android.common.annotation.view.BindViewByTag;
import com.android.common.annotation.view.OnClickView;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.control.UserCentreControl;
import com.yuepang.yuepang.interFace.LoginState;

/**
 * <p>
 * Completed
 * 首页 包含登录和注册按钮
 */

public class FirstActivity extends BaseActivity implements LoginState {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserCentreControl.getInstance().addLoginMonitor(this);//设置登录成功的监听
    }

    @Override
    public String getMyTittle() {
        return null;
    }

    @OnClickView({R.id.btn_2login, R.id.btn_2register})
    private String click;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_2login:
                startActivity(LoginActivity.class);
                break;
            case R.id.btn_2register:
                startActivity(RegisterActivity.class);
                break;
        }
    }

    @Override
    protected boolean isShowBar() {
        return false;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.first_ly;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserCentreControl.getInstance().relieveLoginMonitor(this);
    }

    /**
     * 登录成功监听回调
     */
    @Override
    public void loginSuccess() {
        finish();
    }

    @Override
    public void loginFailed() {

    }
}
