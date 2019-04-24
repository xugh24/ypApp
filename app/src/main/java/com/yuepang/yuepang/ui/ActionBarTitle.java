package com.yuepang.yuepang.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.common.activity.ActivityManage;
import com.android.common.annotation.view.AnnotateBindViewUtil;
import com.android.common.annotation.view.BindView;
import com.android.common.utils.LogUtils;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.activity.BaseActivity;

/**
 * Created by xugh on 2019/4/24.
 */

public class ActionBarTitle implements View.OnClickListener {


    @BindView(id = R.id.iv_back)
    private ImageView ivBack; // 返回按钮


    @BindView(id = R.id.tv_title)
    private TextView tvTitle; // 中间的title


    @BindView(id = R.id.tv_r_title)
    private TextView tvRtitle; // 右边的文字说明


    @BindView(id = R.id.tv_left_title)
    private TextView tvLeftTitle;// 左边的文字说明


    @BindView(id = R.id.iv_star)
    private ImageView ivStar;// 收藏星星页面使用


    private ActivityManage activityManage;

    private View barView;

    private BaseActivity activity;

    public ActionBarTitle(BaseActivity activity) {
        this.activity = activity;
        init();
    }

    private void init() {
        barView = View.inflate(activity, R.layout.bar_ly, null);
        AnnotateBindViewUtil.initBindView(this, barView, this);
        setRightTitle(activity.getMyRTitle());
        setTitle(activity.getMyTittle());
        setTvLeftTitle(activity.getLeftTitle());
    }


    public void setTvLeftTitle(String title) {
        setTitle(tvLeftTitle, title);
    }

    public void setTitle(String title) {
        setTitle(tvTitle, title);
    }

    public void setRightTitle(String title) {
        setTitle(tvRtitle, title);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                activity.finish();
                break;
            case R.id.tv_r_title:
                LogUtils.e("---");
                activity.clikRt();
                break;
            case R.id.iv_star:

                break;
            case R.id.tv_left_title:
                activity.clikLeftTv();
        }
    }

    public TextView getTvRtitle() {
        return tvRtitle;
    }


    public TextView getTvLeftTitle() {
        return tvLeftTitle;
    }

    private void setTitle(TextView tv, String text) {
        if (tv != null) {
            if (TextUtils.isEmpty(text)) {
                tv.setVisibility(View.GONE);
            } else {
                tv.setVisibility(View.VISIBLE);
                tv.setText(text);
            }
        }
    }
}
