package com.android.common.annotation.formCheck;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xugh on 2019/4/20.
 */

@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {
}
