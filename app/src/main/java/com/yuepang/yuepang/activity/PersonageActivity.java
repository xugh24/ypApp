package com.yuepang.yuepang.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.Util.DrawableUtil;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.control.DataControl;
import com.yuepang.yuepang.control.UserCentreControl;
import com.yuepang.yuepang.db.YuePangExternalDB;
import com.yuepang.yuepang.dialog.PersonalDialog;
import com.yuepang.yuepang.dialog.PicDialog;
import com.yuepang.yuepang.dialog.SexDialog;
import com.yuepang.yuepang.fragment.MineSecFragment;
import com.yuepang.yuepang.model.UserInfo;
import com.yuepang.yuepang.widget.CustomDatePicker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.yuepang.yuepang.dialog.PicDialog.CODE;
import static com.yuepang.yuepang.dialog.PicDialog.PHOTO_CODE;

/**
 * 个人信息页面
 * <p>
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
    @BindView(id = R.id.tv_pwd, click = true)
    private TextView tvPwd;
    @BindView(id = R.id.tv_nick)
    private TextView tvNick;//
    @BindView(id = R.id.tv_sex)
    private TextView tvsex;//
    @BindView(id = R.id.tv_birthday)
    private TextView tvbirthday;//
    @BindView(id = R.id.tv_tel)
    private TextView tvTel;//
    private int REQ_CROP = 0x03;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onResume() {
        super.onResume();
        String tel = YuePangExternalDB.getInstance(this).getValueById(UserCentreControl.getInstance().getInfo().getId() + "", YuePangExternalDB.FIELD_TEL);
    }

    @Override
    public String getMyTittle() {
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
            case R.id.tv_pwd: // 修改密码
                startActivity(ChangePwdActivity.class);
                break;
            case R.id.iv_head: //
                new PicDialog(this).show();
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
            personalDialog = new PersonalDialog(this, this);//
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.e("---requestCode-----" + requestCode + "===" + data);
        if (requestCode == PHOTO_CODE) {
            startPhotoZoom(Uri.fromFile(PicDialog.getHeaderTempBmpFile()));//获取地址并调用裁剪
            startPhotoZoom(data.getData());//返回的是地址，然后对图片裁剪
        } else if (requestCode == REQ_CROP) {
            if (data != null) {
                Bundle bundle = data.getExtras();//把数据取出来，Bundle是一个装数据的可以在activity之间传输的类
                Bitmap bitmap = bundle.getParcelable("data");//获取位图
                ivHead.setImageBitmap(bitmap);//设置位图，显示
            }
        } else if (requestCode == CODE) {
            startPhotoZoom(data.getData());//返回的是地址，然后对图片裁剪
        }
    }

    private static final String IMAGE_UNSPECIFIED = "image/*";

    /**
     * 照片压缩
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");//裁剪意图
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);//设置裁剪的地址和类型
        intent.putExtra("crop", false);//把未裁剪信息附加到intent上
        //设置宽高比例为1:1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        //设置裁剪图片的宽高
        intent.putExtra("return-data", true);//要返回值
        startActivityForResult(intent, REQ_CROP);//执行意图，赋予请求码
    }

    private TextView getTargetText(int type) {
        switch (type) {
            case PersonageActivity.NICK:
                return tvNick;
            case PersonageActivity.BIR:
                return tvbirthday;
            case PersonageActivity.SEX:
                return tvsex;
        }
        return null;
    }

    public static void toThisActivity(Context context) {
        Intent intent = new Intent(context, PersonageActivity.class);
        context.startActivity(intent);
    }
}
