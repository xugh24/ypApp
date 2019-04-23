package com.android.common.annotation.view;

/**
 * Created by xugh on 2019/4/20.
 */

public @interface BindBtn {
    /**
     * 控件ID
     */
    int id();

    /**
     * 是否注册点击事件，默认不注册
     */
    boolean click() default true;
}
