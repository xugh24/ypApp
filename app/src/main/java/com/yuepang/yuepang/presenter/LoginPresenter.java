package com.yuepang.yuepang.presenter;

import com.yuepang.yuepang.activity.LoginActivity;
import com.yuepang.yuepang.activity.MainActivity;
import com.yuepang.yuepang.control.CheckManage;
import com.yuepang.yuepang.control.DataControl;
import com.yuepang.yuepang.control.LoginControl;
import com.yuepang.yuepang.model.UserInfo;

/**
 * Created by xugh on 2019/3/24.
 */

public class LoginPresenter implements LoginControl.LoginResult {

    private LoginControl loginControl;

    private String loginName; // 用户名

    private String pwd; // 密码

    private LoginActivity activity;

    private UserInfo info;


    public LoginPresenter(LoginActivity activity) {
        this.activity = activity;
        loginControl = new LoginControl(activity, this);
        loginName = DataControl.getInstance(activity).getLoginName();
        pwd = DataControl.getInstance(activity).getPwd();
    }


    /**
     * 登录方法
     */
    public void login() {
        loginName = activity.getLoginName();
        pwd = activity.getPwd();
        if (CheckManage.checklogin(loginName, pwd, activity)) {
            activity.showLoadingDialogSafe(true);
            loginControl.loginByPwd(loginName, pwd);
        }
    }

    @Override
    public void loginSuccess() {
        activity.dismissLoadingDialogSafe();
        activity.startActivity(MainActivity.class);
        activity.finish();
    }

    @Override
    public void loginFailed() {
        activity.dismissLoadingDialogSafe();
    }

    public String getLoginName() {
        return loginName;
    }

    public String getPwd() {
        return pwd;
    }
}
