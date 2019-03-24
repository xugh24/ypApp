package com.yuepang.yuepang.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;

/**
 * Created by xugh on 2019/3/24.
 */

public class FeedbackActivity extends BaseActivity {

    @BindView(id=R.id.feedback)
    private EditText edMsg;

    @BindView(id = R.id.btn_sub,click = true)
    private Button btnSub;

    @Override
    protected String getMyRTitle() {
        return null;
    }

    @Override
    protected String getMyTittle() {
        return "我的反馈";
    }

    @Override
    protected int getContentViewId() {
        return R.layout.feedback_ly;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if(v==btnSub){
            subFeedBack();
        }
    }

    private void subFeedBack() {
        String msg = edMsg.getText().toString();

    }
}
