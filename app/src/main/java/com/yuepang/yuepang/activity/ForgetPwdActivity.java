package com.yuepang.yuepang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.common.annotation.view.BindViewByTag;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.presenter.ForgetPwdPresenter;

/**
 * 忘记密码页面
 */

public class ForgetPwdActivity extends BaseActivity {

    @BindViewByTag
    private EditText edTel;// 手机号输入框
    @BindViewByTag
    private EditText code;// 验证码输入框
    @BindViewByTag
    private TextView tvGetCode;// 获得验证码点击
    @BindViewByTag
    private EditText pwd1;// 密码框1
    @BindViewByTag
    private EditText pwd2;// 密码框2
    @BindViewByTag
    private Button btnSure;//

    private ForgetPwdPresenter forgetPwdPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forgetPwdPresenter = new ForgetPwdPresenter(this);
    }

    @Override
    public String getMyTittle() {
        return "忘记密码";
    }

    /**
     * 获得布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.forgetpwd_ly;
    }

    @Override
    public void onClick(View v) {
        if (v == btnSure) {
            forgetPwdPresenter.resetPwd();
        }
    }

    public EditText getEdTel() {
        return edTel;
    }

    public EditText getCode() {
        return code;
    }

    public TextView getTvGetCode() {
        return tvGetCode;
    }

    public String getPwd1() {// 获得密码1
        return getEditText(pwd1);
    }

    public String getPwd2() {// 获得密码2
        return getEditText(pwd2);
    }
}
