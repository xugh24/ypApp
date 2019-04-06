package com.yuepang.yuepang.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.control.CheckManage;
import com.yuepang.yuepang.control.UserCentreControl;

/**
 * Created by xugh on 2019/4/6.
 */

public class ChangePwdActivity extends BaseActivity {


    @BindView(id = R.id.tv_oldpwd)
    private EditText edoldpwd;

    @BindView(id = R.id.tv_pwd1)
    private EditText edpwd1;

    @BindView(id = R.id.tv_pwd2)
    private EditText edPwd2;

    @BindView(id = R.id.btn_sub,click = true)
    private Button btnChange;

    private String oldPwd;

    private String pwd1;

    private String pwd2;

    @Override
    protected String getMyRTitle() {
        return null;
    }

    @Override
    protected String getMyTittle() {
        return "修改密码";
    }

    @Override
    protected int getContentViewId() {
        return R.layout.change_pwd_ly;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if(btnChange == v){
            oldPwd = edoldpwd.getText().toString();
            pwd1 = edpwd1.getText().toString();
            pwd2 = edPwd2.getText().toString();
            if(TextUtils.isEmpty(oldPwd)){
                showToastSafe("请输入旧密码");
                return;
            }
            if(CheckManage.checkPwd(pwd1,pwd2,this)){
                changPwd();
            }

        }
    }

    private void changPwd() {
        UserCentreControl.getInstance().outLogin(this);
    }
}
