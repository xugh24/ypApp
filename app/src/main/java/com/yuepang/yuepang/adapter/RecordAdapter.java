package com.yuepang.yuepang.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.common.annotation.view.AnnotateBindViewUtil;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.model.RecordInfo;

import java.util.List;

/**
 * Created by xugh on 2019/3/27.
 */

public class RecordAdapter extends YueBaseAdapter <RecordInfo>{


    public RecordAdapter(BaseActivity activity, List<RecordInfo> recordInfos) {
        super(activity, recordInfos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(activity, R.layout.record_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else if (convertView.getTag() instanceof ViewHolder) {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(getItem(position).getMerchantName());
        viewHolder.orderId.setText(getItem(position).getOrderId());
        viewHolder.price.setText(getItem(position).getPrice()+"");
        viewHolder.time.setText(getItem(position).getTime() + "");
        return convertView;
    }


    private final class ViewHolder {
        TextView name;

        TextView price;

        TextView orderId;

        TextView state;

        TextView time;

        public ViewHolder(View view) {
            AnnotateBindViewUtil.initBindView(this,view,null);
        }
    }
}
