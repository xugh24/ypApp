package com.yuepang.yuepang.dialog;
import android.content.DialogInterface;

import com.android.common.activity.ActivityManage;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.widget.SDKdialog;

/**
 * Created by xugh on 2019/4/4.
 */

public class OutDialog extends SDKdialog {
    public OutDialog(BaseActivity activity) {
        super(activity);
        setTitle("退出");
        setMsg("是否退出应用？");
        hideEdInput();
        setPositiveButton("是", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityManage.finishAll();
            }
        });
        setNegativeButton("否", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

    }
}
