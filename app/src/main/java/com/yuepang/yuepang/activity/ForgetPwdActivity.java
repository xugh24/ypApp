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

/**
 * Created by xugh on 2019/3/4.
 */

public class ForgetPwdActivity extends BaseActivity {

    @BindView(id = R.id.ed_tel)
    private EditText edTel;

    @BindView(id = R.id.ed_coed)
    private EditText code;

    @BindView(id = R.id.tv_getcoed, click = true)
    private TextView tvGetCode;

    @BindView(id = R.id.tv_pwd1)
    private EditText pwd1;

    @BindView(id = R.id.tv_pwd2)
    private EditText pwd2;

    @BindView(id = R.id.btn_sub)
    private Button btnSure;

    private GetTelCodeControl control;


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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        control = new GetTelCodeControl(this, edTel, code, tvGetCode, 1);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_getcoed:
                control.getTelCode();
                break;
            case R.id.btn_sub:
                String tel = edTel.getText().toString();
                String pw1 = pwd1.getText().toString();
                String pw2 = pwd2.getText().toString();
                if (TextUtils.isEmpty(tel)) {
                    showToastSafe("手机号为空");
                    return;
                }
                resetPwd(tel, pw1, pw2);
                break;
        }
    }

    private void resetPwd(String pw1, String pw2, String pw3) {


    }
}
