package com.yuepang.yuepang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.widget.RefreshListView;

/**
 * Created by xugh on 2019/3/18.
 */

public class TopicDetailActivity extends BaseActivity {

    @BindView(id = R.id.rl_main)
    private RelativeLayout rlMain;

    private RefreshListView refreshListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refreshListView = new RefreshListView(this);
        refreshListView.setOnClickListener(this);
    }

    @Override
    protected String getMyRTitle() {
        return null;
    }

    @Override
    protected String getMyTittle() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.chat_log_layout;
    }
}
