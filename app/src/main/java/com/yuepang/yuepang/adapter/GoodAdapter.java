package com.yuepang.yuepang.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.async.CommonTaskExecutor;
import com.yuepang.yuepang.model.GoodInfo;
import com.yuepang.yuepang.protocol.HandpickInfoProtocol;

import java.util.List;

/**
 * Created by xugh on 2019/4/2.
 */

public class GoodAdapter extends YueBaseAdapter {

    private List<GoodInfo> goodInfos;

    private int areaId;

    public GoodAdapter(BaseActivity activity) {
        super(activity);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    private boolean getData() {

        CommonTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                HandpickInfoProtocol protocol = new HandpickInfoProtocol(activity, areaId);
                if (protocol.request() == 200){

                }
            }
        });

        return false;
    }
}
