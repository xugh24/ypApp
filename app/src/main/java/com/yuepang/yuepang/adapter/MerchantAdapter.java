package com.yuepang.yuepang.adapter;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.common.annotation.view.AnnotateBindViewUtil;
import com.android.common.annotation.view.BindViewByTag;
import com.android.common.async.ImageLoaderUtil;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.MerchantDetailActivity;
import com.yuepang.yuepang.activity.PayActivity;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.AreaInfo;
import com.yuepang.yuepang.model.MerchantInfo;
import com.yuepang.yuepang.protocol.GetShopListProtocol;
import java.util.ArrayList;
import java.util.List;


/**
 * 商家设配器类
 */

public class MerchantAdapter extends YueBaseAdapter<MerchantInfo> implements LoadCallBack<List<MerchantInfo>> {


    private List<MerchantInfo> allInfos;

    public MerchantAdapter(BaseActivity baseActivity) {
        super(baseActivity);
        allInfos = new ArrayList<>();
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
        ImageLoaderUtil.LoadImageViewForUrl(holder.iv, getItem(position).getPicture());
        return convertView;
    }

    /**
     * 切换商圈 重新刷新数据
     */
    public void refresh(AreaInfo info) {
        new GetShopListProtocol(activity, this, info.getId()).request();
    }

    @Override
    public void loadCallBack(final CallType callType, int CODE, String msg, final List<MerchantInfo> infos) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (callType.equals(CallType.SUCCESS)) {
                    allInfos.clear();
                    allInfos.addAll(infos);
                    setList(infos);
                    notifyDataSetChanged();
                }
            }
        });
    }

    /**
     * 模糊搜索功能
     */
    public void serchList(String s) {
        if (TextUtils.isEmpty(s)) {// 如果关键词为空，设置全部数据
            setList(allInfos);
        } else {
            List<MerchantInfo> newList = new ArrayList<>();// 新建新数据
            for (MerchantInfo info : allInfos) { // 遍历原数据。满足调教设置到新数据
                if (info.getName().indexOf(s) != -1) {
                    newList.add(info);
                }
            }
            if (newList.size() == 0) {// 如果新数据为空，设置原数据，并提示用户
                setList(allInfos);
                activity.showToastSafe("搜索无结果，返回原数据");
            } else {// 否则设置新数据
                setList(newList);
            }
        }//  刷新UI
        notifyDataSetChanged();
    }

    private final class ViewHolder implements View.OnClickListener {
        @BindViewByTag
        ImageView iv;
        @BindViewByTag
        TextView name;
        @BindViewByTag
        TextView loction;
        @BindViewByTag(click = true)
        Button btnPay;

        private int position;

        public ViewHolder(View view) {
            AnnotateBindViewUtil.initBindView(this, view, this);
        }

        @Override
        public void onClick(View v) {
            if (v == btnPay) {
                PayActivity.toPay(activity, getItem(position));
            } else {
                MerchantDetailActivity.toMerActivity(activity, getItem(position));
            }
        }
    }

}
