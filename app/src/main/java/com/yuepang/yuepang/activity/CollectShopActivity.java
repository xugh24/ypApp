package com.yuepang.yuepang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.android.common.annotation.view.BindViewByTag;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.adapter.MerchantAdapter;
import com.yuepang.yuepang.control.UserCentreControl;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.AreaInfo;
import com.yuepang.yuepang.model.GoodInfo;
import com.yuepang.yuepang.model.MerchantInfo;
import com.yuepang.yuepang.protocol.GetShopListProtocol;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xugh on 2019/5/6.
 */

public class CollectShopActivity extends BaseActivity implements  LoadCallBack<List<MerchantInfo>> {
    @BindViewByTag
    private ListView lvMer;

    private MerchantAdapter merchantAdapter;

    private List<MerchantInfo> allInfo;

    private int size;

    private int infosize;

    private JSONArray jsonArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allInfo = new ArrayList<>();
        merchantAdapter = new MerchantAdapter(this);
        lvMer.setAdapter(merchantAdapter);
        jsonArray = getDataControl().getCollect();
        if(jsonArray!=null && jsonArray.length()>0){
            load();
        }
    }

    private void load() {
        List<AreaInfo> areaInfos = UserCentreControl.getInstance().getList();
        LogUtils.e("list.size()=============" + areaInfos.size());
        if (areaInfos != null && areaInfos.size() > 0) {
            size = areaInfos.size();
            for (AreaInfo info :areaInfos) {
                LogUtils.e("info  " + info.getId());
                new GetShopListProtocol(this, this, info.getId()).request();
            }
        }
    }


    @Override
    public String getMyTittle() {
        return "我的收藏";
    }

    @Override
    protected int getContentViewId() {
        return R.layout.my_shop_ly;
    }



    @Override
    public void loadCallBack(final CallType callType, int CODE, String msg,final List<MerchantInfo> infos) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(callType.equals(CallType.SUCCESS)){
                    LogUtils.e("infos.size()" + infos.size());
                    allInfo.addAll(infos);
                    infosize++;
                    if (infosize == size) {
                        List<MerchantInfo> myInfo = new ArrayList<>();
                        for (MerchantInfo info : allInfo){
                            for(int i = 0; i<jsonArray.length();i++){
                                if(info.getId() == jsonArray.optInt(i)){
                                    myInfo.add(info);
                                }
                            }
                        }
                        dismissLoadingDialogSafe();
                        merchantAdapter.setList(myInfo);
                        merchantAdapter.notifyDataSetChanged();
                    }
                }

                if(callType.equals(CallType.START)){
                    showLoadingDialogSafe(false);
                }
            }
        });
    }

    public static void tothisAvtivity(Context context) {
        Intent intent = new Intent(context,CollectShopActivity.class);
        context.startActivity(intent);
    }

}
