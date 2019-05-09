package com.yuepang.yuepang.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xugh on 2019/4/3.
 */

public class PayItem implements Parcelable {

    private int id;

    private float price;

    private String orderId;

    private String shopName;

    private long orderDate;



    public PayItem() {
    }


    protected PayItem(Parcel in) {
        id = in.readInt();
        price = in.readFloat();
        orderId = in.readString();
        shopName = in.readString();
        orderDate = in.readLong();
    }

    public static final Creator<PayItem> CREATOR = new Creator<PayItem>() {
        @Override
        public PayItem createFromParcel(Parcel in) {
            return new PayItem(in);
        }

        @Override
        public PayItem[] newArray(int size) {
            return new PayItem[size];
        }
    };

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMerchantName() {
        return shopName;
    }

    public void setMerchantName(String merchantName) {
        this.shopName = merchantName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeFloat(price);
        dest.writeString(orderId);
        dest.writeString(shopName);
        dest.writeLong(orderDate);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return orderDate;
    }

    public void setTime(long time) {
        this.orderDate = time;
    }

    /*
    * 将时间戳转换为时间
    */
    public String getOrderIdst() {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(orderDate);
        res = simpleDateFormat.format(date);
        return res + orderDate / 1000;
    }

}
