package com.yuepang.yuepang.fragment;

import android.support.v4.media.session.IMediaControllerCallback;
import android.view.View;
import android.widget.ImageView;

import com.android.common.annotation.view.BindView;
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

    @BindViewByTag
    private ImageView ivHead;

    @Override
    public void onShow() {// 刷新头像照片
        super.onShow();
        ImageLoaderUtil.LoadImageViewForUrl(ivHead, getMainActivity().getUserInfo().getHeaderImgUrl());
    }

    @Override
    public void onHide() {
        super.onHide();
    }



    @OnClickView({R.id.personal_ly, R.id.rl_uc_1, R.id.rl_uc_2,
            R.id.rl_uc_3, R.id.rl_uc_4, R.id.rl_uc_5})
    private String string;

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_ly:
                PersonageActivity.toThisActivity(getContext());
                break;
            case R.id.rl_uc_1:
                PayRecordActivity.toThisActivity(getContext());
                break;
            case R.id.rl_uc_2:

                break;
            case R.id.rl_uc_3:
                MyTopicActivity.toThisActivity(getContext());
                break;
            case R.id.rl_uc_4:
                MylikeActivity.toThisActivity(getContext());
                break;
            case R.id.rl_uc_5:
                SettingActivity.toThisActivity(getContext());

                break;
        }
    }

    @Override
    public int getLyId() {
        return R.layout.mine_ly;
    }

    @Override
    protected void refreshView() {// 刷新当前View

    }

    @Override
    protected boolean getData() {
        return true;
    }

    @Override
    protected void initafterView() {

    }

    @Override
    protected void initbeforeView() {

    }

}
