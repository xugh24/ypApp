package com.yuepang.yuepang.presenter;

import com.yuepang.yuepang.Util.Md5;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.ForgetPwdActivity;
import com.yuepang.yuepang.activity.LoginActivity;
import com.yuepang.yuepang.async.CommonTaskExecutor;
import com.yuepang.yuepang.control.CheckManage;
import com.yuepang.yuepang.control.GetTelCodeControl;
import com.yuepang.yuepang.control.UserCentreControl;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.AuthCodeInfo;
import com.yuepang.yuepang.protocol.CheckCodeProtocol;
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
        final AuthCodeInfo authCodeInfo = codeControl.getInfo();
        new CheckCodeProtocol(forgetPwdActivity, new LoadCallBack() {
            @Override
            public void loadCallBack(CallType callType, int CODE, String msg, Object info) {
                if (callType.equals(CallType.SUCCESS)) {
                    forgetPwdActivity.getDataControl().setPwd(authCodeInfo.getmTel(),"123456");
                    forgetPwdActivity.getDataControl().setLoginName(authCodeInfo.getmTel());
                    forgetPwdActivity.getDataControl().setNewPwd(authCodeInfo.getmTel(),pw1);
                }
            }
        }, authCodeInfo.getmTel(), authCodeInfo.getCode()).request();
    }
}
