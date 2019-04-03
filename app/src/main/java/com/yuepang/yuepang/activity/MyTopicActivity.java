package com.yuepang.yuepang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.adapter.TopicAdapter;

/**
 * Created by xugh on 2019/4/2.
 */

public class MyTopicActivity extends BaseLoadFrameActivity {
    private View view;

    private ListView recordLv;

    private TopicAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new TopicAdapter(null,this);
    }

    @Override
    protected View getMainView() {
        view = View.inflate(this, R.layout.common_list, null);
        recordLv = view.findViewById(R.id.com_lv);
        return view;
    }

    @Override
    protected boolean getdata() {
        return adapter.getData();
    }

    @Override
    protected String getMyRTitle() {
        return null;
    }

    @Override
    protected String getMyTittle() {
        return "我的话题";
    }
}
