package com.yuepang.yuepang.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.common.annotation.view.BindViewByTag;
import com.android.common.annotation.view.OnClickView;
import com.android.common.async.ImageLoaderUtil;
import com.yuepang.yuepang.BuildConfig;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.control.UserCentreControl;
import com.yuepang.yuepang.dialog.PersonalDialog;
import com.yuepang.yuepang.dialog.PicDialog;
import com.yuepang.yuepang.dialog.SexDialog;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.UserInfo;
import com.yuepang.yuepang.protocol.SubImageProtocol;
import com.yuepang.yuepang.protocol.SubPersonInfoProtocol;
import com.yuepang.yuepang.widget.CustomDatePicker;

import java.io.FileNotFoundException;
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

public class PersonageActivity extends BaseActivity implements PersonalDialog.CallBack, LoadCallBack<UserInfo> {

    public static final int NICK = 0x01;
    public static final int SEX = 0x02;
    public static final int BIR = 0x03;
    public static final int TEL = 0x04;

    @OnClickView({R.id.ll_nick, R.id.ll_sex, R.id.ll_birthday, R.id.tv_pwd})
    private String clik;

    @BindViewByTag(click = true)
    private ImageView ivHead;// 头像

    @BindViewByTag
    private TextView tvNick;// 昵称

    @BindViewByTag
    private TextView tvsex;// 性别

    @BindViewByTag
    private TextView tvbirthday;// 生日

    @BindViewByTag
    private TextView tvTel;// 电话

    private int REQ_CROP = 0x03;

    private UserInfo info;// 需要修改的临时Info信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageLoaderUtil.LoadcircleImage(ivHead, getUserInfo().getHeaderImgUrl());//
        tvTel.setText(getUserInfo().getTel());
        tvNick.setText(getUserInfo().getNick());
        tvbirthday.setText(getUserInfo().getBirthday());
        tvsex.setText(getUserInfo().getSexTxte());
    }

    @Override
    protected void initbefore() {
        info = new UserInfo();
    }

    @Override
    public String getMyTittle() {
        return "修改资料";
    }

    @Override
    public String getMyRTitle() {
        return "提交";
    }

    @Override
    protected int getContentViewId() {
        return R.layout.personage_ly;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_nick:// 昵称
                new PersonalDialog(this, this).setType(NICK).show();
                break;
            case R.id.ll_sex: // 性别
                new SexDialog(this, this).show();//
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

    @Override
    public void callBack(int type, Object obj) {
        String data = (String) obj;
        switch (type) {
            case SEX:
                info.setSex(data);
                if ("1".equals(data)) {
                    getTargetText(type).setText("男");
                } else {
                    getTargetText(type).setText("女");
                }
                break;
            case NICK:
                getTargetText(type).setText(data);
                info.setNick(data);
                break;
            case BIR:
                getTargetText(type).setText(data);
                info.setBirthday(data);
                break;
        }
    }

    @Override
    public void clickRt() {
        new SubPersonInfoProtocol(this, this, info).request();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.e("---requestCode-----" + requestCode + "===" + data);
        if (requestCode == PHOTO_CODE) {
            Uri uri = null;
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                String authority = "com.jit.yqq.yuepang.fileprovider";
                uri = FileProvider.getUriForFile(this, authority, PicDialog.getHeaderTempBmpFile());
            } else {
                uri = Uri.fromFile(PicDialog.getHeaderTempBmpFile());
            }
            startPhotoZoom(uri);//获取地址并调用裁剪
        } else if (requestCode == REQ_CROP) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uritempFile));
                ImageLoaderUtil.LoadcircleImage(ivHead, bitmap);//设置位图，显示
                new SubImageProtocol(this, this, bitmap).request();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (requestCode == CODE) {
            if(data!=null){
                startPhotoZoom(data.getData());//返回的是地址，然后对图片裁剪
            }
        }
    }

    private static final String IMAGE_UNSPECIFIED = "image/*";
    Uri uritempFile;

    /**
     * 打开照片裁剪
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");//裁剪意图
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);//设置裁剪的地址和类型
        intent.putExtra("crop", false);//把未裁剪信息附加到intent上
        //设置宽高比例为1:1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        //设置裁剪图片的宽高
        intent.putExtra("return-data", true);//要返回值
        uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
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

    @Override
    public void loadCallBack(CallType callType, int CODE, String msg, UserInfo info) {
        if (callType.equals(CallType.SUCCESS)) {
            UserCentreControl.getInstance().setInfo(info);
        }
    }
}
