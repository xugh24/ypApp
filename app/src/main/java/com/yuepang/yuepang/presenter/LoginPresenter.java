package com.yuepang.yuepang.presenter;

import com.yuepang.yuepang.activity.ForgetPwdActivity;
import com.yuepang.yuepang.activity.LoginActivity;
import com.yuepang.yuepang.activity.MainActivity;
import com.yuepang.yuepang.activity.RegisterActivity;
import com.yuepang.yuepang.control.CheckManage;
import com.yuepang.yuepang.control.DataControl;
import com.yuepang.yuepang.control.LoginControl;
import com.yuepang.yuepang.model.UserInfo;

/**
 * Created by xugh on 2019/3/24.
 * <p>
 * 登录MVP 模式开发本类是 Presenter 负责
 * 数据交互和逻辑处理
 */

public class LoginPresenter  extends  BasePresenter<LoginActivity> implements LoginControl.LoginResult {

    private LoginControl loginControl;// 登录管理类

    private String loginName; // 用户名

    private String pwd; // 密码

    private UserInfo info; // 用户信息 MVP中的M

    public LoginPresenter(LoginActivity activity) {
        super(activity);
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

    /**
     * 登录成功接口实现方法
     */
    @Override
    public void loginSuccess() {
        saveAccount();
    }

    private void saveAccount() {
        DataControl.getInstance(activity).setLoginName(loginName);
        DataControl.getInstance(activity).setPwd(pwd);
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

    public void toRegPage() {
        activity.startActivity(RegisterActivity.class);
    }

    public void toForgetPage() {
        activity.startActivity(ForgetPwdActivity.class);
    }
}
