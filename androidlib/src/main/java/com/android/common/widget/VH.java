package com.android.common.widget;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xugh on 2019/4/17.
 */

public class VH extends RecyclerView.ViewHolder {

    private BaseViewHolder baseViewHolderInter;

    private VH(View v ) {
        super(v);
        baseViewHolderInter = new BaseViewHolder(v);
    }

    public static VH get(ViewGroup parent, int layoutId) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new VH(convertView);
    }

}
