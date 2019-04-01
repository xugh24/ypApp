package com.yuepang.yuepang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.widget.LoadingFrame;

/**
 * Created by xugh on 2019/3/27.
 */

public abstract class BaseLoadFrameActivity extends BaseActivity {

    private LoadingFrame loadingFrame;

    @BindView(id = R.id.main_ll)
    private LinearLayout llmain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingFrame = new LoadingFrame(this) {
            @Override
            public boolean load(View loadingView) {
                return getdata();
            }

            @Override
            public View createLoadedView() {
                return getMainView();
            }
        };
        llmain.addView(llmain, -1, -1);
        loadingFrame.show();
    }

    protected abstract View getMainView();

    protected abstract boolean getdata();

    @Override
    protected int getContentViewId() {
        return R.layout.comm_ly;
    }
}
