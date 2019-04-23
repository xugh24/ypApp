package com.android.common.inter;

import com.android.common.net.HttpConfig;

/**
 */
public interface HttpEngine {
    void startLoad(HttpConfig httpConfig, HttpCallBack httpCallBack);

//    void download(HttpConfig httpConfig, DownloadListener downloadListener);
}
