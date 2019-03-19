package com.yuepang.yuepang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.adapter.MyFragmentPagerAdapter;
import com.yuepang.yuepang.fragment.BaseFragment;
import com.yuepang.yuepang.fragment.HandpickFragment;
import com.yuepang.yuepang.fragment.MerchantFragment;
import com.yuepang.yuepang.fragment.MineFragment;
import com.yuepang.yuepang.fragment.TopicFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xugh on 2019/3/6.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(id = R.id.main_vp)
    private ViewPager viewPager;

    @BindView(id = R.id.ll_bar1, click = true)
    private LinearLayout ll1;//

    @BindView(id = R.id.ll_bar2, click = true)
    private LinearLayout ll2;//

    @BindView(id = R.id.ll_bar3, click = true)
    private LinearLayout ll3;//

    @BindView(id = R.id.ll_bar4, click = true)
    private LinearLayout ll4;//

    private HandpickFragment handpickFragment;// 精选

    private MerchantFragment merchantFragment;// 商家

    private TopicFragment topicFragment;//话题

    private MineFragment mineFragment;// 我的

    private List<BaseFragment> fragmentList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handpickFragment = new HandpickFragment();
        merchantFragment = new MerchantFragment();
        topicFragment = new TopicFragment();
        mineFragment = new MineFragment();
        fragmentList = new ArrayList<>();
        fragmentList.add(handpickFragment);
        fragmentList.add(merchantFragment);
        fragmentList.add(topicFragment);
        fragmentList.add(mineFragment);
        MyFragmentPagerAdapter mfpa = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList); //new myFragmentPagerAdater记得带上两个参数
        viewPager.setAdapter(mfpa);
        viewPager.setCurrentItem(0); //设置当前页是第一页
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        setTitle("话题");
                        setRightTitle("创建");
                        break;
                    case 3:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected String getMyRTitle() {
        return null;
    }

    @Override
    protected String getMyTittle() {
        return null;
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_bar1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.ll_bar2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.ll_bar3:
                viewPager.setCurrentItem(2);
                break;
            case R.id.ll_bar4:
                viewPager.setCurrentItem(3);
                LogUtils.e("---  viewPager");
                break;
        }
    }

    @Override
    public void clikRt() {
        LogUtils.e("---" + viewPager.getCurrentItem());
        if (viewPager.getCurrentItem() == 2) {
            startActivity(CreateTopicActivity.class);
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.main_ly;
    }


}
