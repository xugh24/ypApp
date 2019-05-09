package com.yuepang.yuepang.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import com.android.common.annotation.view.BindViewByTag;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.protocol.AddTopicProtocol;

/**
 * Completed
 * 创建话题页面
 */

public class CreateTopicActivity extends BaseActivity implements LoadCallBack {

    @BindViewByTag
    private EditText edTop;

    @Override
    public String getMyRTitle() {
        return "创建";
    }

    @Override
    public String getMyTittle() {
        return "创建话题";
    }

    @Override
    protected int getContentViewId() {
        return R.layout.createtopic_ly;
    }

    @Override
    public void clickRt() {
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
    private void creatTopic(String title) {
        new AddTopicProtocol(this,this, title).request();
    }

    public static void toThisActivity(Activity activity) {
        Intent intent = new Intent(activity, CreateTopicActivity.class);
        activity.startActivityForResult(intent,2);
    }

    @Override
    public void loadCallBack(CallType callType, int CODE, String msg, Object info) {
        if(callType.equals(CallType.SUCCESS)){// 接受创建回调
            if(CODE == 200){
                showToastSafe("创建成功");
                finish();
            }
        }
    }
}
