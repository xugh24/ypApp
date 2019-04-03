package com.yuepang.yuepang.adapter;

import android.widget.BaseAdapter;

import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.model.MerchantInfo;

import java.util.List;

/**
 * Created by xugh on 2019/3/25.
 * <p>
 * 悦旁 Adapter的基类
 */

public abstract class YueBaseAdapter extends BaseAdapter {

    public BaseActivity activity;

    public List<?> list;

    public YueBaseAdapter(BaseActivity activity, List<?> list) {
        this.activity = activity;
        this.list = list;
    }

    public YueBaseAdapter(BaseActivity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

}
