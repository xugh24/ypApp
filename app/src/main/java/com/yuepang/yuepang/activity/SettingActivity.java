package com.yuepang.yuepang.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;

/**
 * 设置页面
 */

public class SettingActivity extends BaseActivity {

    /**
     *
     */
    @BindView(id = R.id.tv_about, click = true)
    private TextView aboutly;

    @BindView(id = R.id.tv_feedback,click = true)
    private TextView feedBackly;

    @BindView(id = R.id.tv_out,click = true)
    private TextView outLogin;


    @Override
    protected String getMyRTitle() {
        return null;
    }

    @Override
    protected String getMyTittle() {
        return "设置";
    }

    @Override
    protected int getContentViewId() {
        return R.layout.merchant_manage_ly;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case  R.id.tv_about:

                break;
            case  R.id.tv_feedback:
            startActivity(FeedbackActivity.class);
                break;
            case  R.id.tv_out:

                break;
        }
    }
}
