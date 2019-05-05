package com.yuepang.yuepang.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.android.common.annotation.view.BindViewByTag;
import com.android.common.annotation.view.OnClickView;
import com.android.common.async.ImageLoaderUtil;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.activity.MerchantDetailActivity;
import com.yuepang.yuepang.adapter.GoodAdapter;
import com.yuepang.yuepang.interFace.AreaInterFace;
import com.yuepang.yuepang.interFace.CutAreaInterFace;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.AreaInfo;
import com.yuepang.yuepang.model.MerchantInfo;
import com.yuepang.yuepang.protocol.GetBusinessAreaProtocol;
import com.yuepang.yuepang.protocol.GetShopListProtocol;
import com.yuepang.yuepang.widget.AreaPopupWindow;

import java.util.List;

/**
 * 精选页面
 */

public class HandpickSecFragment extends BaseSecFragment implements AreaInterFace, CutAreaInterFace {

    @OnClickView({R.id.merchant1_ly, R.id.merchant2_ly})
    private String string;

    @BindViewByTag
    private TextView tvName1; // 商家1的名称

    @BindViewByTag
    private TextView tvName2; // 商家2的名称

    @BindViewByTag
    private ImageView iv1;
    @BindViewByTag
    private ImageView iv2;

    @BindViewByTag
    private ListView goodLv;// 精选商品列表

    @BindViewByTag
    private ImageView ivNot;

    private GoodAdapter goodAdapter; // 商品

    private MerchantInfo info1;// 商家信息1

    private MerchantInfo info2;// 商家信息2

    private AreaPopupWindow areaPopupWindow; // 商家popvindow

    /**
     * View创建后的初始化
     */
    @Override
    protected void initbeforeView() {

    }

    /**
     * View 创建前的初始化
     */
    @Override
    protected void initafterView() {
        ivNot.setVisibility(View.GONE);
        goodLv.setVisibility(View.VISIBLE);
        areaPopupWindow = new AreaPopupWindow(getMainActivity()); // 新建需要加载的商圈View
        goodAdapter = new GoodAdapter(getMainActivity(), this);  // 新建适配器
        goodLv.setAdapter(goodAdapter);
        goodLv.setOnItemClickListener(goodAdapter);
    }

    @Override
    public void onShow() {
        super.onShow();
    }


    @Override
    protected void initData() {
        goodAdapter.getData();
        initShop();
    }

    private void initShop() {
        new GetShopListProtocol(getMainActivity(), new LoadCallBack< List<MerchantInfo>>() {
            @Override
            public void loadCallBack(final CallType callType, int CODE, String msg,final List<MerchantInfo> infos) {
                getMainActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(callType.equals(CallType.SUCCESS)){
                            info1 = infos.get(0);
                            info2 = infos.get(1);
                            tvName1.setText(info1.getName());
                            tvName2.setText(info2.getName());
                            ImageLoaderUtil.LoadImageViewForUrl(iv1,info1.getPicture());
                            ImageLoaderUtil.LoadImageViewForUrl(iv2,info2.getPicture());
                        }
                    }
                });
            }
        },1).request();
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

    public void callAreaInfo(List<MerchantInfo> merchantInfos, final AreaInfo currentInfo) {
      //  getMainActivity().getBarTitle().setTvLeftTitle(currentInfo.getName());
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
//        getMainActivity().getBarTitle().setTvLeftTitle(info.getName());
//        goodAdapter.refresh(info);
    }


    @Override
    public void callAreaInfo(AreaInfo currentInfo) {

    }
}
