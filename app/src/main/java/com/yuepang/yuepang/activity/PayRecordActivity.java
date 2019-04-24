package com.yuepang.yuepang.activity;

import android.view.View;
import android.widget.ListView;

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

public class PayRecordActivity extends BaseLoadFrameActivity {


    private ListView recordLv;

    private List<RecordInfo> recordInfos;

    private View view;

    private RecordAdapter adapter;



    @Override
    public String getMyTittle() {
        return "消费记录";
    }


    @Override
    protected View getMainView() {
        view = View.inflate(this, R.layout.common_list, null);
        recordLv = view.findViewById(R.id.com_lv);
        LogUtils.e("recordInfos" + recordInfos.size());
        adapter = new RecordAdapter(this, recordInfos);
        recordLv.setAdapter(adapter);
        return view;
    }

    @Override
    protected boolean getdata() {
        final GetRecordProtocol protocol = new GetRecordProtocol(PayRecordActivity.this);
//        if (protocol.request() == 200) {
//            recordInfos = (List<RecordInfo>) protocol.getData();
//        }
        recordInfos = TestData.getRecInfos();
        return true;
    }
}
