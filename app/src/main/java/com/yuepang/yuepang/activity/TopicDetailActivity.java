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
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.adapter.TopicItemAdapter;
import com.yuepang.yuepang.async.CommonTaskExecutor;
import com.yuepang.yuepang.protocol.GetChatProtocol;
import com.yuepang.yuepang.protocol.SendMsgProtocol;
import com.yuepang.yuepang.test.TestData;
import com.yuepang.yuepang.widget.RefreshListView;

/**
 * Created by xugh on 2019/3/18.
 */

public class TopicDetailActivity extends BaseActivity {

    @BindView(id = R.id.rl_main)
    private RelativeLayout rlMain;

    @BindView(id = R.id.btn_send_message, click = true)
    private Button btnSend;

    @BindView(id = R.id.et_message_content)
    private EditText edMsg;

    private RefreshListView refreshListView;

    private int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getIntExtra("id", 0);
        LogUtils.e("id " + id);
        refreshListView = new RefreshListView(this);
        TopicItemAdapter adapter = new TopicItemAdapter(TestData.gettopicItem(id), this);
        refreshListView.setAdapter(adapter);
        rlMain.addView(refreshListView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CommonTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                GetChatProtocol protocol = new GetChatProtocol(TopicDetailActivity.this, id);
                if (protocol.request() == 0) {

                }
            }
        });
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
}
