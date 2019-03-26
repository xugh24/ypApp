package com.yuepang.yuepang.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.MerchantDetailActivity;
import com.yuepang.yuepang.adapter.AreaAdapter;
import com.yuepang.yuepang.test.TestData;

/**
 */

public class HandpickFragment extends BaseFragment {

    @BindView(id = R.id.merchant1_ly, click = true)
    private LinearLayout llmerchant1;

    @BindView(id = R.id.merchant2_ly, click = true)
    private LinearLayout llmerchant2;

    private PopupWindow pw;

    private ListView areaList;

    private View popRootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean getData() {
        return true;
    }


    @Override
    protected void initView() {
        popRootView = View.inflate(getActivity(), R.layout.common_list, null);
        areaList = popRootView.findViewById(R.id.com_lv);// 初始化商圈列
        AreaAdapter areaAdapter = new AreaAdapter((BaseActivity) getActivity(), TestData.getinfos());
        areaList.setAdapter(areaAdapter);
        ((BaseActivity) getActivity()).setTvLeftTitle("测试商家");
    }

    @Override
    public void show() {

    }


    public void showAreaPop() {
        initPopupWindow();
    }

    @Override
    public void hide() {

    }

    @Override
    public int getLyId() {
        return R.layout.handpick_ly;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.merchant1_ly:
                Intent intent1 = new Intent(getActivity(), MerchantDetailActivity.class);
                getActivity().startActivity(intent1);
                break;
            case R.id.merchant2_ly:
                Intent intent2 = new Intent(getActivity(), MerchantDetailActivity.class);
                getActivity().startActivity(intent2);
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void initPopupWindow() {
        int width = activity.getTvLeftTitle().getWidth() + 2;// 获得右边商圈文字的大小
        LogUtils.e("width " + width);
        pw = new PopupWindow(popRootView, width, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 设置pw如果点击外面区域，便关闭。
        pw.setOutsideTouchable(true);
        pw.setFocusable(true);
        pw.setOnDismissListener(new PopupWindow.OnDismissListener() {// popupwindow隐藏时回调
            @Override
            public void onDismiss() {

            }
        });
        pw.showAsDropDown(activity.getTvLeftTitle(), -1, 0);
        if (Build.VERSION.SDK_INT >= 11) {
            activity.getWindow().getDecorView().getRootView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    activity.getWindow().getDecorView().getRootView().removeOnLayoutChangeListener(this);
                    if (oldBottom < bottom) {
                        pw.dismiss();
                    }
                }
            });
        }
    }

}
