package com.yuepang.yuepang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.android.common.annotation.view.BindViewByTag;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.presenter.ChatPresenter;

/**
 * 话题聊天页面
 */

public class ChatActivity extends BaseActivity {

    @BindViewByTag(click = true)
    private Button btnSend;
    @BindViewByTag
    private EditText edMsg;
    @BindViewByTag
    private ListView chatListView;

    private ChatPresenter chatPresenter;// 消息逻辑处理类

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatPresenter = new ChatPresenter(this);
        chatPresenter.getNewChatInfo();// 获得新消息
    }

    @Override
    public void onClick(View v) {
        if (v == btnSend) {
            chatPresenter.sendMsg();
        }
    }

    public ListView getChatListView() {
        return chatListView;
    }

    public String getMsg() {
        return edMsg.getText().toString();
    }

    public EditText getEdMsg() {
        return edMsg;
    }

    @Override
    public String getMyTittle() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.chat_log_layout;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        chatPresenter.onDestroy();
    }
}
