package com.yuepang.yuepang.adapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.SysUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.TopicDetailActivity;
import com.yuepang.yuepang.model.TopicInfo;

import java.util.List;

/**
 */

public class TopicAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    private List<TopicInfo> topicInfos;

    private BaseActivity baseActivity;

    public TopicAdapter(List<TopicInfo> topicInfos, BaseActivity baseActivity) {
        this.topicInfos = topicInfos;
        this.baseActivity = baseActivity;
    }


    public void setTopicInfos(List<TopicInfo> topicInfos) {
        this.topicInfos = topicInfos;
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
        if (topicInfos.get(position).getTitle().length() > 0) {
            String head = String.valueOf(topicInfos.get(position).getTitle().charAt(0));
            holder.head.setText(head);
        }
        holder.title.setText(topicInfos.get(position).getTitle());
        holder.content.setText(topicInfos.get(position).getContentSt());
        holder.time.setText(SysUtils.stampToDate(topicInfos.get(position).getTime()));
        return convertView;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(baseActivity, TopicDetailActivity.class);
        intent.putExtra("id", topicInfos.get(position).getId());
        intent.putExtra("title", topicInfos.get(position).getTitle());
        baseActivity.startActivity(intent);
    }

    private final class ViewHolder {
        TextView head;
        TextView title;
        TextView content;
        TextView time;
    }
}
