package com.android.common.annotation.view;

import android.view.View;

import com.android.common.utils.LogUtils;

import java.lang.reflect.Field;
import java.util.logging.Logger;

/**
 * Created by xugh on 2019/4/20.
 */

public class AnnotateBindViewUtil {


    /**
     * 绑定View
     */
    public static void initBindView(Object currentClass, View sourceView, View.OnClickListener clickListener) {
        Field[] fields = currentClass.getClass().getDeclaredFields();// 获得本类中所有的对象（父类不能获得）
        for (Field field : fields) {
            if (field.isAnnotationPresent(BindView.class)) {
                bindView(currentClass, field, sourceView, clickListener);
            }else if(field.isAnnotationPresent(BindBtn.class)){
                bindView(currentClass, field, sourceView, clickListener);
            }
        }
    }

    private static void bindView(Object currentClass, Field field, View sourceView, View.OnClickListener clickListener) {
        BindView bindView = (BindView) field.getAnnotation(BindView.class);
        if (bindView != null) {
            int viewId = bindView.id();
            boolean clickLis = bindView.click();
            try {
                field.setAccessible(true);
                View view = sourceView.findViewById(viewId);
                if (view != null) {
                    field.set(currentClass, view);
                    if (clickLis) {
                        view.findViewById(viewId).setOnClickListener(clickListener);
                    }
                } else {
                    LogUtils.e("子 view 为空 子view id " + viewId);
                }
            } catch (Exception var11) {
                var11.printStackTrace();
            }
        }
    }
}
