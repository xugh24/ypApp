package com.yuepang.yuepang.activity;

import android.text.TextUtils;
import android.widget.EditText;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.async.CommonTaskExecutor;
import com.yuepang.yuepang.protocol.AddTopicProtocol;

/**
 * Completed
 * 创建话题页面
 */

public class CreateTopicActivity extends BaseActivity {

    @BindView(id = R.id.ed_topic)
    private EditText edTop;

    @Override
    protected String getMyRTitle() {
        return "创建";
    }

    @Override
    protected String getMyTittle() {
        return "创建话题";
    }

    @Override
    protected int getContentViewId() {
        return R.layout.createtopic_ly;
    }

    @Override
    public void clikRt() {
        String title = edTop.getText().toString();
        if (TextUtils.isEmpty(title)) {  // 判断输入的话题是不是为null
            showToastSafe("标题为空");
        } else {
            creatTopic(title);
        }
    }

    /**
     * 创建话题
     */
    private void creatTopic(final String title) {
        CommonTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                AddTopicProtocol protocol = new AddTopicProtocol(CreateTopicActivity.this, title);
                if (protocol.request() == 200) { // 创建成功后 关闭页面
                    showToastSafe("创建成功");
                    finish();
                } else {
                    if (!TextUtils.isEmpty(protocol.getCodeDesc())) { // 如果服务端返回错误原因，则抛出
                        showToastSafe(protocol.getCodeDesc());
                    }
                }
            }
        });

    }
}
