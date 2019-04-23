package com.android.common.inter;

import com.android.common.model.PayResultInfo;

/**
 * Created by xugh on 2019/4/15.
 *
 * 支付返回结果类
 */

public interface PayCallBack {

    public static final int PAY_SUCCESS = 0x01;// 支付成功
    public static final int PAY_FAIL = 0x02;// 支付失败
    public static final int PAY_CANCLE = 0x03;// 支付取消
    public static final int PAY_WAITTING = 0x04;// 支付等待

    /**
     * @param code 返回结果 PAY_SUCCESS 支付成功，
     *             PAY_FAIL 支付失败
     *             PAY_CANCLE 支付取消
     *             PAY_WAITTING 支付等待
     * @param msg 支付结果说明
     *
     * @param msg 支付返回信息bin，可以为空
     */
    public void payCallBack(int code, String msg, PayResultInfo payResultInfo);
}
