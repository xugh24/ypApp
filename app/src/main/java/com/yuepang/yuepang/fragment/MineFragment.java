package com.yuepang.yuepang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.activity.PersonageActivity;

/**
 * Created by xugh on 2019/3/10.
 */

public class MineFragment extends BaseFragment {


    @BindView(id = R.id.personal_ly, click = true)
    private LinearLayout llhead;

    @BindView(id = R.id.rl_uc_1, click = true)
    private RelativeLayout rlorderId;

    @BindView(id = R.id.rl_uc_2, click = true)
    private RelativeLayout rlhuati;

    @BindView(id = R.id.rl_uc_3, click = true)
    private RelativeLayout rlsetting;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.personal_ly:
                Intent intent = new Intent(getContext(), PersonageActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_uc_1:
                break;
            case R.id.rl_uc_2:
                break;
            case R.id.rl_uc_3:
                break;
        }
    }

    @Override
    public int getLyId() {
        return R.layout.mine_ly;
    }
}
