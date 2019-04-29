package com.android.common.inter;

/**
 * Created by xugh on 2019/4/26.
 */

public interface ViewPagechangeInter {
    void hide(int position);

    void show(int position);

     void pageChanged(int lastPosition,int position);
}
