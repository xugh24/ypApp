package com.yuepang.yuepang.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.android.common.annotation.view.AnnotateBindViewUtil;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.interFace.CutAreaInterFace;
import com.yuepang.yuepang.interFace.LoadCallBack;
import com.yuepang.yuepang.model.AreaInfo;
import com.yuepang.yuepang.protocol.GetBusinessAreaProtocol;

import java.util.List;

/**
 * Created by xugh on 2019/3/26.
 */

public class AreaAdapter extends YueBaseAdapter<AreaInfo> implements AdapterView.OnItemClickListener, LoadCallBack {


    private CutAreaInterFace cutAreaInterFace;

    public AreaAdapter(BaseActivity activity,  CutAreaInterFace cutAreaInterFace) {
        super(activity);
        this.cutAreaInterFace = cutAreaInterFace;
    }


    public void getData() {
        GetBusinessAreaProtocol protocol = new GetBusinessAreaProtocol(activity, this);
        protocol.request();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(activity, R.layout.area_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else if (convertView.getTag() instanceof ViewHolder) {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(getItem(position).getName());
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        cutAreaInterFace.cutAreaInfo(getItem(position));
    }

    @Override
    public void loadCallBack(CallType callType, int CODE, String msg, Object info) {

    }

    private final class ViewHolder {
        TextView name;

        public ViewHolder(View view) {
            AnnotateBindViewUtil.initBindView(this, view, null);
        }
    }

}
