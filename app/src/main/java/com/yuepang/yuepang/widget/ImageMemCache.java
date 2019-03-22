package com.yuepang.yuepang.widget;

import android.graphics.Bitmap;
import android.graphics.Movie;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class ImageMemCache {
    // ==========================================================================
    // Constants
    // ==========================================================================
    public static final int DEFAULT_ICON_CACHE_SIZE = 20;
    public static final int DEFAULT_IMAGE_CACHE_SIZE = 10;
    public static final int DEFAULT_GIF_ICON_CACHE_SIZE = 20;

    // ==========================================================================
    // Fields
    // ==========================================================================
    public static final Map<Object, WeakReference<Bitmap>> sIconCache = Collections.synchronizedMap(new HashMap<Object, WeakReference<Bitmap>>(
            DEFAULT_ICON_CACHE_SIZE));
    public static final Map<Object, WeakReference<Movie>> sGifIconCache = Collections.synchronizedMap(new HashMap<Object, WeakReference<Movie>>(
            DEFAULT_GIF_ICON_CACHE_SIZE));
    public static final Map<Object, WeakReference<Bitmap>> sImageCache = Collections.synchronizedMap(new HashMap<Object, WeakReference<Bitmap>>(
            DEFAULT_IMAGE_CACHE_SIZE));

    public static final Queue<String> sKeys = new ArrayBlockingQueue<String>(DEFAULT_ICON_CACHE_SIZE);
    // ==========================================================================
    // Constructors
    // ==========================================================================

    // ==========================================================================
    // Getters
    // ==========================================================================

    // ==========================================================================
    // Setters
    // ==========================================================================

    // ==========================================================================
    // Methods
    // ==========================================================================
    public static void clear() {
        synchronized (sIconCache) {
            sIconCache.clear();
        }
        
        synchronized (sGifIconCache) {
            sGifIconCache.clear();
        }
        synchronized (sImageCache) {
            sImageCache.clear();
        }
    }

    public static void putIcon(String key, Bitmap bmp) {
        synchronized (sIconCache) {
            if (sIconCache.size() > DEFAULT_ICON_CACHE_SIZE) {
                sIconCache.remove(sKeys.poll());
            }
            sIconCache.put(key, new WeakReference<Bitmap>(bmp));
            sKeys.offer(key);
        }

    }

    public static Bitmap getIcon(Object key) {
        Bitmap bmp = null;
        WeakReference<Bitmap> ref = null;
        if (sIconCache.containsKey(key)) {
            ref = sIconCache.get(key);
        } else {
            return null;
        }
        if (null != ref) {
            bmp = ref.get();
        }
        return bmp;
    }


    public static void removeIcon(Object key) {
        synchronized (sIconCache) {
            sIconCache.remove(key);
        }
    }
    
    public static void putGifIcon(Object key, Movie d) {
        sGifIconCache.put(key, new WeakReference<Movie>(d));
    }
    
    public static Movie getGifIcon(Object key) {
        Movie d = null;
        WeakReference<Movie> ref = null;
        if (sGifIconCache.containsKey(key)) {
            ref = sGifIconCache.get(key);
        } else {
            return null;
        }
        if (null != ref) {
            d = ref.get();
        }
        return d;
    }

    public static void removeGifIcon(Object key) {
        synchronized (sGifIconCache) {
            sGifIconCache.remove(key);
        }
    }

//    public static boolean containsDownLoadKey(Object key) {
//        return sDownLoadCache.containsKey(key);
//    }
//
//    public static void putDownLoad(Object key, Drawable d) {
//        sDownLoadCache.put(key, new WeakReference<Drawable>(d));
//    }
//
//    public static Drawable getDownLoad(Object key) {
//        Drawable d = null;
//        WeakReference<Drawable> ref = sDownLoadCache.get(key);
//        if (null != ref) {
//            d = ref.get();
//        }
//        return d;
//    }
//
//    public static void keepDownLoad(Drawable d) {
//        synchronized (sDownLoadRefKeeper) {
//            sDownLoadRefKeeper.keep(d);
//        }
//    }

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
