package com.yuepang.yuepang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.adapter.TopicItemAdapter;
import com.yuepang.yuepang.async.CommonTaskExecutor;
import com.yuepang.yuepang.control.UserCentreControl;
import com.yuepang.yuepang.model.TopicItemInfo;
import com.yuepang.yuepang.protocol.GetChatProtocol;
import com.yuepang.yuepang.protocol.SendMsgProtocol;
import com.yuepang.yuepang.widget.RefreshListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 话题聊天页面
 */

public class TopicDetailActivity extends BaseActivity implements RefreshListView.OnRefreshListener {

    @BindView(id = R.id.rl_main)
    private RelativeLayout rlMain;

    @BindView(id = R.id.btn_send_message, click = true)
    private Button btnSend;

    @BindView(id = R.id.et_message_content)
    private EditText edMsg;

    private RefreshListView refreshListView;

    private int id;

    private String title;

    private List<TopicItemInfo> topicItemInfos;

    private TopicItemAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getIntExtra("id", 0);
        title = getIntent().getStringExtra("title");
        TopicItemInfo info = new TopicItemInfo();
        topicItemInfos = new ArrayList<>();
        info.setMsg(title);
        info.setName("发起人");
        info.setId(id);
        topicItemInfos.add(info);
        refreshListView = new RefreshListView(this);
        refreshListView.setOnRefreshListener(this);
        adapter = new TopicItemAdapter(topicItemInfos, this);
        refreshListView.setAdapter(adapter);
        rlMain.addView(refreshListView);
        addInfo();
    }


    private void addInfo() {
        CommonTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                GetChatProtocol protocol = new GetChatProtocol(TopicDetailActivity.this, id);
                if (protocol.request() == 200) {
                    topicItemInfos.addAll((Collection<TopicItemInfo>) protocol.getData());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.setTopicItemInfos(topicItemInfos);
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == btnSend) {
            String msg = edMsg.getText().toString().trim();
            if (TextUtils.isEmpty(msg)) {
                showToastSafe("消息为空");
            } else {
                sendMsg(msg);
                TopicItemInfo info = new TopicItemInfo();
                info.setMsg(msg);
                info.setName(UserCentreControl.getInstance().getInfo().getName());
                topicItemInfos.add(info);
                adapter.setTopicItemInfos(topicItemInfos);
                adapter.notifyDataSetChanged();
                edMsg.setText("");
            }
        }
    }

    private void sendMsg(final String msg) {
        CommonTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                SendMsgProtocol protocol = new SendMsgProtocol(TopicDetailActivity.this, id, msg);
                if (protocol.request() == 200) {

                }
            }
        });

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

    @Override
    public void onPullRefresh() {

    }
}
