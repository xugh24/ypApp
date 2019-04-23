package com.android.common.widget;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.android.common.inter.ViewHolderClick;


/**
 * Created by xugh on 2019/4/18.
 */

public class BaseViewHolder {

    SparseArray<View> mViews;// item 的View列表
    private int mPosition;
    private View mConvertView;

    /**
     * RecyclerView 在初始化的时没有绑定position
     */
    public BaseViewHolder(View mConvertView) {
        this.mConvertView = mConvertView;
        mViews = new SparseArray<>();
    }


    /**
     * listView 在初始化的时候需要绑定id
     */
    public BaseViewHolder(Context context, int layoutId, int position) {
        this(View.inflate(context, layoutId, null));
        this.mPosition = position;
    }

    public <T extends View> T getView(int layoutId, final ViewHolderClick listener) {
        View view = mViews.get(layoutId);
        if (view == null) {
            view = mConvertView.findViewById(layoutId);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v, getmPosition());
                }
            });
            mViews.put(layoutId, view);
        }
        return (T) view;
    }

    public <T extends View> T getView(int id) {
        return getView(id, null);
    }

    public View getmConvertView() {
        return mConvertView;
    }

    public void setText(int id, String value) {
        TextView view = getView(id);
        view.setText(value);
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }

    public int getmPosition() {
        return mPosition;
    }
}
