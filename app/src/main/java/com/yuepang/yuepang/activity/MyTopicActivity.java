package com.yuepang.yuepang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.android.common.annotation.view.BindView;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.adapter.TopicAdapter;

/**
 * Created by xugh on 2019/4/2.
 */

public class MyTopicActivity extends BaseActivity {
    private View view;

    @BindView(id= R.id.com_lv)
    private ListView recordLv;

    private TopicAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        adapter = new TopicAdapter(this);
        super.onCreate(savedInstanceState);
        recordLv.setAdapter(adapter);
        recordLv.setOnItemClickListener(adapter);
        adapter.getData();
    }

    @Override
    public String getMyTittle() {
        return "我的话题";
    }

    @Override
    protected int getContentViewId() {
        return R.layout.common_list;
    }

    public static void toThisActivity(Context context) {
        Intent intent = new Intent(context, MyTopicActivity.class);
        context.startActivity(intent);
    }
}
