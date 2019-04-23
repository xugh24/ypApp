package com.android.common.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import com.android.common.activity.BaseActivity;
import com.android.common.widget.BaseViewHolder;
import com.android.common.widget.ListViewViewHolder;

import java.util.List;

/**
 * Created by xugh on 2019/4/20.
 */

public abstract class LvAdapter<T> extends BaseAdapter {


    public final static int TYPE_VIEW = 1;// 普通View

    public final static int TYPE_MORE_VIEW = 0;// 加载更多的View

    protected boolean isLoadMoreData;

    private List<T> list;

    private AbsListView absListView;

    private AdapterManage adapterManage;

    private BaseActivity activity;

    public LvAdapter(List<T> list, AbsListView listView) {
//        adapterManage = new AdapterManage();
        this.list = list;
        this.absListView = listView;
        absListView.setOnScrollListener(adapterManage);
    }

    /**
     * 获得listView数目
     */

    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (getItemViewType(position)) {
            case TYPE_MORE_VIEW:
                return getMoreView();
            case TYPE_VIEW:
                ListViewViewHolder holder = ListViewViewHolder.getViewHolder(activity, position, convertView, getLayoutId());
                bindView(position, holder.getBaseViewHolderInter());
                return holder.getBaseViewHolderInter().getmConvertView();
        }
        return null;

    }

    @Override
    public final int getCount() {
        return hasMore() && isLoadMoreData ? getItemCount() + 1 : getItemCount();
    }

    public boolean hasMore() {
        return true;
    }

    private void loadMoreData() {
//        if (isLoading) {
//            return;
//        }
//        isLoading = true;
//        CommonTaskExecutor.execute(new Runnable() {
//
//            @Override
//            public void run() {
//                start = getItemCount();
//                size = DEFAULT_INCREMENT;
//                int code = onLoadMoreData(start, size);
//                if (code == 200) {
//                    handler.sendEmptyMessage(LOAD_MORE_DATE_SUCCESS);
//                }
//            }
//        });
    }


    /**
     * 绑定View设置参数
     */
    protected abstract void bindView(int position, BaseViewHolder holder);

    protected abstract int getLayoutId();

    /**
     * 获得item的类型，
     * 根据item的类型不同可以处理不同的类型
     */
    @Override
    public final int getItemViewType(int position) {
        if (hasMore() && isLoadMoreData && position == getCount() - 1) {
            return TYPE_MORE_VIEW;
        }
        return getCountentViewType(position);
    }


    /**
     * 根据item的类型不同可以处理不同的类型
     */
    public int getCountentViewType(int position) {
        return TYPE_VIEW;
    }


    /**
     * 获得加载更多的View
     */
    public View getMoreView() {
        return null;
    }
}
