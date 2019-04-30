package com.yuepang.yuepang.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.common.annotation.view.BindViewByTag;
import com.android.common.annotation.view.OnClickView;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.MainActivity;
import com.yuepang.yuepang.activity.MerchantDetailActivity;
import com.yuepang.yuepang.adapter.AreaAdapter;
import com.yuepang.yuepang.adapter.GoodAdapter;
import com.yuepang.yuepang.interFace.AreaInterFace;
import com.yuepang.yuepang.interFace.CutAreaInterFace;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.AreaInfo;
import com.yuepang.yuepang.model.MerchantInfo;
import com.yuepang.yuepang.protocol.GetBusinessAreaProtocol;
import com.yuepang.yuepang.widget.AreaPopupWindow;

import java.util.List;

/**
 * 精选页面
 */

public class HandpickSecFragment extends BaseSecFragment implements AreaInterFace, CutAreaInterFace, LoadCallBack {


    @BindViewByTag
    private TextView tvName1; // 商家1的名称

    @BindViewByTag
    private TextView tvName2; // 商家2的名称

    @BindViewByTag
    private ListView goodLv;

    @BindViewByTag
    private ImageView ivNot;

    private GoodAdapter goodAdapter; // 商品

    private MerchantInfo info1;// 商家信息1

    private MerchantInfo info2;// 商家信息2

    private AreaPopupWindow areaPopupWindow; // 商家popvindow

    private AreaAdapter areaAdapter;// 切换商圈


    /**
     * View 创建前的初始化
     */
    @Override
    protected void initafterView() {
        goodLv.setAdapter(goodAdapter);
        goodLv.setOnItemClickListener(goodAdapter);
    }

    /**
     * View创建后的初始化
     */
    @Override
    protected void initbeforeView() {
        areaPopupWindow = new AreaPopupWindow(getMainActivity()); // 新建需要加载的商圈View
        goodAdapter = new GoodAdapter(getMainActivity(), this);  // 新建适配器
        GetBusinessAreaProtocol protocol = new GetBusinessAreaProtocol(getMainActivity(), this);
        protocol.request();
    }


    @Override
    protected void initData() {
        ivNot.setVisibility(View.GONE);
        goodLv.setVisibility(View.VISIBLE);
    }



    @Override
    public int getLyId() {
        return R.layout.handpick_ly;
    }

    @OnClickView({R.id.merchant1_ly, R.id.merchant2_ly})
    private String string;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.merchant1_ly:// 商家推荐位1
                MerchantDetailActivity.toMerActivity(getContext(), info1);
                break;
            case R.id.merchant2_ly:// 商家推荐位2
                MerchantDetailActivity.toMerActivity(getContext(), info2);
                break;
        }
    }


    @Override
    public void callAreaInfo(List<AreaInfo> areaInfos, List<MerchantInfo> merchantInfos, final AreaInfo currentInfo) {
        areaAdapter = new AreaAdapter(getMainActivity(), areaInfos, this);
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

    @Override
    public void onShow() {
        super.onShow();
    }

    @Override
    public void onHide() {
        super.onHide();
    }

    @Override
    public void onClikLeft() {
        if (areaPopupWindow != null) {
            areaPopupWindow.show(getMainActivity().getBarTitle().getTvLeftTitle());
        }
    }

    /**
     * 切换商圈
     */
    @Override
    public void cutAreaInfo(AreaInfo info) {
        getMainActivity().getBarTitle().setTvLeftTitle(info.getName());
        goodAdapter.refresh(info);
    }

    @Override
    public void loadCallBack(CallType callType, int CODE, String msg, Object info) {

    }
}
