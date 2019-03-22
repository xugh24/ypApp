package com.yuepang.yuepang.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.dialog.PersonalDialog;
import com.yuepang.yuepang.widget.CustomDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by xugh on 2019/3/10.
 * 个人信息页面
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
    @BindView(id = R.id.ll_tel, click = true)
    private LinearLayout lltel;//联系方式


    @BindView(id = R.id.tv_nick)
    private TextView tvNick;//
    @BindView(id = R.id.tv_sex)
    private TextView tvsex;//
    @BindView(id = R.id.tv_birthday)
    private TextView tvbirthday;//
    @BindView(id = R.id.tv_tel)
    private TextView tvTel;//


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
                showInput(SEX);
                break;
            case R.id.ll_birthday: // 生日
                init();
                break;
            case R.id.ll_tel: // 电话
                showInput(TEL);
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

    private void showInput(int type) {
        if (personalDialog == null) {
            personalDialog = new PersonalDialog(this, this);//        }d:\Program Files\Gitd:\Program Files\Git
        }
        personalDialog.setType(type);
        personalDialog.show();
    }

    @Override
    public void callBack(int type, Object obj) {
        if (obj != null && getTargetText(type) != null) {
            String data = (String) obj;
            getTargetText(type).setText(data);
        }
    }

    private TextView getTargetText(int type) {
        switch (type) {
            case PersonageActivity.NICK:
                return tvNick;
            case PersonageActivity.SEX:
                return tvsex;
            case PersonageActivity.TEL:
                return tvTel;
            case PersonageActivity.BIR:
                return tvbirthday;
        }
        return null;
    }
}
