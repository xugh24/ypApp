package com.yuepang.yuepang.Util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，用来绑定页面UI控件
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindView {

    /**
     *   控件ID
     */
    int id();

    /**
     * 是否注册点击事件，默认不注册
     */
    boolean click() default false;
}
