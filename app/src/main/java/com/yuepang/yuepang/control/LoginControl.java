package com.yuepang.yuepang.control;

import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.MainActivity;
import com.yuepang.yuepang.async.CommonTaskExecutor;
import com.yuepang.yuepang.protocol.LoginProtocol;

/**
 * Created by xugh on 2019/3/6.
 */

public class LoginControl {

    private BaseActivity baseActivity;

    public LoginControl(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }


    public void loginByPwd(String loginName, String pwd) {
        final LoginProtocol protocol = new LoginProtocol(baseActivity,loginName,pwd);
        CommonTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                int code = protocol.request();
                if(code == 200){
                    baseActivity.startActivity(MainActivity.class);
                }
            }
        });
    }
}
