package com.yuepang.yuepang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.presenter.ForgetPwdPresenter;

/**
 *
 * 忘记密码页面
 */

public class ForgetPwdActivity extends BaseActivity {

    private EditText edTel;

    private EditText code;

    private TextView tvGetCode;

    private EditText pwd1;

    private EditText pwd2;

    private Button btnSure;

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

    @Override
    protected int getContentViewId() {
        return R.layout.forgetpwd_ly;
    }



    @Override
    public void onClick(View v) {
        super.onClick(v);
        if(v==btnSure){
            forgetPwdPresenter.resetPwd();
        }
    }

    public EditText getEdTel() {
        return edTel;
    }

    public EditText getCode() {
        return code;
    }

    public TextView getTvGetCode() { return tvGetCode; }

    public String getPwd1() {
        return pwd1.getText().toString();
    }

    public String getPwd2() {
        return pwd1.getText().toString();
    }

}
