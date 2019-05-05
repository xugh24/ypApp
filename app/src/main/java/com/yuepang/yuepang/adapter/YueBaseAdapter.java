package com.yuepang.yuepang.adapter;

import android.widget.BaseAdapter;
import com.yuepang.yuepang.activity.BaseActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xugh on 2019/3/25.
 * <p>
 * 悦旁 Adapter的基类
 */

public abstract class YueBaseAdapter <T>  extends BaseAdapter {

    public BaseActivity activity;

    public List<T> list;

    public YueBaseAdapter(BaseActivity activity, List<T> list) {
        this.activity = activity;
        this.list = list;
    }

    public YueBaseAdapter(BaseActivity activity) {
        this.activity = activity;
        list = new ArrayList<T>();
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
    public T getItem(int position) {
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
