package com.yuepang.yuepang.adapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.AnnotateUtil;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.Util.SysUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.ChatActivity;
import com.yuepang.yuepang.model.TopicInfo;

import java.util.List;

/**
 */

public class TopicAdapter extends YueBaseAdapter implements AdapterView.OnItemClickListener {
    private List<TopicInfo> topicInfos;


    public TopicAdapter(List<TopicInfo> topicInfos, BaseActivity baseActivity) {
        super(baseActivity, topicInfos);
        this.topicInfos = topicInfos;
    }


    public void setTopicInfos(List<TopicInfo> topicInfos) {
        this.topicInfos = topicInfos;
        setList(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(activity, R.layout.topic_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else if (convertView.getTag() instanceof ViewHolder) {
            holder = (ViewHolder) convertView.getTag();
        }
        if (topicInfos.get(position).getTitle().length() > 0) {
            String head = String.valueOf(topicInfos.get(position).getTitle().charAt(0));
            holder.head.setText(head);
        }
        holder.title.setText(topicInfos.get(position).getTitle());
        holder.time.setText(SysUtils.stampToDate(topicInfos.get(position).getTime()));
        return convertView;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(activity, ChatActivity.class);
        intent.putExtra("id", topicInfos.get(position).getId());
        intent.putExtra("title", topicInfos.get(position).getTitle());
        activity.startActivity(intent);
    }

    private final class ViewHolder {
        @BindView(id = R.id.title_name)
        TextView head;

        @BindView(id = R.id.tv_title)
        TextView title;
        @BindView(id = R.id.time)
        TextView time;

        public ViewHolder(View view) {
            AnnotateUtil.initBindView(this, view);
        }
    }
}
