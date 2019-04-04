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
import com.yuepang.yuepang.control.GetTelCodeControl;
import com.yuepang.yuepang.presenter.ForgetPwdPresenter;

/**
 *
 * 忘记密码页面
 */

public class ForgetPwdActivity extends BaseActivity {

    @BindView(id = R.id.ed_tel)
    private EditText edTel;

    @BindView(id = R.id.ed_coed)
    private EditText code;

    @BindView(id = R.id.tv_getcoed)
    private TextView tvGetCode;

    @BindView(id = R.id.tv_pwd1)
    private EditText pwd1;

    @BindView(id = R.id.tv_pwd2)
    private EditText pwd2;

    @BindView(id = R.id.btn_sub,click = true)
    private Button btnSure;

    private ForgetPwdPresenter forgetPwdPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forgetPwdPresenter = new ForgetPwdPresenter(this);
    }

    @Override
    protected String getMyRTitle() {
        return null;
    }

    @Override
    protected String getMyTittle() {
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
