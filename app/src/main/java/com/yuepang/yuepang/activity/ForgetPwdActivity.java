package com.yuepang.yuepang.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;

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
        switch (v.getId()) {
            case R.id.tv_getcoed:

                break;
            case R.id.btn_sub:

                break;
        }
    }
}
