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
import com.yuepang.yuepang.model.TopicInfo;
import com.yuepang.yuepang.protocol.GetTopicProtocol;
import com.yuepang.yuepang.view.TopicView;

import java.util.List;

/**
 */

public class TopicFragment extends BaseFragment {

    @BindView(id = R.id.top_lv)
    private ListView listView;
    private TopicView topicView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean getData() {
        return topicView.getData();
    }

    @Override
    protected void initView() {
        topicView = new TopicView((BaseActivity) getActivity(), listView);
    }

    @Override
    public void show() {
        topicView.show();
    }

    @Override
    public void hide() {
    }

    @Override
    public int getLyId() {
        return R.layout.topic_ly;
    }
}
