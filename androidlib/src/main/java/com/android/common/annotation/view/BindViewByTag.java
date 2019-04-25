package com.android.common.annotation.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xugh on 2019/4/25.
 */


@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BindViewByTag {
    /**
     * 是否注册点击事件，默认不注册
     */
    boolean click() default false;
}
