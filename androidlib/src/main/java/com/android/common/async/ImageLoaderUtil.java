package com.android.common.async;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by xugh on 2019/4/29.
 */

public class ImageLoaderUtil {

    public static void LoadImageViewForUrl(ImageView imageView, String url) {

        RequestOptions options = new RequestOptions().dontAnimate().placeholder(imageView.getDrawable()).priority(Priority.HIGH).skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        if (imageView != null) {
            imageView.setTag(null);
        }
        Glide.with(imageView.getContext()).load(url).apply(options).into(imageView);
    }
    public static void LoadcircleImage(ImageView imageView, String url) {
        RequestOptions options = new RequestOptions().dontAnimate().placeholder(imageView.getDrawable()).priority(Priority.HIGH).circleCrop().skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        if (imageView != null) {
            imageView.setTag(null);
        }
        Glide.with(imageView.getContext()).load(url).apply(options).into(imageView);
    }
    public static void LoadcircleImage(ImageView imageView, Bitmap bitmap) {
        RequestOptions options = new RequestOptions().dontAnimate().placeholder(imageView.getDrawable()).priority(Priority.HIGH).circleCrop().skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        if (imageView != null) {
            imageView.setTag(null);
        }
        Glide.with(imageView.getContext()).load(bitmap).apply(options).into(imageView);
    }

    public void loadCircleImage(String url, int placeholder, ImageView imageView) {
        if (imageView == null)
            return;
        RequestOptions options = new RequestOptions().dontAnimate().placeholder(placeholder).priority(Priority.HIGH).circleCrop().skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(imageView.getContext()).load(url).apply(options)
                .into(imageView);
    }

}
