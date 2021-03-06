package com.yuepang.yuepang.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yuepang.yuepang.R;

/**
 * Created by xugh on 2019/3/24.
 */

public class FeedbackActivity extends BaseActivity {

    private EditText edMsg;

    private Button btnSub;


    @Override
    public String getMyTittle() {
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
        if(TextUtils.isEmpty(msg)){
            showToastSafe("请输入您的反馈信息");
        }else{
            showToastSafe("反馈成功");
            finish();
        }
    }
}
