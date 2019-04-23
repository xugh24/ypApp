package com.android.common.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by xugh on 2019/4/18.
 * <p>
 * ListView holder 类
 */

public class ListViewViewHolder {
    private BaseViewHolder baseViewHolderInter;

    private ListViewViewHolder(Context context, int position, int layoutId) {
        baseViewHolderInter = new BaseViewHolder(context,layoutId,position);
        baseViewHolderInter.getmConvertView().setTag(this);
    }

    public static ListViewViewHolder getViewHolder(Context context, int position, View convertView ,int layoutId) {
        if (convertView == null) {
            return new ListViewViewHolder(context, position,  layoutId);
        } else {
            ListViewViewHolder viewHolder = (ListViewViewHolder) convertView.getTag();
            viewHolder.baseViewHolderInter.setmPosition(position);
            return viewHolder;
        }
    }

    public BaseViewHolder getBaseViewHolderInter() {
        return baseViewHolderInter;
    }
}
