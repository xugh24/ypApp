package com.yuepang.yuepang.protocol;

import android.content.Context;

import com.android.common.enums.HttpType;
import com.android.common.model.ResultInfo;
import com.android.common.protocol.BaseFirstProtocol;
import com.yuepang.yuepang.Util.DomainUtil;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.control.UserCentreControl;
import com.yuepang.yuepang.interFace.LoadCallBack;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xugh on 2019/4/25.
 */

public abstract class JsonProtocol<T> extends BaseFirstProtocol {

    private JSONObject reqJson;

    private LoadCallBack<T> callBack;

    private ResultInfo resultInfo;

    private T mData;

    public JsonProtocol(Context context, LoadCallBack<T> callBack) {
        super(context);
        this.callBack = callBack;
    }


    @Override
    protected JSONObject getRequestJson() {
        reqJson = new JSONObject();
        try {
            creatDataJson(reqJson);
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return reqJson;
    }


    @Override
    public void onFailed(ResultInfo info) {
        LogUtils.e("msg"+info.getMsg());
        callBack.loadCallBack(LoadCallBack.CallType.FAILED, info.getCode(), info.getMsg(), null);
    }

    @Override
    public void onStart() {
        callBack.loadCallBack(LoadCallBack.CallType.START, 0, null, null);
    }

    @Override
    public void logout() {
        callBack.loadCallBack(LoadCallBack.CallType.FAILED, 0, null, null);
    }

    @Override
    public void onSuccess(ResultInfo info) {
        mData = analysis(info.getData());
        callBack.loadCallBack(LoadCallBack.CallType.SUCCESS, info.getCode(), info.getMsg(), mData);
    }

    protected  T analysis(String data){
        return null;
    }

    @Override
    public void onFinish() {
        callBack.loadCallBack(LoadCallBack.CallType.FINISH, 0, null, null);
    }



    @Override
    public HttpType getHttpType() {
        return HttpType.POST;
    }


    @Override
    protected String getUrl() {
        return DomainUtil.MAIN_URL + getUrlToken();
    }

    /**
     * 获得接口名称
     */
    protected abstract String getUrlToken();

    /**
     * 创建请求数据
     */
    protected abstract void creatDataJson(JSONObject reqJson);


    public CharSequence getCodeDesc() {
        return null;
    }

    public String getData() {
        return null;
    }

    @Override
    public Map<String, String> getHeadMap() {
        Map map = new HashMap<String,String>();
        map.put("token", UserCentreControl.getInstance().getToken());
        return map;
    }
}
