package com.android.common.annotation.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.android.common.model.PayResultInfo;
import com.android.common.utils.LogUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * Created by xugh on 2019/4/20.
 */

public class AnnotateBindViewUtil {


    /**
     * 绑定View
     */
    public static void initBindView(Object currentClass, View sourceView, View.OnClickListener listener) {
        Field[] fields = currentClass.getClass().getDeclaredFields();// 获得本类中所有的对象（父类不能获得）
        for (Field field : fields) {
            if (field.isAnnotationPresent(BindView.class)) {
                bindView(currentClass, field, sourceView, listener);
            } else if (field.isAnnotationPresent(BindBtn.class)) {
                bindView(currentClass, field, sourceView, listener);
            } else if (field.isAnnotationPresent(OnClickView.class)) {//如果
                bindOnclick(field, sourceView, listener);
            } else if (field.isAnnotationPresent(BindViewByTag.class)) {
                bindViewByTag(currentClass, field, sourceView, listener);
            }
        }
    }

    private static void bindViewByTag(Object currentClass, Field field, View sourceView, View.OnClickListener listener) {
        String tag = field.getName();
        BindViewByTag bindView = (BindViewByTag) field.getAnnotation(BindViewByTag.class);
        if (!TextUtils.isEmpty(tag) && sourceView != null && bindView != null) {
            field.setAccessible(true);
            View view = sourceView.findViewWithTag(tag);
            if (view != null) {
                try {
                    boolean clickL = bindView.click();
                    field.set(currentClass, view);
                    if (clickL) view.setOnClickListener(listener);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                LogUtils.e("子 view 为空 子view tag " + tag);
            }
        }
    }

    private static void bindOnclick(final Field field, View sourceView, View.OnClickListener listene) {
        OnClickView onClick = field.getAnnotation(OnClickView.class);
        if (onClick != null) {
            try {
                field.setAccessible(true);
                int[] ids = onClick.value();
                if (ids != null && ids.length > 0) {
                    for (int id : ids) {
                        View view = sourceView.findViewById(id);
                        if (view != null && listene != null) {
                            view.setOnClickListener(listene);
                        }
                    }
                }
            } catch (Exception e) {

            }

        }
    }

    /**
     * 遍历所有的字View
     */
    public static void autoBindClik(View sourceView) {
        if (sourceView instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) sourceView;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View view = viewGroup.getChildAt(i);
                autoBindClik(view);
            }
        }
    }


    private static void bindView(Object currentClass, Field field, View sourceView, View.OnClickListener listene) {
        BindView bindView = (BindView) field.getAnnotation(BindView.class);
        if (bindView != null) {
            int viewId = bindView.id();
            boolean clickL = bindView.click();
            try {
                field.setAccessible(true);
                View view = sourceView.findViewById(viewId);
                if (view != null) {
                    field.set(currentClass, view);
                    if (clickL) view.setOnClickListener(listene);
                } else {
                    LogUtils.e("子 view 为空 子view id " + viewId);
                }
            } catch (Exception var11) {
                var11.printStackTrace();
            }
        }
    }
}
