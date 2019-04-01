package com.yuepang.yuepang.Util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
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
     * @param iv   需要设置的图片
     * @param url  图片地址
     * @param activity 页面
     * @Description 动态加载图片
     */
    public static void loadHeadForUrl(final ImageView iv, String url, final BaseActivity activity) {
        if (TextUtils.isEmpty(url) || iv == null || activity == null) {
            return;
        }
        AsyncImageLoader.getInstance(activity).loadDrawable(url, new AsyncImageLoader.ImageCallback() {
            @Override
            public void onLoadedFromMemCache(Drawable drawable, String imageUrl) {
                Bitmap bm= ((BitmapDrawable) drawable).getBitmap();
                iv.setImageBitmap(toRoundBitmap(bm));


            }

            @Override
            public void onLoadedFromBackground(final Drawable drawable, String imageUrl) {

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bm= ((BitmapDrawable) drawable).getBitmap();
                        iv.setImageBitmap(toRoundBitmap(bm));
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


    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        LogUtils.e("toRoundBitmap :" + width + "," + height);
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return output;
    }
}
