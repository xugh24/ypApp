package com.yuepang.yuepang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.common.annotation.view.AnnotateBindViewUtil;
import com.android.common.annotation.view.BindView;
import com.android.common.annotation.view.BindViewByTag;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.presenter.RegPresenter;

/**
 * 注册页面
 */

public class RegisterActivity extends BaseActivity {

    @BindViewByTag
    private EditText edtel;// 注册手机号输入框
    @BindViewByTag
    private EditText edCode;// 验证码输框
    @BindViewByTag
    private EditText edPwd;// 密码输入框
    @BindViewByTag
    private TextView tvGetCode; // 获得验证码输入框

    @BindViewByTag(click = true)
    private Button btnReg;// 点击注册按钮

    private RegPresenter regPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        regPresenter = new RegPresenter(this);
    }


    @Override
    public String getMyTittle() {
        return "注册";
    }

    @Override
    public void onClick(View v) {
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

    public String getPwd() {
        return edPwd.getText().toString();
    }
}
