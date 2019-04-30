package com.yuepang.yuepang.presenter;

import com.yuepang.yuepang.activity.LoginActivity;
import com.yuepang.yuepang.control.CheckManage;
import com.yuepang.yuepang.control.DataControl;
import com.yuepang.yuepang.control.LoginControl;
import com.yuepang.yuepang.control.UserCentreControl;
import com.yuepang.yuepang.interFace.LoginState;
import com.yuepang.yuepang.model.UserInfo;

/**
 * Created by xugh on 2019/3/24.
 * <p>
 * 登录MVP 模式开发本类是 Presenter 负责
 * 数据交互和逻辑处理
 */

public class LoginPresenter extends BasePresenter<LoginActivity> implements LoginState {

    private LoginControl loginControl;// 登录管理类

    private String loginName; // 用户名

    private String pwd; // 密码

    private UserInfo info; // 用户信息 MVP中的M

    public LoginPresenter(LoginActivity activity) {
        super(activity);
        loginControl = new LoginControl(activity);
        UserCentreControl.getInstance().addLoginMonitor(this);
    }

    /**
     * 登录方法
     */
    public void login() {
        loginName = activity.getLoginName();// 获得帐号密码
        pwd = activity.getPwd();
        if (CheckManage.checklogin(loginName, pwd, activity)) {// 校验成功后发起登录
            loginControl.loginByPwd(loginName, pwd);
        }
    }

    /**
     * 登录成功接口实现方法
     */
    @Override
    public void loginSuccess() {// 保存账号密码
        DataControl.getInstance(activity).setLoginName(loginName);
        DataControl.getInstance(activity).setPwd(pwd);
    }

    @Override
    public void loginFailed() {

    }


    public void onDestroy() {// 解除登录成功监听
        UserCentreControl.getInstance().relieveLoginMonitor(this);
    }
}
