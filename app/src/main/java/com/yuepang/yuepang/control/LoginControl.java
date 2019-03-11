package com.yuepang.yuepang.control;

import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.MainActivity;

/**
 * Created by xugh on 2019/3/6.
 */

public class LoginControl {

    private BaseActivity baseActivity;

    public LoginControl(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }


    public void loginByPwd(String loginName, String pwd) {
        baseActivity.startActivity(MainActivity.class);
    }
}
