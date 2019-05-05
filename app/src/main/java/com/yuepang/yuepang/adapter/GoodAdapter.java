package com.yuepang.yuepang.adapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.common.annotation.view.AnnotateBindViewUtil;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.GoodDetailActivity;
import com.yuepang.yuepang.async.CommonTaskExecutor;
import com.yuepang.yuepang.interFace.AreaInterFace;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.AreaInfo;
import com.yuepang.yuepang.model.GoodInfo;
import com.yuepang.yuepang.protocol.GetBusinessAreaProtocol;
import com.yuepang.yuepang.protocol.HandpickInfoProtocol;
import com.yuepang.yuepang.test.TestData;

/**
 * Created by xugh on 2019/4/2.
 */

public class GoodAdapter extends YueBaseAdapter <GoodInfo> implements AdapterView.OnItemClickListener, LoadCallBack {

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
    public void getData() {

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

    @Override
    public void loadCallBack(CallType callType, int CODE, String msg, Object info) {
        setList( TestData.getGoods());
        notifyDataSetChanged();
    }

    private final class ViewHolder {
        ImageView ivPic;

        TextView name;

        TextView msg;

        public ViewHolder(View view) {
            AnnotateBindViewUtil.initBindView(this,view,null);
        }
    }
}
