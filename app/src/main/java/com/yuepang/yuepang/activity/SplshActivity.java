package com.yuepang.yuepang.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.async.CommonTaskExecutor;
import com.yuepang.yuepang.control.UserCentreControl;

import java.util.ArrayList;
import java.util.List;

/**
 * 闪屏页面
 * 1、功能动态权限获取（Android 版本23以上需要动态获得权限）
 * 2、程序初始化操作
 */

public class SplshActivity extends BaseActivity {

    private final int TIME = 3 * 1000; // 首页展示时间（单位毫秒）默认3000ms ， 可修改

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 23) {
            checkAndRequestPermission();
        } else {
            // 如果是Android6.0以下的机器，默认在安装时获得了所有权限，可以直接调用初始化方法
            loadAdSplash();
        }
    }


    /**
     * 正常展示和初始化逻辑
     */
    private void loadAdSplash() {
        CommonTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    UserCentreControl.getInstance().init(getApplicationContext());//程序初始化
                    Thread.sleep(TIME);// 延时 checkAndRequestPermission();// 检查权限
                    startActivity(FirstActivity.class);// 打开登录页面
                    finish(); // 关闭页面
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkAndRequestPermission() {
        List<String> lackedPermission = new ArrayList<String>();
        if (!(checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.READ_PHONE_STATE);
        }

        if (!(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        // 权限都已经有了，那么直接调用SDK
        if (lackedPermission.size() == 0) {
            loadAdSplash();
        } else {
            // 请求所缺少的权限，在onRequestPermissionsResult中再看是否获得权限，如果获得权限就可以调用SDK，否则不要调用SDK。
            String[] requestPermissions = new String[lackedPermission.size()];
            lackedPermission.toArray(requestPermissions);
            requestPermissions(requestPermissions, 1024);
        }
    }

    private boolean hasAllPermissionsGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1024 && hasAllPermissionsGranted(grantResults)) {
            loadAdSplash();
        } else {
            // 如果用户没有授权，那么应该说明意图，引导用户去设置里面授权。
            Toast.makeText(this, "应用缺少必要的权限！请点击\"权限\"，打开所需要的权限。", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
            finish();
        }
    }



    @Override
    public String getMyTittle() {
        return null;
    }

    @Override
    protected boolean isShowBar() {
        return false;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.splsh_ly;
    }
}
