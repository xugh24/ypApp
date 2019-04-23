/*
 * File Name: ImageMemCache.java 
 * History:
 */
package com.android.common.cache;

import android.graphics.Bitmap;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ImageMemCache {
    // ==========================================================================
    // Constants
    // ==========================================================================
    public static final int DEFAULT_IMAGE_CACHE_SIZE = 10;

    // ==========================================================================
    // Fields
    // ==========================================================================

    public static final Map<Object, WeakReference<Bitmap>> sImageCache = Collections.synchronizedMap(new HashMap<Object, WeakReference<Bitmap>>(
            DEFAULT_IMAGE_CACHE_SIZE));


    public static void clear() {
        synchronized (sImageCache) {
            sImageCache.clear();
        }
    }


    public static void putImage(Object key, Bitmap bmp) {
        sImageCache.put(key, new WeakReference<Bitmap>(bmp));
    }

    public static Bitmap getImage(Object key) {
        Bitmap bmp = null;
        WeakReference<Bitmap> ref = sImageCache.get(key);
        if (null != ref) {
            bmp = ref.get();
        }
        return bmp;
    }

    public static void removeImage(Object key) {
        synchronized (sImageCache) {
            sImageCache.remove(key);
        }
    }

    // ==========================================================================
    // Inner/Nested Classes
    // ==========================================================================

}
