package com.yuepang.yuepang.presenter;

import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.control.GetTelCodeControl;

/**
 * Created by xugh on 2019/3/27.
 */

public class BasePresenter<T> {

    public T activity;


    public BasePresenter(T activity){
        this.activity = activity;
    }

}
