package com.android.common.widget;

import android.content.Context;
import android.util.Log;

import com.android.common.inter.ViewHolderClick;
import com.android.common.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xugh on 2019/4/26.
 */

public class ViewPageTab {

    private List<BaseViewHolder> holders;

    private int count;

    public ViewPageTab(Context context, int count, int layoutId) {
        this.count = count;
        holders = new ArrayList<BaseViewHolder>(count);
        for (int i = 0; i < count; i++) {
            BaseViewHolder holder = new BaseViewHolder(context, layoutId, i);
            holders.add(holder);
        }
    }

    public BaseViewHolder getHolder(int p) {
        LogUtils.e("---"+p);
        return holders.get(p);
    }

}
