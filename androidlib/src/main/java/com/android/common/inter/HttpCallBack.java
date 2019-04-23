package com.android.common.inter;

import com.android.common.model.ResultInfo;

import java.lang.reflect.Type;

/**
 * Created by xugh on 2019/4/23.
 */

public interface HttpCallBack<T> {

    int HTTP_OK = 200;

    int LOGIN_INVALID =444;

    public void onStart();

    public void callBack(ResultInfo info);

    public void logout();

    public void onSuccess(ResultInfo<T> info);

    public void onFinish();

    public Type getType();

}
