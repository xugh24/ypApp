package com.yuepang.yuepang.control;

import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.MainActivity;
import com.yuepang.yuepang.async.CommonTaskExecutor;
import com.yuepang.yuepang.protocol.LoginProtocol;

import org.json.JSONObject;

/**
 * Created by xugh on 2019/3/6.
 */

public class LoginControl {

    private BaseActivity baseActivity;

    private LoginResult loginResult;

    public LoginControl(BaseActivity baseActivity, LoginResult loginResult) {
        this.baseActivity = baseActivity;
        this.loginResult = loginResult;
    }


    public void loginByPwd(String loginName, String pwd) {
        final LoginProtocol protocol = new LoginProtocol(baseActivity, loginName, pwd);
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

                }
            }
        });
    }

    public interface LoginResult {
        public abstract void loginSuccess();

        public abstract void loginFailed();
    }
}
