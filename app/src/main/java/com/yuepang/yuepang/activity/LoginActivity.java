package com.yuepang.yuepang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.control.CheckManage;
import com.yuepang.yuepang.control.DataControl;
import com.yuepang.yuepang.control.LoginControl;

/**
 * Created by xugh on 2019/3/1.
 */

public class LoginActivity extends BaseActivity {


    @BindView(id = R.id.ed_login_tel)
    private EditText edTel; // 用户手机号

    @BindView(id = R.id.ed_login_pwd)
    private EditText edPwd;// 用户密码

    @BindView(id = R.id.btn_login, click = true)
    private Button btnLogin;// 登录按钮

    @BindView(id = R.id.tv_register, click = true)
    private TextView tvRegister;// 注册文字

    @BindView(id = R.id.forget_pwd)
    private TextView tvforgetPwd; // 忘记密码文字

    private String loginName;

    private String pwd;

    private LoginControl loginControl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginName = DataControl.getInstance(this).getLoginName();
        pwd = DataControl.getInstance(this).getPwd();
        loginControl = new LoginControl(this);
        if (!TextUtils.isEmpty(loginName)) {
            edTel.setText(loginName);
        }
        if (!TextUtils.isEmpty(pwd)) {
            edPwd.setText(pwd);
        }
    }


    @Override
    protected String getMyRTitle() {
        return null;
    }

    @Override
    protected String getMyTittle() {
        return "登录";
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_login:// 登录按钮
                login();
                break;
            case R.id.tv_register:// 跳转注册页面
                startActivity(RegisterActivity.class);
                break;
            case R.id.forget_pwd://跳转忘记密码页面
                startActivity(ForgetPwdActivity.class);
                break;
        }
    }

    private void login() {
        String loginName = edTel.getText().toString().trim();
        String pwd = edPwd.getText().toString().trim();
        if (CheckManage.checklogin(loginName, pwd, this)) {
            loginControl.loginByPwd(loginName,pwd);
        }

    }

    @Override
    protected int getContentViewId() {
        return R.layout.login_ly;
    }
}
