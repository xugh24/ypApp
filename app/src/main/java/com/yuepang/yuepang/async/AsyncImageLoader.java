package com.yuepang.yuepang.async;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;

import com.yuepang.yuepang.Util.CircularQueue;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.Util.SysUtils;
import com.yuepang.yuepang.net.HttpEngine;
import com.yuepang.yuepang.widget.ImageMemCache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

public class AsyncImageLoader extends ThreadPool.Workgroup {

    private static final int MAX_LARGE_IMAGE_NUM = 20;
    private static final int MAX_ICON_NUM = 50;
    private ReferenceKeeper mLargeImageCacheKeep = new ReferenceKeeper(MAX_LARGE_IMAGE_NUM);
    private ReferenceKeeper mIconCacheKeep = new ReferenceKeeper(MAX_ICON_NUM);
    private static AsyncImageLoader sInstance;
    public Context mContext;
    private LinkedList<CommonTask> mTaskQueue;

    public static synchronized AsyncImageLoader getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new AsyncImageLoader(context);
        }
        return sInstance;
    }

    private AsyncImageLoader(Context context) {
        mContext = context;
        mTaskQueue = new LinkedList<CommonTask>();
        ThreadPool.registerWorkgroup(this);
    }


    private void keepImage(Drawable d, boolean isLargeImage) {
        if (isLargeImage) {
            mLargeImageCacheKeep.keep(d);
        } else {
            mIconCacheKeep.keep(d);
        }
    }

    // 通过名称来获得图片
    public Bitmap getLocalBitmap(String name) {
        if (!TextUtils.isEmpty(name)) {
            File file = new File(getImageCacheDir(), String.valueOf(name.hashCode()));
            if (file != null && file.exists()) {
                try {
                    Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath());
                    if (bmp != null) {
                        return bmp;
                    } else {
                        file.delete();
                    }
                } catch (OutOfMemoryError e) {
                    LogUtils.e(e);
                } catch (Exception e) {
                    file.delete(); // 如果加载失败删除本地文件，下次重新下载
                    LogUtils.e(e);
                }
            }
        }
        return null;
    }



    private String getImageCacheDir() {
        String path = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            path = Environment.getExternalStorageDirectory() + "/usercenter/img_cache";
        } else {
            path = mContext.getCacheDir().getAbsolutePath();
        }
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return path;
    }

    public void loadDrawable(final String imageUrl, final ImageCallback imageCallback) {
        loadDrawable(imageUrl, imageCallback, false);
    }

    public void loadDrawable(final String imageUrl, final ImageCallback imageCallback, boolean isLargeImage) {
        if (TextUtils.isEmpty(imageUrl)) {
            LogUtils.w("Load image url is null!!");
            return;
        }
        if (imageUrl != null) {
            Bitmap bmp = ImageMemCache.getIcon(imageUrl);
            if (bmp != null) {
                if (imageCallback != null) {
                    imageCallback.onLoadedFromMemCache(new BitmapDrawable(bmp), imageUrl);
                }
                return;
            }
        }
        asyncLoad(imageUrl, imageCallback, isLargeImage);
    }

    /**
     * 
     * 
     * @param url
     * 
     * 
     * @return
     * @throws IOException
     */
    public Drawable loadImageFromUrl(String url) throws Exception {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            if (SysUtils.isSDCardAvailable()) {// sd卡存在并且控件足够
                String fileName = String.valueOf(url.hashCode());
                File f = new File(getImageCacheDir(), fileName);
                if (f.exists()) {
                    fis = new FileInputStream(f);
                    return Drawable.createFromStream(fis, null);
                }
            }

            InputStream is = HttpEngine.createGetRequest(mContext, url);
            Drawable drawable = Drawable.createFromStream(is, null);
            if (is != null) {
                is.close();
            }
            fos = new FileOutputStream(new File(getImageCacheDir(), String.valueOf(url.hashCode())));
            ((BitmapDrawable) drawable).getBitmap().compress(CompressFormat.PNG, 100, fos);

            return drawable;
        } catch (Exception e) {
            LogUtils.e(e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                }catch (Exception e){}
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e2) {
            }

        }
        return null;

    }





    public void cancel(String url, ImageCallback callback) {
        if (null == callback) {
            return;
        }
        synchronized (mTaskQueue) {
            if (!cancelExecutingTasks(false, url, callback)) {
                CommonTask toRemove = new CommonTask(url, callback, false);
                mTaskQueue.remove(toRemove);
            }
        }
    }

    private void asyncLoad(String key, ImageCallback item, boolean isLargeImage) {
        final CommonTask task = new CommonTask(key, item, isLargeImage);
        addTask(task);
    }

    @Override
    public int maxWorkers() {
        return 5;
    }

    @Override
    public boolean isTaskShared() {
        return false;
    }

    @Override
    protected boolean enqueueTask(BackgroundTask<?> task) {
        if (task instanceof CommonTask) {
            synchronized (mTaskQueue) {
                return mTaskQueue.add((CommonTask) task);
            }
        } else {
            return false;
        }
    }

    @Override
    public BackgroundTask<?> dequeueTask() {
        synchronized (mTaskQueue) {
            return mTaskQueue.poll();
        }
    }

    public interface ImageCallback {

        void onLoadedFromMemCache(Drawable drawable, String imageUrl);

        void onLoadedFromBackground(Drawable drawable, String imageUrl);
    }

    public class CommonTask extends BackgroundTask<Void> {

        private String mImgUrl;
        private ImageCallback mCallback;
        private boolean isLargeImage;

        public CommonTask(String imgUrl, ImageCallback cb, boolean isLargeImg) {
            mImgUrl = imgUrl;
            mCallback = cb;
            isLargeImage = isLargeImg;
        }

        @Override
        protected Void doInBackground() {
            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
            Drawable drawable = null;
            try {
                drawable = loadImageFromUrl(mImgUrl);

                if (drawable != null) {
                    ImageMemCache.putIcon(mImgUrl, ((BitmapDrawable)drawable).getBitmap());
                    keepImage(drawable, isLargeImage);
                    if (mCallback != null) {
                        mCallback.onLoadedFromBackground(drawable, mImgUrl);
                    }
                }
            } catch (Exception e) {
                LogUtils.e(e);
            }
            return null;
        }

        @Override
        protected void onDoneInBackground(Void result) {
        }

        @Override
        protected boolean match(Object... params) {
            return params != null && params.length >= 2 && mImgUrl.equals(params[0]) && params[1] == mCallback;
        }

        @Override
        public boolean equals(Object o) {
            if (o != null && o instanceof CommonTask) {
                CommonTask temp = (CommonTask) o;
                return temp.mImgUrl != null && temp.mImgUrl.equals(mImgUrl) && temp.mCallback == mCallback;
            }
            return super.equals(o);
        }
    }

    private static class ReferenceKeeper extends CircularQueue<Object> {

        public ReferenceKeeper(int capacity) {
            super(capacity);
        }

        /**
         * Keep a reference. This may kick out one old reference if the reference queue is full.
         * 
         * @param ref
         *            New reference to keep
         */
        public void keep(Object ref) {
            remove(ref);
            offer(ref);
        }

    }
}
