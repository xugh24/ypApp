package com.yuepang.yuepang.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.model.TopicInfo;
import com.yuepang.yuepang.model.TopicItemInfo;

import java.util.List;

/**
 * Created by xugh on 2019/3/19.
 */

public class TopicItemAdapter extends BaseAdapter {

    private List<TopicItemInfo> topicItemInfos;

    private BaseActivity baseActivity;

    public TopicItemAdapter(List<TopicItemInfo> topicItemInfos, BaseActivity baseActivity) {
        this.topicItemInfos = topicItemInfos;
        this.baseActivity = baseActivity;
    }


    public void setTopicItemInfos( List<TopicItemInfo> topicItemInfos){
        this.topicItemInfos = topicItemInfos;
    }



    @Override
    public int getCount() {
        return topicItemInfos.size();
    }

    @Override
    public TopicItemInfo getItem(int position) {
        return topicItemInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(baseActivity, R.layout.chat_item, null);
            holder = new ViewHolder();
            holder.ivHead = convertView.findViewById(R.id.iv_head);
            holder.name = convertView.findViewById(R.id.tv_name);
            holder.msg = convertView.findViewById(R.id.tv_msg);
            convertView.setTag(holder);
        } else if (convertView.getTag() instanceof ViewHolder) {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(topicItemInfos.get(position).getName());
        holder.msg.setText(topicItemInfos.get(position).getMsg());
        return convertView;
    }


    private final class ViewHolder {
        ImageView ivHead;
        TextView name;
        TextView msg;
    }
}
