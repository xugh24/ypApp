package com.android.common.pay;

import com.android.common.activity.ActivityManage;
import com.android.common.inter.PayCallBack;

/**
 * Created by xugh on 2019/4/15.
 * 接入支付时，首先继承本类，然后在改造PayEnum，添加需要实现的支付类型
 * 通过第三方支付的回调调用paycall方法，接入的支付的activity 需要实现PayCallBack 接口
 * 通过接口和第三方SDK解耦，后期增加删除第三方SDK只要 修改本类的子类即可
 */

public abstract class PayProcess {
    private ActivityManage activityManage;
    private PayCallBack payCallBack;


    public PayProcess(ActivityManage activityManage, PayCallBack PayCallBack) {
        this.activityManage = activityManage;
        this.payCallBack = payCallBack;
    }

    public abstract void pay(int type, Object object);
}
