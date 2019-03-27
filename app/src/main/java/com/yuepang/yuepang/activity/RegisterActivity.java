package com.yuepang.yuepang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.control.GetTelCodeControl;
import com.yuepang.yuepang.presenter.RegPresenter;

/**
 * 注册页面
 */

public class RegisterActivity extends BaseActivity {

    @BindView(id = R.id.tv_rgtel)
    private EditText edtel;// 注册手机号输入框

    @BindView(id = R.id.tv_rgtel)
    private EditText edCode;// 验证码输框

    @BindView(id = R.id.ed_regpwd)
    private EditText edPwd;// 密码输入框

    @BindView(id = R.id.tv_getrgcoed)
    private TextView tvGetCode; // 获得验证码输入框


    @BindView(id = R.id.btn_reg, click = true)
    private Button btnReg;// 点击注册按钮

    private RegPresenter regPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        regPresenter = new RegPresenter(this);

    }

    @Override
    protected String getMyRTitle() {
        return null;
    }

    @Override
    protected String getMyTittle() {
        return "注册";
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == btnReg) {
            regPresenter.register();
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.register_ly;
    }

    public EditText getEdtel() {
        return edtel;
    }

    public EditText getEdCode() {
        return edCode;
    }

    public TextView getTvGetCode() {
        return tvGetCode;
    }

    public EditText getEdPwd() {
        return edPwd;
    }

    public String getTel() {
        return edtel.getText().toString();
    }

    public String getPwd() {
        return edPwd.getText().toString();
    }

    public String getcode() {
        return edCode.getText().toString();
    }
}
