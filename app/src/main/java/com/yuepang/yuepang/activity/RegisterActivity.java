package com.yuepang.yuepang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.control.GetTelCodeControl;

/**
 * Created by xugh on 2019/3/1.
 */

public class RegisterActivity extends BaseActivity {

    @BindView(id = R.id.tv_rgtel)
    private EditText edtel;

    @BindView(id = R.id.tv_rgtel)
    private EditText ed_rgcoed;

    @BindView(id = R.id.tv_getrgcoed)
    private TextView tvGetCode;

    private GetTelCodeControl codeControl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        codeControl = new GetTelCodeControl(this, edtel, ed_rgcoed, tvGetCode, 1);
    }

    @Override
    protected String getMyRTitle() {
        return null;
    }

    @Override
    protected String getMyTittle() {
        return "注册";
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){

        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.register_ly;
    }
}
