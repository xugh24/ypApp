package com.yuepang.yuepang.dialog;

import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.PersonageActivity;
import com.yuepang.yuepang.widget.SDKdialog;

/**
 */

public class PersonalDialog extends SDKdialog {
    private CallBack callBack;
    private int type;

    public PersonalDialog(final BaseActivity activity, final CallBack callBack) {
        super(activity);
        setTitle("个人资料修改");
        setPositiveButton("确认", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (TextUtils.isEmpty(getEdText())) {
                    activity.showToastSafe("请输入信息");
                } else {
                    callBack.callBack(type, getEdText());
                }
            }
        });
    }

    @Override
    public void show() {
        super.show();
    }

    public PersonalDialog setType(int type) {
        this.type = type;
        return this;
    }

    public interface CallBack {
        public void callBack(int type, Object obj);
    }
}
