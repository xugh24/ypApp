package com.yuepang.yuepang.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.common.annotation.view.AnnotateBindViewUtil;
import com.android.common.annotation.view.BindViewByTag;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.SysUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.PayRecordActivity;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.PayItem;
import com.yuepang.yuepang.model.RecordInfo;
import com.yuepang.yuepang.protocol.GetRecordProtocol;

import java.util.List;

/**
 * Created by xugh on 2019/3/27.
 */

public class RecordAdapter extends YueBaseAdapter<RecordInfo> implements LoadCallBack<List<RecordInfo>> {


    public RecordAdapter(BaseActivity activity) {
        super(activity);
        getdata();// 获得数据
    }

    private void getdata() {
        new GetRecordProtocol(activity, this).request();
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
        viewHolder.name.setText("商家名称：" + getItem(position).getMerchantName());
        viewHolder.orderId.setText("订单号："+ getItem(position).getOrderId());
        viewHolder.price.setText("金额："+getItem(position).getPrice() + "");
        viewHolder.time.setText(getItem(position).getData());
        return convertView;
    }

    @Override
    public void loadCallBack(final CallType callType, int CODE, String msg, final List<RecordInfo> infos) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (callType.equals(CallType.SUCCESS)) {
                    setList(infos);
                    notifyDataSetChanged();
                }
            }
        });
    }


    private final class ViewHolder {
        @BindViewByTag
        TextView name;
        @BindViewByTag
        TextView price;
        @BindViewByTag
        TextView orderId;
        @BindViewByTag
        TextView time;

        public ViewHolder(View view) {
            AnnotateBindViewUtil.initBindView(this, view, null);
        }
    }
}
