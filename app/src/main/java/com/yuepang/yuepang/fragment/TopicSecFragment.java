package com.yuepang.yuepang.fragment;

import android.view.View;
import android.widget.ListView;

import com.android.common.annotation.view.BindViewByTag;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.adapter.TopicAdapter;

/**
 *
 */

public class TopicSecFragment extends BaseSecFragment {

    @BindViewByTag
    private ListView listView;
    private TopicAdapter adapter;// 数据
    private boolean isFirstShow = true;

    @Override
    protected void initbeforeView() {
        adapter = new TopicAdapter(getMainActivity());
    }

    @Override
    protected void initafterView() {
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);
    }

    public void onShow() {
        if (adapter != null) {
            adapter.getData();
        }
    }

    @Override
    public int getLyId() {
        return R.layout.topic_ly;
    }

    @Override
    protected void initData() {
        adapter.getData();
    }

    @Override
    public void onClick(View v) {

    }
}
