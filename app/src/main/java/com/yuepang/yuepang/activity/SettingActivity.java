package com.yuepang.yuepang.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.android.common.annotation.view.OnClickView;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.control.UserCentreControl;

/**
 * 设置页面
 */

public class SettingActivity extends BaseActivity {


    @Override
    public String getMyTittle() {
        return "设置";
    }

    @Override
    protected int getContentViewId() {
        return R.layout.merchant_manage_ly;
    }

    @OnClickView({R.id.tv_about,R.id.tv_feedback,R.id.tv_out})
    private String string;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_about:

                break;
            case R.id.tv_feedback:
                startActivity(FeedbackActivity.class);
                break;
            case R.id.tv_out:
                UserCentreControl.getInstance().outLogin(this);
                break;
        }
    }

    public static void toThisActivity(Context context) {
        Intent intent5 = new Intent(context, SettingActivity.class);
        context.startActivity(intent5);
    }
}
