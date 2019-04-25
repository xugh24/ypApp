//package com.android.common.async;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.os.Process;
//import android.text.TextUtils;
//
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.LinkedList;
//
//
///**
// * 获取图片线程线程池
// */
//
//public class AsyncImageLoader extends ThreadPool.Workgroup {
//
//    private static final int MAX_LARGE_IMAGE_NUM = 20;
//    private static final int MAX_ICON_NUM = 50;
//    private ReferenceKeeper mLargeImageCacheKeep = new ReferenceKeeper(MAX_LARGE_IMAGE_NUM); // 大图片循环队列
//    private ReferenceKeeper mIconCacheKeep = new ReferenceKeeper(MAX_ICON_NUM);// 小图片循环队列
//    private static AsyncImageLoader sInstance;
//    public Context mContext;
//    /**
//     * 下载、保存图片任务队列
//     */
//    private LinkedList<CommonTask> mTaskQueue;//
//
//    public static synchronized AsyncImageLoader getInstance(Context context) {
//        if (sInstance == null) {
//            sInstance = new AsyncImageLoader(context);
//        }
//        return sInstance;
//    }
//
//    private AsyncImageLoader(Context context) {
//        mContext = context;
//        mTaskQueue = new LinkedList<CommonTask>();
//        ThreadPool.registerWorkgroup(this);
//    }
//
//    private void keepImage(Drawable d, boolean isLargeImage) {
//        if (isLargeImage) {
//            mLargeImageCacheKeep.keep(d);
//        } else {
//            mIconCacheKeep.keep(d);
//        }
//    }
//
//
//    public void loadDrawable(final String imageUrl, final ImageCallback imageCallback) {
//        loadDrawable(imageUrl, imageCallback, false);
//    }
//
//
//    /**
//     * 根据bitmap 三级缓存思想获得图片依次重内存、本地、网络按照顺序获得获得图片
//     */
//    public void loadDrawable(final String imageUrl, final ImageCallback imageCallback, boolean isLargeImage) {
//        if (TextUtils.isEmpty(imageUrl)) {// 判断图片地址是否为空
//            LogUtils.w("Load image url is null!!");
//            return;
//        }
//        Bitmap bmp = ImageMemCache.getIcon(imageUrl);// 重内存中获取图片
//        if (bmp != null) {
//            if (imageCallback != null) {
//                imageCallback.onLoadedFromMemCache(new BitmapDrawable(bmp), imageUrl);
//            }
//            return;
//        }
//        asyncLoad(imageUrl, imageCallback, isLargeImage);// 获得是吧继续获取
//    }
//
//    /**
//     * @param url 图片地址
//     * @return 资源
//     * @throws IOException
//     */
//    public Drawable loadImageFromUrl(String url) throws Exception {
//        if (TextUtils.isEmpty(url)) {
//            return null;
//        }
//        FileInputStream fis = null;
//        try {
//            if (SysUtils.isSDCardAvailable()) {// sd卡存在并且控件足够
//                String fileName = String.valueOf(url.hashCode());
//                File f = new File(SysUtils.getImageCacheDir(mContext), fileName);
//                if (f.exists()) {// 如果内存中图片存下则直接返回
//                    fis = new FileInputStream(f);
//                    return Drawable.createFromStream(fis, null);
//                }else{
//                    f.createNewFile();
//                }
//            }
//            return MyOkHttpEngine.createGetRequest(mContext, url);// 重网络获取图片
//        } catch (Exception e) {
//            LogUtils.e(e);
//        } finally {
//            if (fis != null)
//                fis.close();
//        }
//        return null;
//
//    }
//
//
//    private void asyncLoad(String key, ImageCallback item, boolean isLargeImage) {
//        final CommonTask task = new CommonTask(key, item, isLargeImage);
//        addTask(task);
//    }
//
//    @Override
//    public int maxWorkers() {
//        return 5;
//    }
//
//    @Override
//    public boolean isTaskShared() {
//        return false;
//    }
//
//    @Override
//    protected boolean enqueueTask(BackgroundTask<?> task) {
//        if (task instanceof CommonTask) {
//            synchronized (mTaskQueue) {
//                return mTaskQueue.add((CommonTask) task);
//            }
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    public BackgroundTask<?> dequeueTask() {
//        synchronized (mTaskQueue) {
//            return mTaskQueue.poll();
//        }
//    }
//
//    public interface ImageCallback {
//
//        void onLoadedFromMemCache(Drawable drawable, String imageUrl);
//
//        void onLoadedFromBackground(Drawable drawable, String imageUrl);
//    }
//
//    public class CommonTask extends BackgroundTask<Void> {
//
//        private String mImgUrl;
//        private ImageCallback mCallback;
//        private boolean isLargeImage;
//
//        public CommonTask(String imgUrl, ImageCallback cb, boolean isLargeImg) {
//            mImgUrl = imgUrl;
//            mCallback = cb;
//            isLargeImage = isLargeImg;
//        }
//
//        @Override
//        protected Void doInBackground() {
//            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
//            Drawable drawable = null;
//            try {
//                drawable = loadImageFromUrl(mImgUrl);
//                if (drawable != null) {
//                    ImageMemCache.putIcon(mImgUrl, ((BitmapDrawable) drawable).getBitmap());
//                    keepImage(drawable, isLargeImage);
//                    if (mCallback != null) {
//                        mCallback.onLoadedFromBackground(drawable, mImgUrl);
//                    }
//                }
//            } catch (Exception e) {
//                LogUtils.e(e);
//            }
//            return null;
//        }
//
//        @Override
//        protected void onDoneInBackground(Void result) {
//
//        }
//
//        @Override
//        protected boolean match(Object... params) {
//            return params != null && params.length >= 2 && mImgUrl.equals(params[0]) && params[1] == mCallback;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (o != null && o instanceof CommonTask) {
//                CommonTask temp = (CommonTask) o;
//                return temp.mImgUrl != null && temp.mImgUrl.equals(mImgUrl) && temp.mCallback == mCallback;
//            }
//            return super.equals(o);
//        }
//    }
//
//    private static class ReferenceKeeper extends CircularQueue<Object> {
//
//        public ReferenceKeeper(int capacity) {
//            super(capacity);
//        }
//
//        /**
//         * Keep a reference. This may kick out one old reference if the reference queue is full.
//         *
//         * @param ref New reference to keep
//         */
//        public void keep(Object ref) {
//            remove(ref);
//            offer(ref);
//        }
//
//    }
//}
