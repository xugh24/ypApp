package com.yuepang.yuepang.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.AnnotateUtil;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.model.MerchantInfo;

import java.util.List;


/**
 * 商家设配器类
 */

public class MerchantAdapter extends YueBaseAdapter {

    private List<MerchantInfo> merchantInfos;

    public MerchantAdapter(BaseActivity baseActivity, List<MerchantInfo> merchantInfos) {
        super(baseActivity, merchantInfos);
        this.merchantInfos = merchantInfos;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(activity, R.layout.merchant_item_ly, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else if (convertView.getTag() instanceof ViewHolder) {
            holder = (ViewHolder) convertView.getTag();
        }
        return null;
    }

    private final class ViewHolder implements View.OnClickListener{
        @BindView(id = R.id.iv_pic)
        ImageView iv;
        @BindView(id = R.id.tv_name)
        TextView name;
        @BindView(id = R.id.tv_location)
        TextView loction;
        @BindView(id = R.id.btn_buy)
        Button btnPay;

        public ViewHolder(View view) {
            AnnotateUtil.initBindView(this, view);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
