package com.yuepang.yuepang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.android.common.annotation.view.OnClickView;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.presenter.LoginPresenter;

/**
 * 登录页面
 * 登录注册功能实现MVP模式
 * activity 做为V 实现页面展示，提供页面数据获得接口
 */

public class LoginActivity extends BaseActivity {

    @BindView(id = R.id.ed_login_tel)
    private EditText edTel; // 用户手机号

    @BindView(id = R.id.ed_login_pwd)
    private EditText edPwd;// 用户密码

    @BindView(id = R.id.btn_reg, click = true)
    private Button btnReg;// 注册按钮

    private LoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginPresenter(this);
        edTel.setText(presenter.getLoginName());
        edPwd.setText(presenter.getPwd());
    }

    @Override
    public String getMyTittle() {
        return "登录";
    }

    @OnClickView({R.id.btn_login,R.id.btn_reg,R.id.forget_pwd})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:// 登录按钮
                presenter.login();
                break;
            case R.id.btn_reg:// 跳转注册页面
                presenter.toRegPage();
                break;
            case R.id.forget_pwd://跳转忘记密码页面
                presenter.toForgetPage();
                break;
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.login_ly;
    }

    /**
     * 获得用户名
     */
    public String getLoginName() {
        if (edTel != null) {
            return edTel.getText().toString().trim();
        }
        return null;
    }

    /**
     * 获得用户名
     */
    public String getPwd() {
        if (edTel != null) {
            return edPwd.getText().toString().trim();
        }
        return null;
    }
}
