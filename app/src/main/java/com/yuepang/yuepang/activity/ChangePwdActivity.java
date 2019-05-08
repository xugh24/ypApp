package com.yuepang.yuepang.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.Md5;
import com.yuepang.yuepang.control.CheckManage;
import com.yuepang.yuepang.control.UserCentreControl;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.protocol.LoginProtocol;

/**
 * Created by xugh on 2019/4/6.
 */

public class ChangePwdActivity extends BaseActivity implements LoadCallBack {


    private EditText edoldpwd;

    private EditText edpwd1;

    private EditText edPwd2;

    private Button btnChange;

    private String oldPwd;

    private String pwd1;

    private String pwd2;


    @Override
    public String getMyTittle() {
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
                new LoginProtocol(this,this,getUserInfo().getTel(), Md5.string2MD5(oldPwd)).request();
            }

        }
    }

    private void changPwd() {
        UserCentreControl.getInstance().outLogin(this);
    }

    @Override
    public void loadCallBack(CallType callType, int CODE, String msg, Object info) {
        if(callType.equals(CallType.SUCCESS)){// 验证成功设置密码
            getDataControl().setNewPwd(pwd1);
            UserCentreControl.getInstance().outLogin(this);
        }
    }
}
