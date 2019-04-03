package com.yuepang.yuepang.fragment;

import android.widget.ListView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;

/**
 */

public class MerchantFragment extends BaseFragment{

    @BindView(id = R.id.mer_lv )
    private ListView lvMer;

    @Override
    protected void refreshView() {

    }

    @Override
    protected boolean getData() {
        return true;
    }

    @Override
    protected void init() {
        
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public int getLyId() {
        return R.layout.merchant_list;
    }
}
