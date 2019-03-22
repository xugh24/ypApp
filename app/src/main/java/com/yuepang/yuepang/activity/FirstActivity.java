package com.yuepang.yuepang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.control.UserCentreControl;
import com.yuepang.yuepang.interFace.LoginSuccess;

/**
 * Created by xugh on 2019/3/1.
 * <p>
 * 首页 包含登录和注册按钮
 */

public class FirstActivity extends BaseActivity implements LoginSuccess {


    /**
     * 登录按钮
     */
    @BindView(id = R.id.btn_2login, click = true)
    private Button btnLogin;

    /**
     * 注册按钮
     */
    @BindView(id = R.id.btn_2register, click = true)
    private Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserCentreControl.getInstance().addLoginMonitor(this);
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
    public void onClick(View v) {
        super.onClick(v);
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

    @Override
    public void loginSuccess() {
        finish();
    }
}
