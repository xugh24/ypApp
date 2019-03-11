package com.yuepang.yuepang.common.widget;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.activity.BaseActivity;

/**
 * 通用的dialog 类
 */

public class SDKdialog extends Dialog implements android.view.View.OnClickListener {

    private BaseActivity activity;
    private boolean hideBackgroundClose = true;
    private View mContentView;
    private boolean interceptClose = false;

    private TextView mTitle;
    private TextView mMessage;
    private EditText edInput;
    private Button mPositiveBtn;
    private Button mNegativeBtn;


    public SDKdialog(BaseActivity activity) {
        super(activity);
        mContentView = View.inflate(activity, R.layout.dialog_layout, null);
        getWindow().setBackgroundDrawableResource(R.drawable.nothing);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setDimAmount(0.7f);
        mTitle = (TextView) mContentView.findViewById(R.id.dialog_tiltle);
        mMessage = (TextView) mContentView.findViewById(R.id.tv_dialog_msg);
        edInput = mContentView.findViewById(R.id.ed_dialog_msg);
        mPositiveBtn = (Button) mContentView.findViewById(R.id.dialog_btnl);
        mNegativeBtn = (Button) mContentView.findViewById(R.id.dialog_btnr);
        setContentView(mContentView);
    }

    public final void setTitle(CharSequence title) {
        mTitle.setText(title);
    }
    public final void setMsg(CharSequence title) {
        mMessage.setText(title);
    }

    public String getEdText(){
        return edInput.getText().toString().trim();
    }

    public final SDKdialog setMessage(CharSequence message) {
        mMessage.setText(message);
        //如果message显示行数为1行，则居中显示
        mMessage.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                //这个回调会调用多次，获取完行数记得注销监听
                mMessage.getViewTreeObserver().removeOnPreDrawListener(this);

                if (mMessage.getLineCount() <= 1) {
                    mMessage.setPadding(0, 25, 0, 25);
                    mMessage.setGravity(Gravity.CENTER);
                }
                return false;
            }
        });
        return this;
    }


    public final SDKdialog setPositiveButton(CharSequence text, final DialogInterface.OnClickListener listener) {
        mPositiveBtn.setText(text);
        mPositiveBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!interceptClose) {
                    dismiss();
                }
                if (listener != null) {
                    listener.onClick(SDKdialog.this, DialogInterface.BUTTON_POSITIVE);
                }
            }
        });
        mPositiveBtn.setVisibility(View.VISIBLE);
        return this;
    }

    public final SDKdialog setNegativeButton(CharSequence text, final DialogInterface.OnClickListener listener) {
        mNegativeBtn.setText(text);
        mNegativeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
                if (listener != null) {
                    listener.onClick(SDKdialog.this, DialogInterface.BUTTON_NEGATIVE);
                }
            }
        });
        mNegativeBtn.setVisibility(View.VISIBLE);
        return this;
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void show() {
        super.show();
    }

}