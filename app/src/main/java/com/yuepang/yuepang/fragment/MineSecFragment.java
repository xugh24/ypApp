package com.yuepang.yuepang.fragment;

import android.view.View;
import android.widget.ImageView;
import com.android.common.annotation.view.BindViewByTag;
import com.android.common.annotation.view.OnClickView;
import com.android.common.async.ImageLoaderUtil;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.activity.MyTopicActivity;
import com.yuepang.yuepang.activity.MylikeActivity;
import com.yuepang.yuepang.activity.PayRecordActivity;
import com.yuepang.yuepang.activity.PersonageActivity;
import com.yuepang.yuepang.activity.SettingActivity;

/**
 */

public class MineSecFragment extends BaseSecFragment {

    @BindViewByTag(click = true)
    private ImageView ivHead;// 头像

    @OnClickView({R.id.personal_ly, R.id.rl_uc_1, R.id.rl_uc_2,// 注册
            R.id.rl_uc_3, R.id.rl_uc_4, R.id.rl_uc_5})
    private String string;

    @Override
    public void onShow() {// 刷新头像照片
        super.onShow();
        ImageLoaderUtil.LoadImageViewForUrl(ivHead, getMainActivity().getUserInfo().getHeaderImgUrl());
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_ly:// 进入个人资料页面
                PersonageActivity.toThisActivity(getContext());
                break;
            case R.id.rl_uc_1:// 进入个支付订单页面
                PayRecordActivity.toThisActivity(getContext());
                break;
            case R.id.rl_uc_2:// 进入我的收藏页面

                break;
            case R.id.rl_uc_3:// 进入话题页面
                MyTopicActivity.toThisActivity(getContext());
                break;
            case R.id.rl_uc_4: // 进入我的喜好页面
                MylikeActivity.toThisActivity(getContext());
                break;
            case R.id.rl_uc_5: // 进入设置页面
                SettingActivity.toThisActivity(getContext());
                break;
        }
    }

    @Override// 返回布局文件
    public int getLyId() {
        return R.layout.mine_ly;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initafterView() {
    }

    @Override
    protected void initbeforeView() {
    }

}
