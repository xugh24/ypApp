package com.android.common.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.common.utils.DeviceUtils;

/**
 * Created by xugh on 2019/4/17.
 * <p>
 * 图片三级缓存的内存级别实现类之一
 */

public class BitmapLruCache extends LruCache<String, Bitmap> {

    private static BitmapLruCache lruCache;

    private static int max;

    private static int proportion = 10;

    public static BitmapLruCache getInstance(Context context) {
        if (lruCache == null) {
            synchronized (BitmapLruCache.class) {
                if (lruCache == null) {
                    lruCache = new BitmapLruCache(context);
                }
            }
        }
        return lruCache;
    }

    /**

     */
    public BitmapLruCache(Context context) {
        super(DeviceUtils.getMemoryCacheSize(context) / proportion);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        // 重写获取某个节点的内存大小，不写默认返回1
        return value.getByteCount() / 1024;
    }

    // 某节点被移除后调用该函数
    @Override
    protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
        super.entryRemoved(evicted, key, oldValue, newValue);
    }


}
