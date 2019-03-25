package com.yuepang.yuepang.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.AnnotateUtil;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.model.TopicItemInfo;

import java.util.List;

/**
 */

public class TopicItemAdapter extends YueBaseAdapter {

    private List<TopicItemInfo> topicItemInfos;

    public TopicItemAdapter(List<TopicItemInfo> topicItemInfos, BaseActivity baseActivity) {
        super(baseActivity, topicItemInfos);
        this.topicItemInfos = topicItemInfos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(activity, R.layout.chat_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else if (convertView.getTag() instanceof ViewHolder) {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(topicItemInfos.get(position).getName());
        holder.msg.setText(topicItemInfos.get(position).getMsg());
        return convertView;
    }

    public void setTopicItemInfos(List<TopicItemInfo> topicItemInfos) {
        this.topicItemInfos = topicItemInfos;
        setList(topicItemInfos);
    }

    private final class ViewHolder {
        @BindView(id = R.id.iv_head)
        ImageView ivHead;
        @BindView(id = R.id.tv_name)
        TextView name;
        @BindView(id = R.id.tv_msg)
        TextView msg;

        public ViewHolder(View view) {
            AnnotateUtil.initBindView(this, view);
        }
    }
}
