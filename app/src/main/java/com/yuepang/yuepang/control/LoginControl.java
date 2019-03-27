package com.yuepang.yuepang.control;

import android.text.TextUtils;

import com.yuepang.yuepang.Util.Md5;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.MainActivity;
import com.yuepang.yuepang.async.CommonTaskExecutor;
import com.yuepang.yuepang.protocol.LoginProtocol;
import com.yuepang.yuepang.protocol.RegisterProtocol;

import org.json.JSONObject;

/**
 */

public class LoginControl {

    private BaseActivity baseActivity;

    private LoginResult loginResult;

    public LoginControl(BaseActivity baseActivity, LoginResult loginResult) {
        this.baseActivity = baseActivity;
        this.loginResult = loginResult;
    }


    public void loginByPwd(String loginName, final String pwd) {
        final LoginProtocol protocol = new LoginProtocol(baseActivity, loginName, Md5.string2MD5(pwd));
        CommonTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                int code = protocol.request();
                if (code == 200) {
                    baseActivity.startActivity(MainActivity.class);
                    JSONObject json = (JSONObject) protocol.getData();
                    UserCentreControl.getInstance().setToken(json.optString(LoginProtocol.TOKEN));
                    JSONObject jsonUser = json.optJSONObject(LoginProtocol.USERINFO);
                    UserCentreControl.getInstance().getInfo().setHeaderImgUrl(jsonUser.optString(LoginProtocol.AVATAR));
                    UserCentreControl.getInstance().getInfo().setName(jsonUser.optString(LoginProtocol.USERNAME));
                    baseActivity.showToastSafe("登录成功");
                    loginResult.loginSuccess();
                } else {
                    if (!TextUtils.isEmpty(protocol.getCodeDesc())) {
                        baseActivity.showToastSafe(protocol.getCodeDesc());
                    }
                    loginResult.loginFailed();
                }
            }
        });
    }
    public void regByPwd(String tel ,String pwd,  String code) {
        final RegisterProtocol registerProtocol = new RegisterProtocol(baseActivity,tel,code,pwd);
        CommonTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                int code = registerProtocol.request();
                if (code == 200) {
                    baseActivity.startActivity(MainActivity.class);
                    JSONObject json = (JSONObject) registerProtocol.getData();
                    // TODO 协议好了具体处理
                    baseActivity.showToastSafe("登录成功");
                    loginResult.loginSuccess();
                } else {
                    if (!TextUtils.isEmpty(registerProtocol.getCodeDesc())) {
                        baseActivity.showToastSafe(registerProtocol.getCodeDesc());
                    }
                    loginResult.loginFailed();
                }
            }
        });
    }

    public interface LoginResult {
        public abstract void loginSuccess();

        public abstract void loginFailed();
    }
}
