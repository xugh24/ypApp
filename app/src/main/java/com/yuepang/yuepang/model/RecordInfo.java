package com.yuepang.yuepang.model;

/**
 * Created by xugh on 2019/3/27.
 */

public class RecordInfo {

    private String merchantName;// 商家名称

    private long time;// 时间

    private int price;// 金额

    private String OrderId;// 订单

    private int state;// 订单状态

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
