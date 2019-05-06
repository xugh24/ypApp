package com.yuepang.yuepang.model;

import com.yuepang.yuepang.Util.SysUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xugh on 2019/3/27.
 */

public class RecordInfo {

    private String shopName;// 商家名称

    private long orderDate;// 时间

    private int price;// 金额


    public String getMerchantName() {
        return shopName;
    }

    public void setMerchantName(String merchantName) {
        this.shopName = merchantName;
    }

    public long getTime() {
        return orderDate;
    }

    public String getData() {
        return SysUtils.stampToDate(orderDate);
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

    /*
    * 将时间戳转换为时间
    */
    public String getOrderId() {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(orderDate);
        res = simpleDateFormat.format(date);
        return res + orderDate / 1000;
    }


}
