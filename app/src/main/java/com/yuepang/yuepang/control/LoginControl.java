package com.yuepang.yuepang.control;

import android.text.TextUtils;
import android.widget.Switch;

import com.android.common.model.ResultInfo;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.Util.Md5;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.MainActivity;
import com.yuepang.yuepang.async.CommonTaskExecutor;
import com.yuepang.yuepang.db.YuePangExternalDB;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.AuthCodeInfo;
import com.yuepang.yuepang.model.UserInfo;
import com.yuepang.yuepang.protocol.CheckCodeProtocol;
import com.yuepang.yuepang.protocol.LoginProtocol;
import com.yuepang.yuepang.protocol.RegisterProtocol;

import org.json.JSONObject;

/**
 */

public class LoginControl implements LoadCallBack<UserInfo> {

    private BaseActivity baseActivity;

    private String name;

    private String rPwd;

    public LoginControl(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void loadCallBack(final CallType callType, int CODE, final String msg, final UserInfo info) {
        baseActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (callType) {
                    case SUCCESS:// 登录成功处理
                        UserCentreControl.getInstance().setInfo(info);// 设置全局User
                        baseActivity.showToastSafe("登录成功");// 提示语
                        UserCentreControl.getInstance().loginSuccesses();// 发出登录成功提示
                        baseActivity.startActivity(MainActivity.class);// 启动主activity
                        DataControl.getInstance(baseActivity).setLoginName(name);
                        DataControl.getInstance(baseActivity).setPwd(name, rPwd);
                        baseActivity.finish();
                        break;
                    case FINISH:
                        baseActivity.dismissLoadingDialogSafe();// 关闭laoding

                        break;
                    case FAILED:
                        baseActivity.showToastSafe(msg);
                        break;
                    case START:
                        baseActivity.showLoadingDialogSafe(false);
                }
            }
        });
    }


    public void loginByPwd(String loginName, final String pwd) {
        String newPwd = baseActivity.getDataControl().getNewPwd(loginName);
        name = loginName;
        rPwd = pwd;
        if (!TextUtils.isEmpty(newPwd)) {
            if (pwd.equals(newPwd)) {
                rPwd = baseActivity.getDataControl().getPwdBy(loginName);
            } else {
                rPwd = newPwd;
            }
        }
        new LoginProtocol(baseActivity, this, loginName, Md5.string2MD5(rPwd)).request();
    }

    public void regByPwd(AuthCodeInfo info, final String pwd) {
        final String tel = info.getmTel();
        String code = info.getCode();
        new CheckCodeProtocol(baseActivity, new LoadCallBack() {
            @Override
            public void loadCallBack(CallType callType, int CODE, String msg, Object info) {
                if (callType.equals(CallType.SUCCESS)) {
                    name = tel;
                    rPwd = pwd;
                    new RegisterProtocol(baseActivity, this, name, Md5.string2MD5(rPwd)).request();
                }
            }
        }, tel, code).request();
    }
}
