package com.yuepang.yuepang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.activity.MyTopicActivity;
import com.yuepang.yuepang.activity.PayRecordActivity;
import com.yuepang.yuepang.activity.SettingActivity;
import com.yuepang.yuepang.activity.MylikeActivity;
import com.yuepang.yuepang.activity.PersonageActivity;

/**
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

    @BindView(id = R.id.rl_uc_4, click = true)
    private RelativeLayout rlLike;

    @BindView(id = R.id.rl_uc_5, click = true)
    private RelativeLayout rlMerchant;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean getData() {
        return true;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

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
                Intent intent1 = new Intent(getContext(), PayRecordActivity.class);
                startActivity(intent1);
                break;
            case R.id.rl_uc_2:
                break;
            case R.id.rl_uc_3:
                Intent intent3 = new Intent(getContext(), MyTopicActivity.class);
                startActivity(intent3);

                break;
            case R.id.rl_uc_4:
                Intent intent4 = new Intent(getContext(), MylikeActivity.class);
                startActivity(intent4);
                break;
            case R.id.rl_uc_5:
                Intent intent5 = new Intent(getContext(), SettingActivity.class);
                startActivity(intent5);
                break;
        }
    }

    @Override
    public int getLyId() {
        return R.layout.mine_ly;
    }
}
