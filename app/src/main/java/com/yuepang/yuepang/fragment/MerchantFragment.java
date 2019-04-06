package com.yuepang.yuepang.fragment;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.adapter.AreaAdapter;
import com.yuepang.yuepang.adapter.MerchantAdapter;
import com.yuepang.yuepang.widget.AreaPopupWindow;

/**
 */

public class MerchantFragment extends BaseFragment {

    private AreaPopupWindow areaPopupWindow; // 商家popvindow

    private AreaAdapter areaAdapter;// 切换商圈

    private MerchantAdapter merchantAdapter;

    @BindView(id = R.id.mer_lv)
    private ListView lvMer;

    @BindView(id = R.id.ed_serch)
    private EditText edSearch;
    @BindView(id = R.id.tv_serch)
    private TextView tvSearch;


    @Override
    protected void init() {
        areaPopupWindow = new AreaPopupWindow(getMainActivity());
        merchantAdapter = new MerchantAdapter(getMainActivity());
        lvMer.setAdapter(merchantAdapter);
        lvMer.setOnItemClickListener(merchantAdapter);
    }


    @Override
    protected void refreshView() {
        merchantAdapter.notifyDataSetChanged();
    }

    @Override
    protected boolean getData() {
        return merchantAdapter.getData();
    }


    @Override
    public int getLyId() {
        return R.layout.merchant_list;
    }
}
