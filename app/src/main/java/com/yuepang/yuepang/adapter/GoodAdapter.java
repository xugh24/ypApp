package com.yuepang.yuepang.adapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.AnnotateUtil;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.GoodDetailActivity;
import com.yuepang.yuepang.async.CommonTaskExecutor;
import com.yuepang.yuepang.interFace.AreaInterFace;
import com.yuepang.yuepang.model.AreaInfo;
import com.yuepang.yuepang.model.GoodInfo;
import com.yuepang.yuepang.protocol.HandpickInfoProtocol;
import com.yuepang.yuepang.test.TestData;

import java.util.List;

/**
 * Created by xugh on 2019/4/2.
 */

public class GoodAdapter extends YueBaseAdapter <GoodInfo> implements AdapterView.OnItemClickListener {

    private int areaId;

    private AreaInterFace interFace;

    public GoodAdapter(BaseActivity activity, AreaInterFace interFace) {
        super(activity);
        this.interFace = interFace;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(activity, R.layout.good_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        if (convertView.getTag() instanceof ViewHolder) {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }


    /**
     * 请求数据
     */
    public boolean getData() {
        CommonTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                HandpickInfoProtocol protocol = new HandpickInfoProtocol(activity, areaId);
//                if (protocol.request() == 200) {
//                    // TODO 接口参数
//                }
            }
        });
        if (interFace != null) { // 通知主页面刷新View
            interFace.callAreaInfo(TestData.getinfos(), TestData.getMerinfos(), TestData.getinfos().get(0));
        }
        setList( TestData.getGoods());
        notifyDataSetChanged();
        return true;
    }

    /**
     * 切换商圈 重新刷新数据
     */
    public void refresh(AreaInfo info) {
        areaId = info.getId();
        getData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(activity, GoodDetailActivity.class);
        intent.putExtra(GoodDetailActivity.GOODINFO, getItem(position));
        activity.startActivity(intent);
    }

    private final class ViewHolder {
        @BindView(id = R.id.iv_good)
        ImageView ivPic;

        @BindView(id = R.id.goodname)
        TextView name;

        @BindView(id = R.id.goodmsg)
        TextView msg;

        public ViewHolder(View view) {
            AnnotateUtil.initBindView(this, view);
        }
    }
}
