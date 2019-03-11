package com.yuepang.yuepang.dialog;

import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.PersonageActivity;
import com.yuepang.yuepang.common.widget.SDKdialog;

/**
 * Created by xugh on 2019/3/10.
 */

public class PersonalDialog extends SDKdialog {
    private CallBack callBack;
    private int type;


    public PersonalDialog(final BaseActivity activity,  final CallBack callBack) {
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

    public void setType(int type){
        this.type = type;
    }


    public String getTitleByType() {
        switch (type) {
            case PersonageActivity.NICK:
                return "请输入用户名";
            case PersonageActivity.NAME:
                return "请输入真实姓名";
            case PersonageActivity.SEX:
                return "请选择性别";
            case PersonageActivity.ID:
                return "请输入身份证号";
            case PersonageActivity.TEL:
                return "请输入手机号";
            case PersonageActivity.COMPANY:
                return "请输入公司名称";
            case PersonageActivity.BIR:
                return "请选择生日";
        }
        return "";
    }


   public interface CallBack {
        public void callBack(int type, Object obj);
    }
}
