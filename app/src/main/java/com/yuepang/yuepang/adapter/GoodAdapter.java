package com.yuepang.yuepang.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.common.annotation.view.AnnotateBindViewUtil;
import com.android.common.annotation.view.BindViewByTag;
import com.android.common.async.ImageLoaderUtil;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.interFace.ClikGoodInter;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.GoodInfo;
import com.yuepang.yuepang.protocol.GetGoodListProtocol;

import java.util.List;

/**
 * Created by xugh on 2019/4/2.
 */

public class GoodAdapter extends YueBaseAdapter<GoodInfo> implements AdapterView.OnItemClickListener, LoadCallBack<List<GoodInfo>> {

    private int areaId;
    ClikGoodInter interFace;

    public GoodAdapter(BaseActivity activity, ClikGoodInter interFace) {
        super(activity);
        this.interFace = interFace;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(activity, R.layout.good_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        if (convertView.getTag() instanceof ViewHolder) {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(getItem(position).getTitle());
        viewHolder.msg.setText(getItem(position).getMsg());
        ImageLoaderUtil.LoadImageViewForUrl(viewHolder.ivPic, getItem(position).getPicUrl());
        return convertView;
    }

    /**
     * 请求数据
     */
    public void getData(int id) {
        GetGoodListProtocol protocol = new GetGoodListProtocol(activity, this, id);
        protocol.request();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        interFace.toGooddeatail(getItem(position));
    }

    @Override
    public void loadCallBack(final CallType callType, int CODE, String msg,final List<GoodInfo> infos) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(callType.equals(CallType.SUCCESS)){
                    setList(infos);
                    notifyDataSetChanged();
                }
            }
        });

    }

    private final class ViewHolder {
        @BindViewByTag
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
