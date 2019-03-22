package com.yuepang.yuepang.Util;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.async.AsyncImageLoader;

/**
 */

public class DrawableUtil {

    /**
     * @param iv   需要设置的图片
     * @param url  图片地址
     * @param activity 页面
     * @Description 动态加载图片
     */
    public static void loadImageForUrl(final ImageView iv, String url, final BaseActivity activity) {
        if (TextUtils.isEmpty(url) || iv == null || activity == null) {
            return;
        }
        AsyncImageLoader.getInstance(activity).loadDrawable(url, new AsyncImageLoader.ImageCallback() {
            @Override
            public void onLoadedFromMemCache(Drawable drawable, String imageUrl) {
                iv.setImageDrawable(drawable);
            }

            @Override
            public void onLoadedFromBackground(final Drawable drawable, String imageUrl) {

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iv.setImageDrawable(drawable);
                    }
                });
            }
        });
    }

    /**
     * @param iv    需要设置的图片
     * @param url   图片地址
     * @param activity 页面
     * @Description 动态加载圆角图片
     */
    public static void loadRadImageForUrl(final ImageView iv, String url,  final BaseActivity activity) {
        if (TextUtils.isEmpty(url) || iv == null || activity == null) {
            return;
        }
        AsyncImageLoader.getInstance(activity).loadDrawable(url, new AsyncImageLoader.ImageCallback() {

            @Override
            public void onLoadedFromMemCache(Drawable drawable, String imageUrl) {
                iv.setImageResource(R.drawable.ic_mask);
                iv.setBackground(drawable);
            }

            @Override
            public void onLoadedFromBackground(final Drawable drawable, String imageUrl) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iv.setImageResource(R.drawable.ic_mask);
                        iv.setBackground(drawable);
                    }
                });
            }
        });
    }
}
