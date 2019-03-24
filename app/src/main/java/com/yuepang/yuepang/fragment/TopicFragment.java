package com.yuepang.yuepang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.adapter.TopicAdapter;
import com.yuepang.yuepang.async.CommonTaskExecutor;
import com.yuepang.yuepang.info.TopicInfo;
import com.yuepang.yuepang.protocol.GetTopicProtocol;

import java.util.List;

/**
 */

public class TopicFragment extends BaseFragment {

    @BindView(id = R.id.top_lv)
    private ListView listView;
    private TopicAdapter adapter;
    private List<TopicInfo> topicInfos;
    private boolean isFirstShow = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean getData() {
        GetTopicProtocol protocol = new GetTopicProtocol((BaseActivity) getActivity());
        if (protocol.request() == 200) {
            topicInfos = (List<TopicInfo>) protocol.getData();
            if (topicInfos.size() > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.e("TopicFragment  + onResume");
    }

    @Override
    protected void initView() {
        adapter = new TopicAdapter(topicInfos, (BaseActivity) getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);
    }

    @Override
    public void show() {
        if (!isFirstShow) {
            CommonTaskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    GetTopicProtocol protocol = new GetTopicProtocol((BaseActivity) getActivity());
                    if (protocol.request() == 200) {
                        topicInfos = (List<TopicInfo>) protocol.getData();
                        if (topicInfos.size() > 0) {
                            getActivity().runOnUiThread(new Runnable() {
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

    @Override
    public void hide() {

    }

    @Override
    public int getLyId() {
        return R.layout.topic_ly;
    }
}
