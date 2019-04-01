package com.yuepang.yuepang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.adapter.RecordAdapter;
import com.yuepang.yuepang.async.CommonTaskExecutor;
import com.yuepang.yuepang.model.RecordInfo;
import com.yuepang.yuepang.protocol.GetRecordProtocol;
import com.yuepang.yuepang.widget.LoadingFrame;

import java.util.List;

/**
 * Created by xugh on 2019/3/27.
 */

public class PayRecordActivity extends BaseLoadFrameActivity {


    private ListView recordLv;

    private List<RecordInfo> recordInfos;

    private View view;

    private RecordAdapter adapter;


    @Override
    protected String getMyRTitle() {
        return null;
    }

    @Override
    protected String getMyTittle() {
        return "消费记录";
    }


    @Override
    protected View getMainView() {
        view = View.inflate(this, R.layout.common_list, null);
        recordLv = view.findViewById(R.id.com_lv);
        adapter = new RecordAdapter(this, recordInfos);
        recordLv.setAdapter(adapter);
        return view;
    }

    @Override
    protected boolean getdata() {
        CommonTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                final GetRecordProtocol protocol = new GetRecordProtocol(PayRecordActivity.this);
                if(protocol.request() == 200){
                    recordInfos = (List<RecordInfo>) protocol.getData();
                }
            }
        });
        return false;
    }
}
