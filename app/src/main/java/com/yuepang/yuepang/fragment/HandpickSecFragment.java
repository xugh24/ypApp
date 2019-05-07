package com.yuepang.yuepang.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.common.annotation.view.BindViewByTag;
import com.android.common.annotation.view.OnClickView;
import com.android.common.async.ImageLoaderUtil;
import com.android.common.utils.LogUtils;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.activity.GoodDetailActivity;
import com.yuepang.yuepang.activity.MerchantDetailActivity;
import com.yuepang.yuepang.adapter.GoodAdapter;
import com.yuepang.yuepang.interFace.ClikGoodInter;
import com.yuepang.yuepang.interFace.CutAreaInterFace;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.AreaInfo;
import com.yuepang.yuepang.model.GoodInfo;
import com.yuepang.yuepang.model.MerchantInfo;
import com.yuepang.yuepang.protocol.GetShopListProtocol;
import com.yuepang.yuepang.widget.AreaPopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 精选页面
 */

public class HandpickSecFragment extends BaseSecFragment implements CutAreaInterFace, ClikGoodInter {

    @OnClickView({R.id.merchant1_ly, R.id.merchant2_ly})
    private String string;

    @BindViewByTag
    private TextView tvName1; // 商家1的名称

    @BindViewByTag
    private TextView tvName2; // 商家2的名称

    @BindViewByTag
    private ImageView iv1;// 商家图片1
    @BindViewByTag
    private ImageView iv2;// 商家图片2

    @BindViewByTag
    private ListView goodLv;// 精选商品列表

    @BindViewByTag
    private ImageView ivNot; // 精选商品无数据

    private GoodAdapter goodAdapter; // 商品

    private MerchantInfo info1;// 商家信息1

    private MerchantInfo info2;// 商家信息2

    private AreaPopupWindow areaPopupWindow; // 商家popvindow

    private List<MerchantInfo> merchantInfos;

    private AreaInfo cuurentAreaInfo;

    /**
     * View创建后的初始化
     */
    @Override
    protected void initbeforeView() {
        merchantInfos = new ArrayList<>();
    }

    /**
     * View 创建前的初始化
     */
    @Override
    protected void initafterView() {
        ivNot.setVisibility(View.GONE);// 隐藏无数据内容
        goodLv.setVisibility(View.VISIBLE);// 展示数据无数据内容
        areaPopupWindow = new AreaPopupWindow(getMainActivity(), this); // 新建需要加载的商圈View
        goodAdapter = new GoodAdapter(getMainActivity(), this);  // 新建适配器
        goodLv.setAdapter(goodAdapter);
        goodLv.setOnItemClickListener(goodAdapter);
    }

    @Override
    public void onShow() {
        super.onShow();
        if(cuurentAreaInfo!=null){
            setTvLeftTitle(cuurentAreaInfo.getName());
        }
    }

    @Override
    protected void initData() {
        getMainActivity().getBarTitle().setTitle("精选");
    }

    private void initShop(int id) {
        new GetShopListProtocol(getMainActivity(), new LoadCallBack<List<MerchantInfo>>() {
            @Override
            public void loadCallBack(final CallType callType, int CODE, String msg, final List<MerchantInfo> infos) {
                getMainActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (callType.equals(CallType.SUCCESS)) {
                            merchantInfos.clear();
                            merchantInfos.addAll(infos);
                            info1 = infos.get(0);
                            info2 = infos.get(1);
                            tvName1.setText(info1.getName());
                            tvName2.setText(info2.getName());
                            ImageLoaderUtil.LoadImageViewForUrl(iv1, info1.getPicture());
                            ImageLoaderUtil.LoadImageViewForUrl(iv2, info2.getPicture());
                        }
                    }
                });
            }
        }, id).request();
    }

    @Override
    public int getLyId() {
        return R.layout.handpick_ly;
    }

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
        areaPopupWindow.dismiss();
        setTvLeftTitle(info.getName());
        goodAdapter.getData(info.getId());// 获得商品信息
        initShop(info.getId());// 获得商铺信息
        cuurentAreaInfo = info;
    }


    @Override
    public void toGooddeatail(GoodInfo info) {
        for (MerchantInfo merchantInfo : merchantInfos) {
            if (info.getShop() == merchantInfo.getId()) {
                info.setInfo(merchantInfo);
            }
        }
        Intent intent = new Intent(getMainActivity(), GoodDetailActivity.class);
        intent.putExtra(GoodDetailActivity.GOODINFO, info);
        getMainActivity().startActivity(intent);
    }
}
