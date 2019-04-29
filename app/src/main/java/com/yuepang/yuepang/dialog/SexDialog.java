package com.yuepang.yuepang.dialog;

import android.content.DialogInterface;

import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.PersonageActivity;
import com.yuepang.yuepang.widget.SDKdialog;

/**
 * Created by xugh on 2019/4/1.
 */

public class SexDialog extends SDKdialog {


    public SexDialog(final BaseActivity activity, final PersonalDialog.CallBack callBack) {
        super(activity);
        setTitle("个人资料修改");
        setPositiveButton("男", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                callBack.callBack(PersonageActivity.SEX, "1");
            }
        });
        setNegativeButton("女", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callBack.callBack(PersonageActivity.SEX, "2");
            }
        });
        hideEdInput();
    }

}
