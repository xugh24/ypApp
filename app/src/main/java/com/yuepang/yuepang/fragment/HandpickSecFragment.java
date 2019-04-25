package com.yuepang.yuepang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.common.annotation.view.OnClickView;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.adapter.AreaAdapter;
import com.yuepang.yuepang.adapter.GoodAdapter;
import com.yuepang.yuepang.interFace.AreaInterFace;
import com.yuepang.yuepang.interFace.CutAreaInterFace;
import com.yuepang.yuepang.model.AreaInfo;
import com.yuepang.yuepang.model.MerchantInfo;
import com.yuepang.yuepang.widget.AreaPopupWindow;

import java.util.List;

/**
 * 精选页面
 */

public class HandpickSecFragment extends BaseSecFragment implements AreaInterFace, CutAreaInterFace {

    @BindView(id = R.id.tv_merchant1)
    private TextView tvName1; // 商家1的名称

    @BindView(id = R.id.tv_merchant2)
    private TextView tvName2; // 商家2的名称

    @BindView(id = R.id.goodlist)
    private ListView goodLv;

    @BindView(id = R.id.iv_not)
    private ImageView ivNot;

    private GoodAdapter goodAdapter; // 商品

    private MerchantInfo info1;// 商家信息1

    private MerchantInfo info2;// 商家信息2

    private AreaPopupWindow areaPopupWindow; // 商家popvindow

    private AreaAdapter areaAdapter;// 切换商圈

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void refreshView() {
        ivNot.setVisibility(View.GONE);
        goodLv.setVisibility(View.VISIBLE);
    }

    /**
     * 获得数据
     */
    @Override
    protected boolean getData() {
        return goodAdapter.getData();
    }

    /**
     * 初始化方法
     */
    @Override
    protected void init() {
        // 新建 View
        areaPopupWindow = new AreaPopupWindow(getMainActivity());
        // 新建适配器
        goodAdapter = new GoodAdapter(getMainActivity(), this);
        goodLv.setAdapter(goodAdapter);
        goodLv.setOnItemClickListener(goodAdapter);
    }

    public void showAreaPop() {
        if (areaPopupWindow != null) {
            areaPopupWindow.show(getMainActivity().getBarTitle().getTvLeftTitle());
        }
    }


    @Override
    public int getLyId() {
        return R.layout.handpick_ly;
    }

    @OnClickView({R.id.merchant1_ly,R.id.merchant2_ly})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.merchant1_ly:// 商家推荐位1
           //     getMainActivity().toMerActivity(info1);

                break;
            case R.id.merchant2_ly:// 商家推荐位2
            //    getMainActivity().toMerActivity(info2);
                break;
        }
    }


    @Override
    public void callAreaInfo(List<AreaInfo> areaInfos, List<MerchantInfo> merchantInfos, final AreaInfo currentInfo) {
        areaAdapter = new AreaAdapter((BaseActivity) getActivity(), areaInfos, this);
        areaPopupWindow.setAdapter(areaAdapter);
        areaAdapter.notifyDataSetChanged();
        info1 = merchantInfos.get(0);
        info2 = merchantInfos.get(1);
        getMainActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvName1.setText(info1.getName());
                tvName2.setText(info2.getName());
                getMainActivity().getBarTitle().setTvLeftTitle(currentInfo.getName());
            }
        });
    }

    /**
     * 切换商圈
     */
    @Override
    public void cutAreaInfo(AreaInfo info) {
        getMainActivity().getBarTitle().setTvLeftTitle(info.getName());
        goodAdapter.refresh(info);
    }

}
