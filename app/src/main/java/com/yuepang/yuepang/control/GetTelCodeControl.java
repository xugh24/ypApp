package com.yuepang.yuepang.control;

import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.async.CommonTaskExecutor;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.AuthCodeInfo;
import com.yuepang.yuepang.protocol.SendCode;

/**
 * Created by xugh on 2019/3/6.
 * <p>
 * 短信验证管理类
 * 封装了获得短信逻辑
 */

public class GetTelCodeControl implements View.OnClickListener, LoadCallBack {

    public final static int GET_TEL_CODE_WAITING = 1;

    private EditText edTel;

    private EditText code;

    private TextView tvGetCode;

    private AuthCodeInfo info;

    private BaseActivity activity;

    private int timeTemp = 60;

    private int tvGetCodeState;


    public GetTelCodeControl(BaseActivity activity, EditText edTel, EditText code, TextView tvGetCode) {
        this.activity = activity;
        this.edTel = edTel;
        this.code = code;
        this.tvGetCode = tvGetCode;
        tvGetCode.setOnClickListener(this);// 设置点击获得验证的事件处理
        info = new AuthCodeInfo();
    }

    @Override
    public void onClick(View v) {
        getTelCode();//获得验证码
    }

    /**
     * 获得手机号验证码
     */
    private void getTelCode() {
        final String telNum = edTel.getText().toString().trim();
        if (CheckManage.checkTel(telNum, activity)) {// 验证手机号成功后发起获得验证码逻辑
            new SendCode(activity,this,telNum).request();
        }
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
                tvGetCodeState = 0;
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
                        startCountDown();
                    }
                }, 1000);
            }
        }
    }

    public AuthCodeInfo getInfo() {
        info.setCode(code.getText().toString());
        info.setmTel(edTel.getText().toString());
        if (checkDate()) {
            return info;
        }
        return null;
    }

    /**
     *
     */
    private boolean checkDate() {
         return CheckManage.checkTel(info.getmTel(), activity) && CheckManage.checkCode(info.getCode(), activity);
    }


    /**
     * 发送手机号成功的回调处理
     */
    @Override
    public void loadCallBack(CallType callType, int CODE, String msg, Object info) {
        if(callType.equals(CallType.SUCCESS)){
            activity.showToastSafe("短信获得成功");
            timeTemp = 60;
            tvGetCodeState = 1;
            startCountDown();// 切换UI显示开启倒计时
        }
    }
}
