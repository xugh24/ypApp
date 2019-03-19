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
import com.yuepang.yuepang.test.TestData;

import java.util.List;

/**
 * Created by xugh on 2019/3/10.
 */

public class TopicFragment extends BaseFragment {

    @BindView(id = R.id.top_lv)
    private ListView listView;
    private TopicAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (contentView == null) {
            contentView = inflater.inflate(getLyId(), container, false);
            AnnotateUtil.initBindView(this, contentView);
        }
        adapter = new TopicAdapter(TestData.gettops(), (BaseActivity) getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);
        return contentView;

    }

    @Override
    public int getLyId() {
        return R.layout.topic_ly;
    }
}
