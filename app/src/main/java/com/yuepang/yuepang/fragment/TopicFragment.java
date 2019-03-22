package com.yuepang.yuepang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.AnnotateUtil;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.adapter.TopicAdapter;
import com.yuepang.yuepang.model.TopicInfo;
import com.yuepang.yuepang.protocol.GetTopicProtocol;
import com.yuepang.yuepang.test.TestData;

import java.util.List;

/**
 * Created by xugh on 2019/3/10.
 */

public class TopicFragment extends BaseFragment {

    @BindView(id = R.id.top_lv)
    private ListView listView;
    private TopicAdapter adapter;
    private List<TopicInfo> topicInfos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean getData() {
        GetTopicProtocol protocol = new GetTopicProtocol((BaseActivity) getActivity());
        if (protocol.request() == 200) {
            return true;
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void initView() {
        adapter = new TopicAdapter(TestData.gettops(), (BaseActivity) getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);
    }

    @Override
    public int getLyId() {
        return R.layout.topic_ly;
    }
}
