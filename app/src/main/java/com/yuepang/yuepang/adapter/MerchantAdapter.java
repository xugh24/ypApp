package com.yuepang.yuepang.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.AnnotateUtil;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.async.CommonTaskExecutor;
import com.yuepang.yuepang.model.MerchantInfo;
import com.yuepang.yuepang.test.TestData;

import java.util.List;


/**
 * 商家设配器类
 */

public class MerchantAdapter extends YueBaseAdapter implements AdapterView.OnItemClickListener {

    private List<MerchantInfo> merchantInfos;

    public MerchantAdapter(BaseActivity baseActivity) {
        super(baseActivity);
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
        convertView.setOnClickListener(holder);
        holder.name.setText(merchantInfos.get(position).getName());
        holder.loction.setText(merchantInfos.get(position).getLocation());
        holder.position = position;
        return convertView;
    }

    public boolean getData() {
        CommonTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        merchantInfos = TestData.getMerinfos();
        setList(merchantInfos);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LogUtils.e("---onItemClick-------"+position);

    }

    private final class ViewHolder implements View.OnClickListener {
        @BindView(id = R.id.iv_pic)
        ImageView iv;
        @BindView(id = R.id.tv_name)
        TextView name;
        @BindView(id = R.id.tv_location)
        TextView loction;
        @BindView(id = R.id.btn_buy, click = true)
        Button btnPay;

        private int position;

        public ViewHolder(View view) {
            AnnotateUtil.initBindView(this, view);
        }

        @Override
        public void onClick(View v) {
            LogUtils.e("----onClick------"+position);
            if (v == btnPay) {
                activity.toPay(merchantInfos.get(position));
            }else {
                activity.toMerActivity(merchantInfos.get(position));
            }

        }
    }

}
