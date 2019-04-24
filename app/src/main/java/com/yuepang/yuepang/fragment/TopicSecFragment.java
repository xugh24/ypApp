package com.yuepang.yuepang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.adapter.TopicAdapter;

/**
 */

public class TopicSecFragment extends BaseSecFragment {

    @BindView(id = R.id.top_lv)
    private ListView listView;
    private TopicAdapter adapter;// 数据
    private boolean isFirstShow = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void refreshView() {
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);
    }

    @Override
    protected boolean getData() {
        return adapter.getData();
    }

    @Override
    protected void init() {
        adapter = new TopicAdapter(null, getMainActivity());
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
}
