package com.yuepang.yuepang.adapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.android.common.annotation.view.AnnotateBindViewUtil;
import com.android.common.annotation.view.BindViewByTag;
import com.android.common.utils.LogUtils;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.SysUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.ChatActivity;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.TopicInfo;
import com.yuepang.yuepang.protocol.GetTopicProtocol;

import java.util.List;


/**
 *
 */

public class TopicAdapter extends YueBaseAdapter<TopicInfo> implements AdapterView.OnItemClickListener, LoadCallBack {

    public TopicAdapter(BaseActivity baseActivity) {
        super(baseActivity);
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
        if (getItem(position).getTitle().length() > 0) {
            String head = String.valueOf(getItem(position).getTitle().charAt(0));
            holder.head.setText(head);
        }
        holder.title.setText(getItem(position).getTitle());
        holder.time.setText(SysUtils.stampToDate(getItem(position).getTime()));
        return convertView;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(activity, ChatActivity.class);
        intent.putExtra("id", getItem(position).getId());
        intent.putExtra("title", getItem(position).getTitle());
        activity.startActivity(intent);
    }


    @Override
    public void loadCallBack(final CallType callType, final int CODE, final String msg, final Object info) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (callType) {
                    case SUCCESS:
                        List<TopicInfo> list = (List<TopicInfo>) info;
                        LogUtils.e("list  " + list.size());
                        setList(list);
                        notifyDataSetInvalidated();
                        break;
                    case START:
                       activity.showLoadingDialogSafe(false);
                        break;
                    case FINISH:
                        activity.dismissLoadingDialogSafe();
                        break;
                }

            }
        });


    }

    private final class ViewHolder {
        @BindViewByTag
        TextView head;
        @BindViewByTag
        TextView title;
        @BindViewByTag
        TextView time;

        public ViewHolder(View view) {
            AnnotateBindViewUtil.initBindView(this, view, null);
        }
    }

    public boolean getData() {
        GetTopicProtocol protocol = new GetTopicProtocol(activity, this);
        protocol.request();
        return true;
    }
}
