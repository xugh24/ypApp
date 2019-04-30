package com.yuepang.yuepang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.android.common.annotation.view.BindViewByTag;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.adapter.TopicAdapter;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.TopicInfo;

/**
 *
 */

public class TopicSecFragment extends BaseSecFragment implements LoadCallBack<TopicInfo> {

    @BindViewByTag
    private ListView listView;
    private TopicAdapter adapter;// 数据
    private boolean isFirstShow = true;


    @Override
    protected void initbeforeView() {

    }

    @Override
    protected void initafterView() {
        adapter = new TopicAdapter(getMainActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);
    }

    @Override
    protected void initData() {
        adapter.getData();
    }


    public void onShow() {
        if (!isFirstShow && adapter.getData()) {
            getMainActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (adapter != null) {
                        adapter.notifyDataSetInvalidated();
                    }
                }
            });
        }
        isFirstShow = false;
    }

    @Override
    public int getLyId() {
        return R.layout.topic_ly;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void loadCallBack(CallType callType, int CODE, String msg, TopicInfo info) {
        listView.setAdapter(adapter);
    }
}
