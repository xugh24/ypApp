package com.android.common.pay;

/**
 * Created by xugh on 2019/4/15.
 */

public enum PayEnum {
    ALIMM(1, "支付宝"),// 支付宝免密
    WXPAY(2, "微信");

    private int payType;
    String payName;

    PayEnum(int payType, String payName) {
        this.setPayType(payType);
        this.payName = payName;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }
}
