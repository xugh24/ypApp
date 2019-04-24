package com.yuepang.yuepang.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.yuepang.yuepang.fragment.BaseSecFragment;

import java.util.List;

/**
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private FragmentManager fragmetnmanager;  //创建FragmentManager
    private List<BaseSecFragment> listfragment; //创建一个List<Fragment>


    public MyFragmentPagerAdapter(FragmentManager fm, List<BaseSecFragment> list) {
        super(fm);
        this.fragmetnmanager = fm;
        this.listfragment = list;
    }


    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        return listfragment.get(position); //返回第几个fragment
    }

    @Override
    public int getCount() {
        return listfragment.size();
    }
}
