package com.yuepang.yuepang.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xugh on 2019/4/3.
 */

public class PayItem implements Parcelable {

    private int price;

    private String orderId;

    private String merchantName;

    public PayItem() {
    }


    protected PayItem(Parcel in) {
        price = in.readInt();
        orderId = in.readString();
        merchantName = in.readString();
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
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(price);
        dest.writeString(orderId);
        dest.writeString(merchantName);
    }
}
