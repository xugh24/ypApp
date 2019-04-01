package com.yuepang.yuepang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.Util.DrawableUtil;
import com.yuepang.yuepang.control.DataControl;
import com.yuepang.yuepang.control.UserCentreControl;
import com.yuepang.yuepang.db.YuePangExternalDB;
import com.yuepang.yuepang.dialog.PersonalDialog;
import com.yuepang.yuepang.dialog.SexDialog;
import com.yuepang.yuepang.model.UserInfo;
import com.yuepang.yuepang.widget.CustomDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 个人信息页面
 *
 * 头像动态获得
 * 用户可以修改自己昵称和性别
 * 不可以修改自己手机号
 */

public class PersonageActivity extends BaseActivity implements PersonalDialog.CallBack {

    public static final int NICK = 0x01;
    public static final int SEX = 0x02;
    public static final int BIR = 0x03;
    public static final int TEL = 0x04;

    @BindView(id = R.id.iv_head, click = true)
    private ImageView ivHead;
    @BindView(id = R.id.ll_nick, click = true)
    private LinearLayout llnick;//昵称
    @BindView(id = R.id.ll_sex, click = true)
    private LinearLayout llsex;//性别
    @BindView(id = R.id.ll_birthday, click = true)
    private LinearLayout llbirthday;//生日


    @BindView(id = R.id.tv_nick)
    private TextView tvNick;//
    @BindView(id = R.id.tv_sex)
    private TextView tvsex;//
    @BindView(id = R.id.tv_birthday)
    private TextView tvbirthday;//
    @BindView(id = R.id.tv_tel)
    private TextView tvTel;//


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onResume() {
        super.onResume();
        String tel = YuePangExternalDB.getInstance(this).getValueById(UserCentreControl.getInstance().getInfo().getId() + "", YuePangExternalDB.FIELD_TEL);
        DrawableUtil.loadImageForUrl(ivHead, UserCentreControl.getInstance().getInfo().getHeaderImgUrl(), this);

    }

    @Override
    protected String getMyRTitle() {
        return null;
    }

    @Override
    protected String getMyTittle() {
        return "修改资料";
    }

    @Override
    protected int getContentViewId() {
        return R.layout.personage_ly;
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_nick:// 昵称
                showInput(NICK);
                break;
            case R.id.ll_sex: // 性别
                if (sexDialog == null) {
                    sexDialog = new SexDialog(this, this);//
                }
                sexDialog.show();
                break;
            case R.id.ll_birthday: // 生日
                init();
                break;
        }
    }

    private void init() {
        String birthday = "1997年-1月-1日";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年-M月-d日", Locale.CHINA);
        String now = sdf.format(new Date());
        CustomDatePicker customDatePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                callBack(BIR, time);
            }
        }, "1910年-1月-1日", now, birthday);
        customDatePicker.setIsLoop(true); // 不允许循环滚动
        customDatePicker.show("1997年-1月-1日");
    }


    PersonalDialog personalDialog;
    SexDialog sexDialog;

    private void showInput(int type) {
        if (personalDialog == null) {
            personalDialog = new PersonalDialog(this, this);//        }d:\Program Files\Gitd:\Program Files\Git
        }
        personalDialog.setType(type);
        personalDialog.show();
    }

    @Override
    public void callBack(int type, Object obj) {

        if (type == SEX) {
            int sex = (int) obj;
            if (sex == 1) {
                getTargetText(type).setText("男");
            } else {
                getTargetText(type).setText("女");
            }
            UserCentreControl.getInstance().getInfo().setSex(sex);
        } else {
            String data = (String) obj;
            if (obj != null && getTargetText(type) != null) {
                getTargetText(type).setText(data);
            }
            if (type == PersonageActivity.NICK) {
                UserCentreControl.getInstance().getInfo().setNick(data);
            }
            if (type == PersonageActivity.BIR) {
                UserCentreControl.getInstance().getInfo().setBirthday(data);
            }
        }


    }

    private TextView getTargetText(int type) {
        switch (type) {
            case PersonageActivity.NICK:
                return tvNick;
            case PersonageActivity.BIR:
                return tvbirthday;
        }
        return null;
    }
}
