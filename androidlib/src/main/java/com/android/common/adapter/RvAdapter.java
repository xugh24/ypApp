package com.android.common.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.android.common.widget.VH;

import java.util.List;

/**
 * Created by xugh on 2019/4/17.
 */

public abstract class RvAdapter<T> extends RecyclerView.Adapter<VH> implements View.OnClickListener {

    private List<T> mDatas;
    AdapterManage adapterManage;
    public RvAdapter(List<T> mDatas,RecyclerView recyclerView) {
        this.mDatas = mDatas;
        recyclerView.addOnScrollListener(adapterManage);
    }

    public abstract int getLayoutId(int viewType);

    /**
     * 绑定hoder 数据
     */
    public abstract void convert(VH holder, T data, int position);

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return VH.get(parent, getLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        convert(holder, mDatas.get(position), position);
    }

    /**
     * 获得RecyclerView item 条目数量
     */
    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public List<T> getmDatas() {
        return mDatas;
    }


}
