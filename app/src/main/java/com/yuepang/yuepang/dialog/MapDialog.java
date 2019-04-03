package com.yuepang.yuepang.dialog;

import android.content.DialogInterface;

import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.location.LatLng;
import com.yuepang.yuepang.location.MapUtils;
import com.yuepang.yuepang.widget.SDKdialog;

/**
 * Created by xugh on 2019/4/3.
 * <p>
 * 地图导航选择dialog
 */

public class MapDialog extends SDKdialog {


    public MapDialog(final BaseActivity activity, final LatLng latLng, final String address) {
        super(activity);
        setTitle("选择导航");
        setMsg("请选择你的导航软件？");
        hideEdInput();
        setPositiveButton("百度", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                MapUtils.goToBaiduMap(getContext(), MapUtils.GCJ2BD(latLng), address);
            }
        });
        setNegativeButton("高德", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MapUtils.goToGaodeMap(getContext(), latLng, address);
            }
        });

    }
}
