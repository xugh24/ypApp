package com.yuepang.yuepang.activity;

import android.view.View;
import android.widget.ListView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.view.TopicView;

/**
 * Created by xugh on 2019/4/2.
 */

public class MyTopicActivity extends BaseLoadFrameActivity {
    private View view;

    private ListView recordLv;

    private TopicView topicView;

    @Override
    protected View getMainView() {
        view = View.inflate(this, R.layout.common_list, null);
        recordLv = view.findViewById(R.id.com_lv);
        topicView = new TopicView(this,recordLv);
        return view;
    }

    @Override
    protected boolean getdata() {
        return topicView.getData();
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
