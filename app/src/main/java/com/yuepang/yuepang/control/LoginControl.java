package com.yuepang.yuepang.control;

import android.text.TextUtils;

import com.yuepang.yuepang.Util.Md5;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.MainActivity;
import com.yuepang.yuepang.async.CommonTaskExecutor;
import com.yuepang.yuepang.db.YuePangExternalDB;
import com.yuepang.yuepang.model.AuthCodeInfo;
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
                    setUser(json);
                    baseActivity.showToastSafe("登录成功");
                    loginResult.loginSuccess();
                    UserCentreControl.getInstance().loginSuccesses();
                } else {
                    if (!TextUtils.isEmpty(protocol.getCodeDesc())) {
                        baseActivity.showToastSafe(protocol.getCodeDesc());
                    }
                    loginResult.loginFailed();
                }
            }
        });
    }
    public void regByPwd(AuthCodeInfo info, String pwd) {
        final RegisterProtocol registerProtocol = new RegisterProtocol(baseActivity,info,pwd);
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
                    UserCentreControl.getInstance().loginSuccesses();
                } else {
                    if (!TextUtils.isEmpty(registerProtocol.getCodeDesc())) {
                        baseActivity.showToastSafe(registerProtocol.getCodeDesc());
                    }
                    loginResult.loginFailed();
                }
            }
        });
    }

    public void setUser(JSONObject json) {
        UserCentreControl.getInstance().setToken(json.optString(LoginProtocol.TOKEN));
        JSONObject jsonUser = json.optJSONObject(LoginProtocol.USERINFO);
        UserCentreControl.getInstance().getInfo().setHeaderImgUrl(jsonUser.optString(LoginProtocol.AVATAR));
        UserCentreControl.getInstance().getInfo().setTel(jsonUser.optString(LoginProtocol.tel));
        UserCentreControl.getInstance().getInfo().setId(jsonUser.optInt(LoginProtocol.ID));
        UserCentreControl.getInstance().getInfo().setSex(jsonUser.optInt(LoginProtocol.SEX));
        UserCentreControl.getInstance().getInfo().setNick(jsonUser.optString(LoginProtocol.NICK));
        YuePangExternalDB.getInstance(baseActivity).insertUser(UserCentreControl.getInstance().getInfo());
    }

    public interface LoginResult {

        public abstract void loginSuccess();

        public abstract void loginFailed();

    }
}
