package com.yuepang.yuepang.presenter;

import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.MainActivity;
import com.yuepang.yuepang.activity.RegisterActivity;
import com.yuepang.yuepang.control.CheckManage;
import com.yuepang.yuepang.control.DataControl;
import com.yuepang.yuepang.control.GetTelCodeControl;
import com.yuepang.yuepang.control.LoginControl;

/**
 * Created by xugh on 2019/3/27.
 */

public class RegPresenter extends BasePresenter implements LoginControl.LoginResult {

    private GetTelCodeControl codeControl;

    private RegisterActivity registerActivity;

    private LoginControl loginControl;

    public RegPresenter(BaseActivity activity) {
        super(activity);
        this.registerActivity = (RegisterActivity) activity;
        codeControl = new GetTelCodeControl(registerActivity, registerActivity.getEdtel(), registerActivity.getEdCode(), registerActivity.getTvGetCode());
        loginControl = new LoginControl(registerActivity, this);
    }

    private String tel;

    private String pwd;

    public void register() {
        tel = registerActivity.getTel();// 获得手机号
        String code = registerActivity.getcode();// 获得验证码
        pwd = registerActivity.getPwd();// 获得密码
        if (CheckManage.checkTelRg(tel, pwd, code, registerActivity)) {// 校验通过
            registerActivity.showLoadingDialogSafe(true);
            loginControl.regByPwd(tel, pwd, code);
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
