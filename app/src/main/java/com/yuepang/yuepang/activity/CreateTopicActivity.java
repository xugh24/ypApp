package com.yuepang.yuepang.activity;

import android.text.TextUtils;
import android.widget.EditText;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;

/**
 * Created by xugh on 2019/3/19.
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
        if (TextUtils.isEmpty(title)) {
            showToastSafe("标题为空");
        } else {
            finish();
        }
    }
}