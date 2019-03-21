package com.yuepang.yuepang.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.model.MerchantInfo;

import java.util.List;

/**
 * Created by xugh on 2019/3/21.
 */

public class MerchantAdapter extends BaseAdapter {

    private List<MerchantInfo> merchantInfos;

    private BaseActivity baseActivity;

    public MerchantAdapter(BaseActivity baseActivity, List<MerchantInfo> merchantInfos) {
        this.baseActivity = baseActivity;
        this.merchantInfos = merchantInfos;
    }


    @Override
    public int getCount() {
        return merchantInfos.size();
    }

    @Override
    public MerchantInfo getItem(int position) {
        return merchantInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    private final class ViewHolder {
        ImageView iv;
        TextView name;
        TextView loction;
        Button btnPay;
    }

}
