package com.yuepang.yuepang.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.model.TopicInfo;

import java.util.List;

/**
 * Created by xugh on 2019/3/18.
 */

public class TopicAdapter extends BaseAdapter {
    private List<TopicInfo> topicInfos;

    private BaseActivity baseActivity;

    public TopicAdapter(List<TopicInfo> topicInfos, BaseActivity baseActivity) {
        this.topicInfos = topicInfos;
        this.baseActivity = baseActivity;
    }


    @Override
    public int getCount() {
        return topicInfos.size();
    }

    @Override
    public TopicInfo getItem(int position) {
        return topicInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(baseActivity, R.layout.topic_item, null);
            holder = new ViewHolder();
            holder.head = convertView.findViewById(R.id.title_name);
            holder.title = convertView.findViewById(R.id.tv_title);
            holder.content = convertView.findViewById(R.id.tv_msg);
            holder.time = convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        } else if (convertView.getTag() instanceof ViewHolder) {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.head.setText(topicInfos.get(position).getHeader());
        holder.title.setText(topicInfos.get(position).getTitle());
        holder.content.setText(topicInfos.get(position).getContentSt());
        holder.time.setText(topicInfos.get(position).getTime());
        return convertView;
    }

    private final class ViewHolder {
        TextView head;
        TextView title;
        TextView content;
        TextView time;
    }
}
