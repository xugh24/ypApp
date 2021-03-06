package com.yuepang.yuepang.protocol;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.android.common.net.HttpConfig;
import com.android.common.utils.GsonUtils;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xugh on 2019/5/5.
 */

public class SubImageProtocol extends JsonProtocol {

    private Map<String, Object> postMap;

    public SubImageProtocol(Context context, LoadCallBack<UserInfo> loadCallBack, Bitmap bitmap) {
        super(context,loadCallBack);
        sub(bitmap);
    }

    @Override
    protected String getUrlToken() {
        return "user/uploadAvatar/";
    }

    private void sub( Bitmap bitmap) {
        final String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/yuepang/";
        try {
            File folder = new File(dir);
            if (!folder.exists()) {
                folder.mkdir();
            }
            String paht = dir + "summertemp" + ".jpg";
            File file = new File(paht);
            if (file.exists()) {
                file.delete();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);
            loadInfo("img", file);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected UserInfo analysis(String st) {
        try {
            JSONObject  jsonObject = new JSONObject(st);
            String data = jsonObject.optString("data");
            return GsonUtils.getInstance().fromJson(data, UserInfo.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void initCfg() {
        getHttpConfig().setPostMap(postMap);
        getHttpConfig().setIspostFild(true);
    }


    private void loadInfo(String img, File file) {
            postMap = new HashMap<>();
            postMap.put(img, file);
    }

    @Override
    protected void creatDataJson(JSONObject reqJson) {

    }
}
