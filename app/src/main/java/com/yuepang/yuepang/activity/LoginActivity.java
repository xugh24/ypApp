package com.yuepang.yuepang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.android.common.annotation.view.BindView;
import com.android.common.annotation.view.OnClickView;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.presenter.LoginPresenter;

/**
 * 登录页面
 * 登录注册功能实现MVP模式
 * activity 做为V 实现页面展示，提供页面数据获得接口
 */

public class LoginActivity extends BaseActivity {

    @BindView(id = R.id.ed_login_tel)
    private EditText edTel; // 用户手机号输入框

    @BindView(id = R.id.ed_login_pwd)
    private EditText edPwd;// 用户密码

    @OnClickView({R.id.btn_login, R.id.btn_reg, R.id.forget_pwd})// 注解绑定需要事件的控件Id
    private String string;

    private LoginPresenter presenter;// 登录presenter实现数据和页面的交互

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginPresenter(this);// 初始化
        edTel.setText(getDataControl().getLoginName());//设置本地记录的账号
        edPwd.setText(getDataControl().getPwd());// 设置本地密码
    }

    @Override
    public String getMyTittle() {
        return "登录";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:// 登录按钮
                presenter.login();
                break;
            case R.id.btn_reg:// 跳转注册页面
                startActivity(RegisterActivity.class);
                break;
            case R.id.forget_pwd://跳转忘记密码页面
                startActivity(ForgetPwdActivity.class);
                break;
        }
    }

    @Override
    protected int getContentViewId() {// 获得页面页面的id
        return R.layout.login_ly;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    /**
     * 获得输入框用户名
     */
    public String getLoginName() {
        return getEditText(edTel);
    }

    /**
     * 获得密码输入框内容
     */
    public String getPwd() {
        return getEditText(edPwd);
    }
}
