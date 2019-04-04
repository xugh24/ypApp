package com.yuepang.yuepang.widget;

import android.app.Dialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.SysUtils;
import com.yuepang.yuepang.activity.BaseActivity;

/**
 * 通用的dialog 类
 */

public class SDKdialog extends Dialog implements View.OnClickListener {

    private BaseActivity activity;
    private boolean hideBackgroundClose = true;
    private View mContentView;
    private boolean interceptClose = false;

    private TextView mTitle;
    private EditText edInput;
    private Button mPositiveBtn;
    private Button mNegativeBtn;
    private RelativeLayout mainView;
    private TextView msg;


    public SDKdialog(BaseActivity activity) {
        super(activity);
        mContentView = View.inflate(activity, R.layout.dialog_layout, null);
        getWindow().setBackgroundDrawableResource(R.drawable.nothing);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setDimAmount(0.7f);
        getWindow().getDecorView().setPadding(SysUtils.dip2px(activity,24), 0, SysUtils.dip2px(activity,24), 0);
        mTitle = (TextView) mContentView.findViewById(R.id.dialog_tiltle);
        edInput = mContentView.findViewById(R.id.ed_dialog_msg);
        mPositiveBtn = (Button) mContentView.findViewById(R.id.dialog_btnl);
        mNegativeBtn = (Button) mContentView.findViewById(R.id.dialog_btnr);
        msg = mContentView.findViewById(R.id.tv_dialog_msg);
        mainView = mContentView.findViewById(R.id.ll_content);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setContentView(mContentView, params);
    }

    public void setView(View view) {
        mainView.addView(view);
        msg.setVisibility(View.GONE);
        edInput.setVisibility(View.GONE);
        mainView.setVisibility(View.VISIBLE);
    }

    public void setMsg(String stmsg) {
        if (TextUtils.isEmpty(stmsg)) {
            msg.setVisibility(View.GONE);
        } else {
            msg.setVisibility(View.VISIBLE);
            msg.setText(stmsg);
        }

    }

    public final void setTitle(CharSequence title) {
        mTitle.setText(title);
    }

    public String getEdText() {
        return edInput.getText().toString().trim();
    }

    public void hideEdInput() {
        if (edInput != null) {
            edInput.setVisibility(View.GONE);
        }
    }


    public final SDKdialog setPositiveButton(CharSequence text, final OnClickListener listener) {
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

    public final SDKdialog setNegativeButton(CharSequence text, final OnClickListener listener) {
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