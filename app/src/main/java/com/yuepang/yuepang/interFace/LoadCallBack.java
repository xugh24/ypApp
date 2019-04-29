package com.yuepang.yuepang.interFace;

import com.android.common.model.ResultInfo;

/**
 * Created by xugh on 2019/4/25.
 */

public interface LoadCallBack<T> {

    void loadCallBack(CallType callType, int CODE, String msg, T info);

    public enum CallType {
        START, SUCCESS, FAILED, FINISH,
    }
}
