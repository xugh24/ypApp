package com.yuepang.yuepang.control;

import com.yuepang.yuepang.model.UserInfo;

/**
 * Created by xugh on 2019/3/21.
 */

public class UserCentreControl {

    private static UserCentreControl centreControl;

    private UserInfo info;// 用户信息

    public synchronized UserCentreControl getInstance() {
        if (centreControl == null) {
            centreControl = new UserCentreControl();
        }
        return centreControl;
    }

    public UserInfo getInfo() {
        return info;
    }

    public void setInfo(UserInfo info) {
        this.info = info;
    }
}
