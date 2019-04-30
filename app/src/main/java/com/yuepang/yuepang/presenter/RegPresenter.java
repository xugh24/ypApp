package com.yuepang.yuepang.presenter;

import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.MainActivity;
import com.yuepang.yuepang.activity.RegisterActivity;
import com.yuepang.yuepang.control.CheckManage;
import com.yuepang.yuepang.control.DataControl;
import com.yuepang.yuepang.control.GetTelCodeControl;
import com.yuepang.yuepang.control.LoginControl;
import com.yuepang.yuepang.interFace.LoginState;
import com.yuepang.yuepang.model.AuthCodeInfo;

/**
 * Created by xugh on 2019/3/27.
 */

public class RegPresenter extends BasePresenter implements LoginState {

    private GetTelCodeControl codeControl;

    private RegisterActivity registerActivity;

    private LoginControl loginControl;

    private String pwd;

    private String tel;

    public RegPresenter(BaseActivity activity) {
        super(activity);
        this.registerActivity = (RegisterActivity) activity;
        codeControl = new GetTelCodeControl(registerActivity, registerActivity.getEdtel(), registerActivity.getEdCode(), registerActivity.getTvGetCode());
        loginControl = new LoginControl(registerActivity);
    }



    public void register() {
        pwd = registerActivity.getPwd();// 获得密码
        AuthCodeInfo info = codeControl.getInfo();
        if (info != null && CheckManage.checkPwd(pwd, registerActivity)) {
            tel = info.getmTel();//缓存手机号
            registerActivity.showLoadingDialogSafe(true);
            loginControl.regByPwd(info, pwd);
        }
    }

    /**
     * 登录成功接口实现方法
     */
    @Override
    public void loginSuccess() {
        saveAccount();
        registerActivity.dismissLoadingDialogSafe();
        registerActivity.startActivity(MainActivity.class);
        registerActivity.finish();
    }

    private void saveAccount() {
        DataControl.getInstance(registerActivity).setLoginName(tel);
        DataControl.getInstance(registerActivity).setPwd(pwd);
    }

    @Override
    public void loginFailed() {
        registerActivity.dismissLoadingDialogSafe();
    }
}
