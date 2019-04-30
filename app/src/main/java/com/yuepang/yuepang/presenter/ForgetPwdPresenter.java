package com.yuepang.yuepang.presenter;

import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.ForgetPwdActivity;
import com.yuepang.yuepang.activity.LoginActivity;
import com.yuepang.yuepang.async.CommonTaskExecutor;
import com.yuepang.yuepang.control.CheckManage;
import com.yuepang.yuepang.control.GetTelCodeControl;
import com.yuepang.yuepang.control.UserCentreControl;
import com.yuepang.yuepang.model.AuthCodeInfo;
import com.yuepang.yuepang.protocol.RegisterProtocol;

/**
 * Created by xugh on 2019/3/27.
 */

public class ForgetPwdPresenter extends BasePresenter {

    private GetTelCodeControl codeControl;

    private ForgetPwdActivity forgetPwdActivity;


    public ForgetPwdPresenter(ForgetPwdActivity activity) {
        super(activity);
        this.forgetPwdActivity =  activity;
        codeControl = new GetTelCodeControl(activity, forgetPwdActivity.getEdTel(), forgetPwdActivity.getCode(), forgetPwdActivity.getTvGetCode());
    }

    public void resetPwd() {
        final String pw1 = forgetPwdActivity.getPwd1();
        final String pw2 = forgetPwdActivity.getPwd2();
        final AuthCodeInfo info = codeControl.getInfo();
        if (info != null && CheckManage.checkPwd(pw1, pw2, forgetPwdActivity)) {
            CommonTaskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    RegisterProtocol protocol = new RegisterProtocol(forgetPwdActivity, info, pw1);
                    if (protocol.request() == 200) {
                        UserCentreControl.getInstance().outLogin(forgetPwdActivity);
                    }
                }
            });
        }
    }
}
