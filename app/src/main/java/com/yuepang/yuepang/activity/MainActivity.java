package com.yuepang.yuepang.activity;

import android.content.Intent;
import android.view.View;

import com.android.common.inter.ViewHolderClick;
import com.yuepang.yuepang.dialog.OutDialog;
import com.yuepang.yuepang.widget.MainViewPage;

/**
 * 主页面用于加载 精选、商家、话题、我的 功能页
 */

public class MainActivity extends BaseActivity implements ViewHolderClick {

    private MainViewPage mainViewPage; // 主view

    @Override
    protected void initbefore() {
        mainViewPage = new MainViewPage(this, this);
    }

    @Override
    public void clickLeftTv() {
        mainViewPage.onClikLeft();//点击左边的文字
    }

    @Override
    public void clickRt() {// 右上角点击
        mainViewPage.onClikRight();
    }

    @Override
    protected int getContentViewId() {
        return -1;
    }

    @Override
    public View getContentView() {
        return mainViewPage;
    }

    @Override
    public void onClick(View v, int i) {
        mainViewPage.setCurrentItem(i);// 点击主页面底下，跳转到对应的frame
    }

    @Override
    public void onResult(int requestCode, int resultCode, Intent data) {// 创建消息成功后刷新当前消息
        if (requestCode == 2) {
            mainViewPage.refreshTop();
        }
    }

    @Override
    public String getMyRTitle() {
        return null;
    }

    @Override
    public String getMyTittle() {
        return null;
    }

    @Override
    protected boolean isShowBack() {
        return false;
    }

    @Override
    public void onBackPressed() {
        new OutDialog(this).show();
    }
}
