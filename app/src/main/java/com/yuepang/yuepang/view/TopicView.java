package com.yuepang.yuepang.view;

import android.widget.ListView;

import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.adapter.TopicAdapter;
import com.yuepang.yuepang.async.CommonTaskExecutor;
import com.yuepang.yuepang.model.TopicInfo;
import com.yuepang.yuepang.protocol.GetTopicProtocol;

import java.util.List;

/**
 * Created by xugh on 2019/4/2.
 */

public class TopicView extends BaseView {
    private final TopicAdapter adapter;
    private ListView listView;
    private List<TopicInfo> topicInfos;
    private boolean isFirstShow = true;


    public TopicView(BaseActivity activity, ListView listView) {
        super(activity);
        this.listView = listView;
        adapter = new TopicAdapter(topicInfos, activity);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);
    }

    public boolean getData() {
        GetTopicProtocol protocol = new GetTopicProtocol(activity);
        if (protocol.request() == 200) {
            topicInfos = (List<TopicInfo>) protocol.getData();
            if (topicInfos.size() > 0) {
                return true;
            }
        }
        return false;
    }



    public void show() {
        if (!isFirstShow) {
            CommonTaskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    GetTopicProtocol protocol = new GetTopicProtocol(activity);
                    if (protocol.request() == 200) {
                        topicInfos = (List<TopicInfo>) protocol.getData();
                        if (topicInfos.size() > 0) {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (adapter != null) {
                                        adapter.setTopicInfos(topicInfos);
                                        adapter.notifyDataSetInvalidated();
                                    }
                                }
                            });

                        }
                    }
                }
            });
        }
        isFirstShow = false;
    }
}
