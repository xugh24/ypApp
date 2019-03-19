package com.yuepang.yuepang.control;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.async.CommonTaskExecutor;
import com.yuepang.yuepang.model.AuthCodeInfo;

/**
 * Created by xugh on 2019/3/6.
 */

public class GetTelCodeControl implements View.OnClickListener {

    public final static int GET_TEL_CODE_WAITING = 1;

    /**
     *
     */
    private EditText edTel;

    private EditText code;

    private TextView tvGetCode;

    private AuthCodeInfo info;

    private BaseActivity activity;

    private int timeTemp = 60;

    private int tvGetCodeState;


    public GetTelCodeControl(BaseActivity activity, EditText edTel, EditText code, TextView tvGetCode, int codeType) {
        this.activity = activity;
        this.edTel = edTel;
        this.code = code;
        this.tvGetCode = tvGetCode;
        tvGetCode.setOnClickListener(this);
        info = new AuthCodeInfo();
        info.setCodeType(codeType);
    }


    @Override
    public void onClick(View v) {
        if (v == tvGetCode) {
            getTelCode();
        }
    }


    /**
     * 获得手机号验证码
     */
    public void getTelCode() {
        String telNum = edTel.getText().toString().trim();
        if (CheckManage.checkTel(telNum, activity)) {
            CommonTaskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                        writeMsg();
                        activity.showToastSafe("短信获得成功");
                        startCountDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    private void writeMsg() {
        ContentResolver cr = activity.getContentResolver();
        ContentValues values = new ContentValues();
        values.put("address", "155634611");
        values.put("type", 1);
        values.put("date", System.currentTimeMillis());
        values.put("body", "您尾号为9999的信用卡收到1,000,000RMB转账，请注意查收");
        cr.insert(Uri.parse("content://sms"), values);
    }


    private void startCountDown() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                refreshGetCodeTv();
            }
        });
    }


    private void refreshGetCodeTv() {
        if (tvGetCodeState == GET_TEL_CODE_WAITING) {
            if (timeTemp == 0) {
                tvGetCode.setText("获得验证码");
                tvGetCode.setEnabled(true);
                edTel.setEnabled(true);
                edTel.setFocusable(true);
                edTel.setFocusableInTouchMode(true);
            } else {
                LogUtils.e("time = " + timeTemp);
                tvGetCode.setEnabled(false);
                tvGetCode.setFocusable(false);
                edTel.setEnabled(false);
                edTel.setFocusable(false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    tvGetCode.setBackground(activity.getDrawable(R.drawable.btn_gra_bg));
                }
                tvGetCode.setText(activity.getString(R.string.anzhi_get_authcode_waiting_txt, String.valueOf(timeTemp)));
                timeTemp -= 1;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //do something
                    }
                }, 1000);
            }
        }
    }


}
