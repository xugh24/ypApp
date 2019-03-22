package com.yuepang.yuepang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.Util.LogUtils;

import java.util.List;

/**
 * Created by xugh on 2019/3/10.
 */

public class MerchantFragment extends BaseFragment{

    @BindView(id = R.id.mer_lv )
    private ListView lvMer;

    @Override
    protected boolean getData() {
        return true;
    }

    @Override
    protected void initView() {
        
    }

    @Override
    public int getLyId() {
        return R.layout.merchant_list;
    }
}
