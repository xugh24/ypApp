package com.yuepang.yuepang.fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.common.annotation.view.BindView;
import com.android.common.annotation.view.BindViewByTag;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.adapter.AreaAdapter;
import com.yuepang.yuepang.adapter.MerchantAdapter;
import com.yuepang.yuepang.interFace.AreaInterFace;
import com.yuepang.yuepang.interFace.CutAreaInterFace;
import com.yuepang.yuepang.model.AreaInfo;
import com.yuepang.yuepang.model.MerchantInfo;
import com.yuepang.yuepang.widget.AreaPopupWindow;

import java.util.List;

/**
 */

public class MerchantSecFragment extends BaseSecFragment implements AreaInterFace, CutAreaInterFace {

    private AreaPopupWindow areaPopupWindow; // 商家popvindow

    private AreaAdapter areaAdapter;// 切换商圈

    private MerchantAdapter merchantAdapter;

    @BindViewByTag
    private ListView lvMer;
    @BindViewByTag
    private EditText edSearch;
    @BindViewByTag
    private TextView tvSearch;




    public void showAreaPop() {
        if (areaPopupWindow != null) {
            areaPopupWindow.show(getMainActivity().getBarTitle().getTvLeftTitle());
        }
    }

    @Override
    protected void refreshView() {
        lvMer.setAdapter(merchantAdapter);
        merchantAdapter.notifyDataSetChanged();
    }

    @Override
    protected boolean getData() {
        return merchantAdapter.getData();
    }

    @Override
    protected void initafterView() {

    }

    @Override
    protected void initbeforeView() {
        areaPopupWindow = new AreaPopupWindow(getMainActivity());
        merchantAdapter = new MerchantAdapter(getMainActivity(), this);
    }

    @Override
    public void onShow() {
        setTitle("商家");
        setRightTitle(null);
    }


    @Override
    public int getLyId() {
        return R.layout.merchant_list;
    }

    @Override
    public void callAreaInfo(List<AreaInfo> areaInfos, List<MerchantInfo> merchantInfos, AreaInfo currentInfo) {
        areaAdapter = new AreaAdapter((BaseActivity) getActivity(), areaInfos, this);
        areaPopupWindow.setAdapter(areaAdapter);
        areaAdapter.notifyDataSetChanged();
    }

    @Override
    public void cutAreaInfo(AreaInfo info) {
        getMainActivity().getBarTitle().setTvLeftTitle(info.getName());
        merchantAdapter.refresh(info);
    }

    @Override
    public void onClick(View v) {

    }
}
