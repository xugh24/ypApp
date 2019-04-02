package com.yuepang.yuepang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.widget.LoadingFrame;

/**
 * Created by xugh on 2019/3/27.
 */

public abstract class BaseLoadFrameActivity extends BaseActivity {

    private LoadingFrame loadingFrame;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected abstract View getMainView();

    protected abstract boolean getdata();

    @Override
    public View getContentView() {
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
        loadingFrame.show();
        return loadingFrame;
    }

    @Override
    protected int getContentViewId() {
        return -1;
    }
}
