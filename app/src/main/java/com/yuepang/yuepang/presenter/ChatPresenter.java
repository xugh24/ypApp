package com.yuepang.yuepang.presenter;

import android.text.TextUtils;

import com.yuepang.yuepang.activity.ChatActivity;
import com.yuepang.yuepang.adapter.ChatAdapter;
import com.yuepang.yuepang.async.CommonTaskExecutor;
import com.yuepang.yuepang.model.TopicItemInfo;
import com.yuepang.yuepang.protocol.GetChatProtocol;
import com.yuepang.yuepang.protocol.SendMsgProtocol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by xugh on 2019/3/26.
 * <p>
 * 消息界面的逻辑处理类
 */

public class ChatPresenter {

    private final static long periodTime = 5 * 1000;// 间隔时间毫秒

    /**
     * 获得消息的标志位
     */
    private boolean getMsgState = true;

    private ChatActivity activity;

    private int topicId; // 话题Id

    private String title;// 话题标题

    private List<TopicItemInfo> topicItemInfos;// 话题数据

    private List<TopicItemInfo> tempInfos;// 临时数据

    private TopicItemInfo firstinfo;// 发起人数据

    private ChatAdapter adapter;

    private Timer timer;

    public ChatPresenter(ChatActivity activity) {
        this.activity = activity;
        topicId = activity.getIntent().getIntExtra("id", 0);
        title = activity.getIntent().getStringExtra("title");
        firstinfo = new TopicItemInfo();
        topicItemInfos = new ArrayList<>();
        tempInfos = new ArrayList<>();
        firstinfo.setMsg(title);
        firstinfo.setName("发起人");
        topicItemInfos.add(firstinfo);
        adapter = new ChatAdapter(activity,topicItemInfos );
        activity.getChatListView().setAdapter(adapter);
        timer = new Timer(true);
        timer.schedule(task, periodTime, periodTime);// 启动心跳线程定时刷新数据
        activity.setTitle(title);
    }

    public void sendMsg() {
        final String msg = activity.getMsg();
        if (TextUtils.isEmpty(msg)) {
            activity.showToastSafe("消息为空");
        } else {
            CommonTaskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    SendMsgProtocol protocol = new SendMsgProtocol(activity, topicId, msg);
                    if (protocol.request() == 200) {// 发送成功获取新数据
                        getNewChatInfo();
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                activity.getEdMsg().setText("");// 发送消息成功后,置空消息输入框
                            }
                        });
                    }
                }
            });
        }
    }


    /**
     * 获得新的聊天数据并刷新，通过 synchronized 机制保证线程同步
     */
    public synchronized void getNewChatInfo() {
//        CommonTaskExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                GetChatProtocol protocol = new GetChatProtocol(activity, topicId);
//                if (protocol.request() == 200) {
//                    if (tempInfos.size() != ((Collection<TopicItemInfo>) protocol.getData()).size()) {// 如果本地临时数据和线上数据数量不一致，则刷新本地数据
//                        tempInfos.clear();
//                        tempInfos.addAll(((Collection<TopicItemInfo>) protocol.getData()));
//                        topicItemInfos.clear();
//                        topicItemInfos.add(firstinfo);
//                        topicItemInfos.addAll(tempInfos);
//                        activity.runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {// 更新消息数据后刷新UI
//                                adapter.setList(topicItemInfos);
//                                adapter.notifyDataSetChanged();
//                            }
//                        });
//                        getMsgState = false;// 获得消息标志位设置为false；
//                    }
//                }
//            }
//        });
    }

    /**
     * 获得心跳定时器
     */
    TimerTask task = new TimerTask() {

        public void run() {
            if (getMsgState) {// 如果获得消息标志位为true 则重新获得消息
                getNewChatInfo();
            }
            getMsgState = true;// 设置获得消息标志位为true，保证心进程可以正常运行
        }
    };

    /**
     * 销毁心跳进程
     */
    public void onDestroy() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
