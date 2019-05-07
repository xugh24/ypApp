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
import com.yuepang.yuepang.interFace.CutAreaInterFace;
import com.yuepang.yuepang.model.AreaInfo;

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

    private BaseActivity activity;// 传入的activity

    private View popRootView;

    private ListView areaList;

    private AreaAdapter areaAdapter;// 切换商圈

    private CutAreaInterFace interFace;

    public AreaPopupWindow(final BaseActivity activity, CutAreaInterFace interFace) {
        this.activity = activity;
        this.interFace = interFace;
        setHeight(WRAP_CONTENT);
        initView();
        setContentView(popRootView);
    }

    /**
     * 展示商圈popWindow
     */
    public void show(View referenceView) {
        setWidth(referenceView.getWidth() + 2);
        showAsDropDown(referenceView, -1, 0);
    }

    private void initView() {// 初始化View
        popRootView = View.inflate(activity, R.layout.listview, null);
        areaList = popRootView.findViewById(R.id.com_lv);// 初始化商圈列
        areaAdapter = new AreaAdapter(activity, interFace);
        areaList.setAdapter(areaAdapter);
        areaList.setOnItemClickListener(areaAdapter);
        areaAdapter.getData();// 获得数据
    }

    public AreaInfo getAreaInfoById(int id) {
        if (areaAdapter.getList() != null && areaAdapter.getList().size() > 0) {
            for (AreaInfo info : areaAdapter.getList()) {
                if (id == info.getId()) {
                    return info;
                }
            }
        }
        return null;
    }
}
