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
import com.yuepang.yuepang.protocol.LoginProtocol;
import com.yuepang.yuepang.protocol.RegisterProtocol;

import org.json.JSONObject;

/**
 */

public class LoginControl implements LoadCallBack<UserInfo> {

    private BaseActivity baseActivity;

    private LoginResult loginResult;

    public LoginControl(BaseActivity baseActivity, LoginResult loginResult) {
        this.baseActivity = baseActivity;
        this.loginResult = loginResult;
    }

    @Override
    public void loadCallBack(CallType callType, int CODE, String msg, UserInfo info) {
        LogUtils.e("callType " + callType);
        switch (callType) {
            case SUCCESS:
                baseActivity.startActivity(MainActivity.class);
                UserCentreControl.getInstance().setInfo(info);
                baseActivity.showToastSafe("登录成功");
                loginResult.loginSuccess();
                UserCentreControl.getInstance().loginSuccesses();
                break;
            case FINISH:
                baseActivity.dismissLoadingDialogSafe();
                baseActivity.finish();
                break;
            case FAILED:
                baseActivity.showToastSafe(msg);
                break;
        }
    }


    public void loginByPwd(String loginName, final String pwd) {
        final LoginProtocol protocol = new LoginProtocol(baseActivity, this, loginName, Md5.string2MD5(pwd));
        protocol.request();
    }

    public void regByPwd(AuthCodeInfo info, String pwd) {
//        final RegisterProtocol registerProtocol = new RegisterProtocol(baseActivity,info,pwd);
//        CommonTaskExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                int code = registerProtocol.request();
//                if (code == 200) {
//                    baseActivity.startActivity(MainActivity.class);
//                    JSONObject json = (JSONObject) registerProtocol.getData();
//                    // TODO 协议好了具体处理
//                    baseActivity.showToastSafe("登录成功");
//                    loginResult.loginSuccess();
//                    UserCentreControl.getInstance().loginSuccesses();
//                } else {
//                    if (!TextUtils.isEmpty(registerProtocol.getCodeDesc())) {
//                        baseActivity.showToastSafe(registerProtocol.getCodeDesc());
//                    }
//                    loginResult.loginFailed();
//                }
//            }
//        });
    }


    public interface LoginResult {
        public abstract void loginSuccess();

        public abstract void loginFailed();
    }
}
