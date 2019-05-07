package com.yuepang.yuepang.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.common.annotation.view.BindViewByTag;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.adapter.MerchantAdapter;
import com.yuepang.yuepang.interFace.CutAreaInterFace;
import com.yuepang.yuepang.model.AreaInfo;
import com.yuepang.yuepang.widget.AreaPopupWindow;


/**
 * 商家列表页
 */

public class MerchantSecFragment extends BaseSecFragment implements CutAreaInterFace {

    private AreaPopupWindow areaPopupWindow; // 商圈popvindow

    private MerchantAdapter merchantAdapter; // 商家设配器

    private AreaInfo cuurentAreaInfo;

    @BindViewByTag
    private ListView lvMer;
    @BindViewByTag
    private EditText edSearch;
    @BindViewByTag(click = true)
    private TextView tvSearch;

    @Override
    protected void initbeforeView() {// 创建商圈视图
        merchantAdapter = new MerchantAdapter(getMainActivity());
    }

    @Override
    protected void initafterView() {
        areaPopupWindow = new AreaPopupWindow(getMainActivity(), this);
        lvMer.setAdapter(merchantAdapter);
    }

    @Override
    protected void initData() {// 获取数据
    }

    @Override
    public void onShow() {
        super.onShow();
        if(cuurentAreaInfo!=null){
            setTvLeftTitle(cuurentAreaInfo.getName());
        }
    }

    @Override
    public void onClikLeft() {
        if (areaPopupWindow != null) {
            areaPopupWindow.show(getMainActivity().getBarTitle().getTvLeftTitle());
        }
    }

    @Override
    public int getLyId() {
        return R.layout.merchant_list;
    }

    /**
     * 切换商家
     */
    @Override
    public void cutAreaInfo(AreaInfo info) {
        merchantAdapter.refresh(info);
        areaPopupWindow.dismiss();
        cuurentAreaInfo = info;
        setTvLeftTitle(info.getName());
    }

    @Override
    public void onClick(View v) {
        if ("tvSearch".equals(v.getTag())) {
            merchantAdapter.serchList(getMainActivity().getEditText(edSearch));// 搜索商家
        }
    }

}
