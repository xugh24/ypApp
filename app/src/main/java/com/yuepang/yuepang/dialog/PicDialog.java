package com.yuepang.yuepang.dialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.android.common.utils.LogUtils;
import com.yuepang.yuepang.Util.SysUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.widget.SDKdialog;

import java.io.File;

/**
 * Created by xugh on 2019/4/6.
 */

public class PicDialog extends SDKdialog {

    public final static int PHOTO_CODE = 0x01;
    public final static int CODE = 0x02;

    public PicDialog(final BaseActivity activity) {
        super(activity);
        setTitle("选择头像");
        setMsg("选择上传头像的方式");
        hideEdInput();
        setPositiveButton("拍照", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri imageUri = null;
                if (Build.VERSION.SDK_INT >= 24 && activity.getApplicationInfo().targetSdkVersion >= 24) {
                    String authority = "com.jit.yqq.yuepang.fileprovider";
                    imageUri = FileProvider.getUriForFile(activity, authority, getHeaderTempBmpFile());
                } else {
                    imageUri = Uri.fromFile(getHeaderTempBmpFile());
                }
                LogUtils.e("imageUri == "+imageUri);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                activity.startActivityForResult(intent, PHOTO_CODE);
                dismiss();
            }
        });
        setNegativeButton("相册", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                activity.startActivityForResult(intent, CODE);
                dismiss();
            }
        });
    }

    /**
     * @return File
     * @Title: getHeaderBmpFile
     * @Description: 获取头像Bitmap的File对象
     */
    public static File getHeaderTempBmpFile() {
        File file = null;
        if (SysUtils.isSDCardAvailable()) {
            file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/yuepang/image_cache");
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        File headFile = new File(file.getAbsolutePath(), "temp");
        return headFile;
    }
}