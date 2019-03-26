package com.yuepang.yuepang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.adapter.ChatAdapter;
import com.yuepang.yuepang.async.CommonTaskExecutor;
import com.yuepang.yuepang.control.UserCentreControl;
import com.yuepang.yuepang.model.TopicItemInfo;
import com.yuepang.yuepang.presenter.ChatPresenter;
import com.yuepang.yuepang.protocol.GetChatProtocol;
import com.yuepang.yuepang.protocol.SendMsgProtocol;
import com.yuepang.yuepang.widget.RefreshListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TimerTask;

/**
 * 话题聊天页面
 */

public class ChatActivity extends BaseActivity {


    @BindView(id = R.id.btn_send_message, click = true)
    private Button btnSend;

    @BindView(id = R.id.et_message_content)
    private EditText edMsg;

    @BindView(id = R.id.chat_lv)
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
        super.onClick(v);
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
    protected void onDestroy() {
        super.onDestroy();
        chatPresenter.onDestroy();
    }
}
