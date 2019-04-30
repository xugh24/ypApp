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
                        break;
                    case FINISH:
                        baseActivity.dismissLoadingDialogSafe();// 关闭laoding
                        baseActivity.finish();
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


}
