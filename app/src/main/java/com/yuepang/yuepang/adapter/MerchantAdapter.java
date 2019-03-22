package com.yuepang.yuepang.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.model.MerchantInfo;

import java.util.List;

/**
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
        ViewHolder holder = null;
        if(convertView == null){
            convertView = View.inflate(baseActivity, R.layout.merchant_item_ly,null);
            holder = new ViewHolder();
            holder.iv = convertView.findViewById(R.id.iv_pic);
            holder.name = convertView.findViewById(R.id.tv_name);
            holder.loction = convertView.findViewById(R.id.tv_location);
            holder.btnPay = convertView.findViewById(R.id.btn_buy);
            convertView.setTag(holder);
        }else if (convertView.getTag() instanceof ViewHolder) {
            holder = (ViewHolder) convertView.getTag();
        }
//        holder.head.setText(topicInfos.get(position).getHeader());
//        holder.title.setText(topicInfos.get(position).getTitle());
//        holder.content.setText(topicInfos.get(position).getContentSt());
//        holder.time.setText(topicInfos.get(position).getTime());
        return null;
    }

    private final class ViewHolder {
        ImageView iv;
        TextView name;
        TextView loction;
        Button btnPay;
    }

}
