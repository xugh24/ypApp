package com.yuepang.yuepang.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.common.annotation.view.AnnotateBindViewUtil;
import com.android.common.annotation.view.BindViewByTag;
import com.android.common.async.ImageLoaderUtil;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.MerchantDetailActivity;
import com.yuepang.yuepang.activity.PayActivity;
import com.yuepang.yuepang.interFace.AreaInterFace;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.AreaInfo;
import com.yuepang.yuepang.model.MerchantInfo;
import com.yuepang.yuepang.protocol.GetShopListProtocol;
import java.util.List;


/**
 * 商家设配器类
 */

public class MerchantAdapter    extends YueBaseAdapter <MerchantInfo>  implements LoadCallBack  {

    private AreaInterFace interFace;

    private int areaId;

    public MerchantAdapter(BaseActivity baseActivity, AreaInterFace interFace) {
        super(baseActivity);
        this.interFace = interFace;
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
        holder.name.setText(getItem(position).getName());
        holder.loction.setText(getItem(position).getLocation());
        holder.position = position;
        ImageLoaderUtil.LoadImageViewForUrl(holder.iv,getItem(position).getPicture());
        return convertView;
    }

    public void getData() {
        new GetShopListProtocol(activity, this, 1).request();
    }


    /**
     * 切换商圈 重新刷新数据
     */
    public void refresh(AreaInfo info) {
        areaId = info.getId();
        getData();
    }

    @Override
    public void loadCallBack(final CallType callType, int CODE, String msg,final Object infos) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(callType.equals(CallType.SUCCESS)){
                    setList((List<MerchantInfo>) infos);
                    notifyDataSetChanged();
                }
            }
        });

    }


    private final class ViewHolder implements View.OnClickListener {
        @BindViewByTag
        ImageView iv;
        @BindViewByTag
        TextView name;
        @BindViewByTag
        TextView loction;
        @BindViewByTag
        Button btnPay;

        private int position;
        public ViewHolder(View view) {
            AnnotateBindViewUtil.initBindView(this, view, this);
        }

        @Override
        public void onClick(View v) {
            LogUtils.e("----onClick------" + position);
            if (v == btnPay) {
                PayActivity.toPay(activity,getItem(position));
            } else {
                MerchantDetailActivity.toMerActivity(activity,getItem(position));
            }
        }
    }

}
