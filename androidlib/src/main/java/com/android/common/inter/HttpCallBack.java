package com.android.common.inter;

import com.android.common.model.ResultInfo;

import java.lang.reflect.Type;

/**
 * Created by xugh on 2019/4/23.
 */

public interface HttpCallBack<T> {

    /**
     * 协议请求开始，可以在本处展示load页面
     */
    void onStart();

    public void onFailed(ResultInfo info);

    public void logout();

    public void onSuccess(ResultInfo info);

    public void onFinish();

}
