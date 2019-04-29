package com.android.common.annotation.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xugh on 2019/4/20.
 * <p>
 * 控件自动化绑定注解，本项目的控件的绑定和事件注册均采用注解完成，大幅度节约代码量
 */

@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BindView {
    /**
     * 控件ID
     */
    int id();

    /**
     * 是否注册点击事件，默认不注册
     */
    boolean click() default false;
}
