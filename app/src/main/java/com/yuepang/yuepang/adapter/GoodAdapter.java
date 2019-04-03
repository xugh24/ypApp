package com.yuepang.yuepang.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.async.CommonTaskExecutor;
import com.yuepang.yuepang.interFace.AreaInterFace;
import com.yuepang.yuepang.model.AreaInfo;
import com.yuepang.yuepang.model.GoodInfo;
import com.yuepang.yuepang.protocol.HandpickInfoProtocol;
import com.yuepang.yuepang.test.TestData;

import java.util.List;

/**
 * Created by xugh on 2019/4/2.
 */

public class GoodAdapter extends YueBaseAdapter {

    private List<GoodInfo> goodInfos;

    private int areaId;

    private AreaInterFace interFace;

    public GoodAdapter(BaseActivity activity, AreaInterFace interFace) {
        super(activity);
        this.interFace = interFace;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public boolean getData() {
        CommonTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                HandpickInfoProtocol protocol = new HandpickInfoProtocol(activity, areaId);
//                if (protocol.request() == 200) {
//
//                }
            }
        });
        if (interFace != null) {
            interFace.callAreaInfo(TestData.getinfos(), TestData.getMerinfos());
        }
        return true;
    }

    public void setInterFace(AreaInterFace interFace) {
        this.interFace = interFace;
    }

    public void refresh(AreaInfo info) {
        areaId = info.getId();
        getData();
    }
}
