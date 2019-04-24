package com.yuepang.yuepang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.adapter.AreaAdapter;
import com.yuepang.yuepang.adapter.MyFragmentPagerAdapter;
import com.yuepang.yuepang.dialog.OutDialog;
import com.yuepang.yuepang.fragment.BaseFragment;
import com.yuepang.yuepang.fragment.HandpickFragment;
import com.yuepang.yuepang.fragment.MerchantFragment;
import com.yuepang.yuepang.fragment.MineFragment;
import com.yuepang.yuepang.fragment.TopicFragment;
import com.yuepang.yuepang.model.MerchantInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页面用于加载 精选、商家、话题、我的 功能页
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

    @BindView(id = R.id.iv1)
    private ImageView iv1;

    @BindView(id = R.id.iv2)
    private ImageView iv2;

    @BindView(id = R.id.iv3)
    private ImageView iv3;

    @BindView(id = R.id.iv4)
    private ImageView iv4;

    private HandpickFragment handpickFragment;// 精选

    private MerchantFragment merchantFragment;// 商家

    private TopicFragment topicFragment;//话题

    private MineFragment mineFragment;// 我的

    private List<BaseFragment> fragmentList;

    private List<ImageView> ivs = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ivs.add(iv1);
        ivs.add(iv2);
        ivs.add(iv3);
        ivs.add(iv4);
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
        selIv(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                selIv(position);
                fragmentList.get(position).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (viewPager != null) {
            fragmentList.get(viewPager.getCurrentItem()).show();
        }
    }

    private void selIv(int position) {
        for (ImageView imageView : ivs) {
            imageView.setSelected(false);
        }
        ivs.get(position).setSelected(true);
        switch (position) {
            case 0:
                setTitle("精选");
                getBarTitle().setRightTitle(null);
                break;
            case 1:
                setTitle("商家");
                getBarTitle().setRightTitle(null);
                break;
            case 2:
                setTitle("话题");
                getBarTitle().setRightTitle("创建");
                getBarTitle().setTvLeftTitle(null);
                break;
            case 3:
                setTitle("我的资料");
                getBarTitle().setRightTitle(null);
                getBarTitle().setTvLeftTitle(null);
                break;
        }
    }

    @Override
    public void clikLeftTv() {
        LogUtils.e("-----viewPager.getCurrentItem()----" + viewPager.getCurrentItem());
        if (viewPager.getCurrentItem() == 0) {// 精选页面的点击商圈文字处理逻辑
            handpickFragment.showAreaPop();
        }

        if (viewPager.getCurrentItem() == 1) {// 商家页面的点击商圈文字处理逻辑

        }
    }

    @Override
    public String getMyRTitle() {
        return null;
    }

    @Override
    public String getMyTittle() {
        return null;
    }

    @Override
    public void onBackPressed() {
        new OutDialog(this).show();
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
                break;
        }
    }

    @Override
    public void clikRt() {// 右上角点击
        if (viewPager.getCurrentItem() == 2) {
            startActivity(CreateTopicActivity.class);
        }
    }

    @Override
    protected boolean isShowBack() {
        return false;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.main_ly;
    }



}
