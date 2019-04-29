package com.yuepang.yuepang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.adapter.TopicAdapter;

/**
 *
 */

public class TopicSecFragment extends BaseSecFragment {

    private ListView listView;
    private TopicAdapter adapter;// 数据
    private boolean isFirstShow = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initafterView() {

    }

    @Override
    protected void initbeforeView() {
        adapter = new TopicAdapter(null, getMainActivity());
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





    public void onShow() {
        setTitle("话题");
        setRightTitle("创建");
        setTvLeftTitle(null);
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
}
