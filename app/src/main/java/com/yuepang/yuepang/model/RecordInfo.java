package com.yuepang.yuepang.model;

/**
 * Created by xugh on 2019/3/27.
 */

public class RecordInfo {

    private String shopName;// 商家名称

    private long orderDate;// 时间

    private int price;// 金额

    private String OrderId;// 订单


    public String getMerchantName() {
        return shopName;
    }

    public void setMerchantName(String merchantName) {
        this.shopName = merchantName;
    }

    public long getTime() {
        return orderDate;
    }

    public void setTime(long time) {
        this.orderDate = time;
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

}
