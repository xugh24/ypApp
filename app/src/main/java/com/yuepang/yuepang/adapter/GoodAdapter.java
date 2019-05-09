package com.yuepang.yuepang.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.common.annotation.view.AnnotateBindViewUtil;
import com.android.common.annotation.view.BindView;
import com.android.common.annotation.view.BindViewByTag;
import com.android.common.async.ImageLoaderUtil;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.interFace.ClikGoodInter;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.GoodInfo;
import com.yuepang.yuepang.model.MerchantInfo;
import com.yuepang.yuepang.protocol.GetGoodListProtocol;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xugh on 2019/4/2.
 */

public class GoodAdapter extends YueBaseAdapter<GoodInfo> implements AdapterView.OnItemClickListener, LoadCallBack<List<GoodInfo>> {

    private int areaId;
    ClikGoodInter interFace;
    private List<GoodInfo> allGoodIndos;
    private int shopSize;
    private int infoSize;

    public GoodAdapter(BaseActivity activity, ClikGoodInter interFace) {
        super(activity);
        this.interFace = interFace;
        allGoodIndos = new ArrayList<>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.good_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(getItem(position).getTitle());
        viewHolder.msg.setText(getItem(position).getMsg());
        ImageLoaderUtil.LoadImageViewForUrl(viewHolder.ivPic, getItem(position).getPicUrl());
        return convertView;
    }

    /**
     * 请求新的商品数据
     */
    public void getData(List<MerchantInfo> infos) {
        allGoodIndos.clear();// 清理老数据
        shopSize = infos.size();
        infoSize = 0;
        for (MerchantInfo info : infos) {
            new GetGoodListProtocol(activity, this, info.getId()).request();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        interFace.toGooddeatail(getItem(position));
    }

    @Override
    public void loadCallBack(final CallType callType, int CODE, String msg, final List<GoodInfo> infos) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (callType.equals(CallType.SUCCESS)) {
                    infoSize++;
                    for (GoodInfo info : infos) { // 遍历所有商品
                        for (int favorite : activity.getUserInfo().getFavorite()) {// 遍历当前用户的喜好
                            if (favorite == info.getFavorite()) { // 如果用户喜好和商品喜好相同添加商品到列表
                                allGoodIndos.add(info);
                            }
                        }
                    }
                    if (infoSize == shopSize) {
                        LogUtils.e("notifyDataSetChanged");
                        setList(allGoodIndos);
                        notifyDataSetChanged();
                    }
                }
            }
        });

    }

    private final class ViewHolder {
        @BindView(id = R.id.iv_good)
        ImageView ivPic;
        @BindViewByTag
        TextView name;
        @BindViewByTag
        TextView msg;

        public ViewHolder(View view) {
            AnnotateBindViewUtil.initBindView(this, view, null);
        }
    }
}
