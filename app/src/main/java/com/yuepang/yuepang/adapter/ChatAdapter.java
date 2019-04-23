package com.yuepang.yuepang.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.AnnotateUtil;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.model.TopicItemInfo;

import java.util.List;

/**
 * 话题聊天适配器实现实时通讯功能
 * 实现逻辑 1、用户在话题页面，程序开启心跳线程定时请求话题数据
 * 2、用户成功发送消息后，会立即请求数据（同事刷新心跳线程时间）
 * 3、方案选择，长连接的实时性更好但是开销大，本程序对实时要求不高，所以
 * 采用心跳方法来实现数据刷新
 */

public class ChatAdapter extends YueBaseAdapter<TopicItemInfo> {

    public ChatAdapter(BaseActivity activity, List<TopicItemInfo> list) {
        super(activity, list);
    }

    /**
     * 获得 item View
     */
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
        holder.name.setText(getItem(position).getName());
        holder.msg.setText(getItem(position).getMsg());
        return convertView;
    }


    /**
     * item View 类
     */
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
