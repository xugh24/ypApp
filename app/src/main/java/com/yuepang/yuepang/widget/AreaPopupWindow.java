package com.yuepang.yuepang.widget;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.adapter.AreaAdapter;
import com.yuepang.yuepang.adapter.GoodAdapter;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * 精选页面和商家页面的商圈选择View
 * 使用Android PopupWindow类实现
 * PopupWindow这个类用来实现一个弹出框，
 * 可以使用任意布局的View作为其内容，
 * 这个弹出框是悬浮在当前activity之上的。
 * PopupWindow在安卓中至关重要，涉及到的多种android知识，
 * 包括事件的分发，事件的消费，以及动画、最基础的布局
 * 可以android中的一个碎片。
 */

public class AreaPopupWindow extends PopupWindow {


    private BaseActivity activity;

    private View popRootView;

    private ListView areaList;

    public AreaPopupWindow(final BaseActivity activity) {
        this.activity = activity;
        setHeight(WRAP_CONTENT);
        initView();
        setContentView(popRootView);
        setOutsideTouchable(true);
        setFocusable(true);
        if (Build.VERSION.SDK_INT >= 11) {
            activity.getWindow().getDecorView().getRootView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    activity.getWindow().getDecorView().getRootView().removeOnLayoutChangeListener(this);
                    if (oldBottom < bottom) {
                        dismiss();
                    }
                }
            });
        }
    }

    public void show(View referenceView) {
        setWidth(referenceView.getWidth() + 2);
        showAsDropDown(referenceView, -1, 0);
    }

    private void initView() {
        popRootView = View.inflate(activity, R.layout.common_list, null);
        areaList = popRootView.findViewById(R.id.com_lv);// 初始化商圈列
    }

    public void setAdapter(AreaAdapter areaAdapter) {
        areaList.setAdapter(areaAdapter);
        areaList.setOnItemClickListener(areaAdapter);
    }
}
