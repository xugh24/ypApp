package com.yuepang.yuepang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.android.common.annotation.view.BindViewByTag;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.adapter.RecordAdapter;
import com.yuepang.yuepang.async.CommonTaskExecutor;
import com.yuepang.yuepang.model.RecordInfo;
import com.yuepang.yuepang.protocol.GetRecordProtocol;
import com.yuepang.yuepang.test.TestData;

import java.util.List;

/**
 * Created by xugh on 2019/3/27.
 */

public class PayRecordActivity extends BaseActivity {

    @BindViewByTag
    private ListView recordLv; // listView

    private RecordAdapter adapter;

    @Override
    public String getMyTittle() {
        return "消费记录";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new RecordAdapter(this);// 创建设配器
        recordLv.setAdapter(adapter); // 添加设配器
    }

    @Override
    protected int getContentViewId() {
        return R.layout.common_list;
    }

    public static void toThisActivity(Context context) {
        Intent intent1 = new Intent(context, PayRecordActivity.class);
        context.startActivity(intent1);
    }
}
