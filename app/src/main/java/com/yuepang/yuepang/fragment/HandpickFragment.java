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
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.MerchantDetailActivity;
import com.yuepang.yuepang.adapter.AreaAdapter;
import com.yuepang.yuepang.adapter.GoodAdapter;
import com.yuepang.yuepang.interFace.AreaInterFace;
import com.yuepang.yuepang.interFace.CutAreaInterFace;
import com.yuepang.yuepang.model.AreaInfo;
import com.yuepang.yuepang.model.MerchantInfo;
import com.yuepang.yuepang.test.TestData;

import java.util.List;

/**
 */

public class HandpickFragment extends BaseFragment implements AreaInterFace, CutAreaInterFace {

    @BindView(id = R.id.merchant1_ly, click = true)
    private LinearLayout llmerchant1; // 商家推荐位

    @BindView(id = R.id.merchant2_ly, click = true)
    private LinearLayout llmerchant2;

    @BindView(id = R.id.tv_merchant1)
    private TextView tvName1;

    @BindView(id = R.id.tv_merchant2)
    private TextView tvName2;

    private PopupWindow pw;

    private ListView areaList;

    private View popRootView;

    private AreaAdapter areaAdapter;// 切换商圈

    private GoodAdapter goodAdapter;

    private MerchantInfo info1;

    private MerchantInfo info2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void refreshView() {
        ((BaseActivity) getActivity()).setTvLeftTitle("测试商家");
    }

    @Override
    protected boolean getData() {
        return goodAdapter.getData();
    }


    @Override
    protected void init() {
        popRootView = View.inflate(getActivity(), R.layout.common_list, null);
        areaList = popRootView.findViewById(R.id.com_lv);// 初始化商圈列
        goodAdapter = new GoodAdapter(getMainActivity(), this);
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
                intent1.putExtra(MerchantDetailActivity.MERCHANTINFO,info1);
                getActivity().startActivity(intent1);
                break;
            case R.id.merchant2_ly:
                Intent intent2 = new Intent(getActivity(), MerchantDetailActivity.class);
                intent2.putExtra(MerchantDetailActivity.MERCHANTINFO,info2);
                getActivity().startActivity(intent2);
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void initPopupWindow() {
        int width = getMainActivity().getTvLeftTitle().getWidth() + 2;// 获得右边商圈文字的大小
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
        pw.showAsDropDown(getMainActivity().getTvLeftTitle(), -1, 0);
        if (Build.VERSION.SDK_INT >= 11) {
            getMainActivity().getWindow().getDecorView().getRootView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    getMainActivity().getWindow().getDecorView().getRootView().removeOnLayoutChangeListener(this);
                    if (oldBottom < bottom) {
                        pw.dismiss();
                    }
                }
            });
        }
    }

    @Override
    public void callAreaInfo(List<AreaInfo> areaInfos, List<MerchantInfo> merchantInfos) {
        areaAdapter = new AreaAdapter((BaseActivity) getActivity(), TestData.getinfos(), this);
        areaList.setAdapter(areaAdapter);
        areaList.setOnItemClickListener(areaAdapter);
        info1 = merchantInfos.get(0);
        info2 = merchantInfos.get(1);
        getMainActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvName1.setText(info1.getName());
                tvName2.setText(info2.getName());
            }
        });
    }

    /**
     * 切换商圈
     */
    @Override
    public void cutAreaInfo(AreaInfo info) {
        getMainActivity().setTvLeftTitle(info.getName());
        goodAdapter.refresh(info);
    }
}
