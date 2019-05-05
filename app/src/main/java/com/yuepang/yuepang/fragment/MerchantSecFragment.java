package com.yuepang.yuepang.fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.android.common.annotation.view.BindViewByTag;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.adapter.MerchantAdapter;
import com.yuepang.yuepang.interFace.AreaInterFace;
import com.yuepang.yuepang.interFace.CutAreaInterFace;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.AreaInfo;
import com.yuepang.yuepang.protocol.GetShopListProtocol;
import com.yuepang.yuepang.widget.AreaPopupWindow;

/**
 */

public class MerchantSecFragment extends BaseSecFragment implements AreaInterFace, CutAreaInterFace, LoadCallBack {

    private AreaPopupWindow areaPopupWindow; // 商家popvindow

    private MerchantAdapter merchantAdapter;

    @BindViewByTag
    private ListView lvMer;
    @BindViewByTag
    private EditText edSearch;
    @BindViewByTag
    private TextView tvSearch;

    @Override
    protected void initbeforeView() {
        areaPopupWindow = new AreaPopupWindow(getMainActivity());
    }

    @Override
    protected void initafterView() {
        merchantAdapter = new MerchantAdapter(getMainActivity(), this);
        lvMer.setAdapter(merchantAdapter);
    }

    @Override
    protected void initData() {// 获取数据
        merchantAdapter.getData();
    }

    public void showAreaPop() {
        if (areaPopupWindow != null) {
            areaPopupWindow.show(getMainActivity().getBarTitle().getTvLeftTitle());
        }
    }

    @Override
    public void onShow() {
        super.onShow();
    }

    @Override
    public int getLyId() {
        return R.layout.merchant_list;
    }

    @Override
    public void cutAreaInfo(AreaInfo info) {
        getMainActivity().getBarTitle().setTvLeftTitle(info.getName());
        merchantAdapter.refresh(info);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void loadCallBack(CallType callType, int CODE, String msg, Object info) {

    }

    @Override
    public void callAreaInfo(AreaInfo currentInfo) {

    }
}
