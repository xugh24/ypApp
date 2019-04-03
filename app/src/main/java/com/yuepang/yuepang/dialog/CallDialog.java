package com.yuepang.yuepang.dialog;

import android.content.DialogInterface;

import com.yuepang.yuepang.Util.SysUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.widget.SDKdialog;

/**
 * Created by xugh on 2019/4/3.
 */

public class CallDialog extends SDKdialog {
    public CallDialog(final BaseActivity activity, final String tel) {
        super(activity);
        setTitle("联系商家");
        setMsg("是否致电商家？");
        hideEdInput();
        setPositiveButton("是", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                SysUtils.call(getContext(), tel);
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
