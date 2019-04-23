package com.yuepang.yuepang.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.AnnotateUtil;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.interFace.CutAreaInterFace;
import com.yuepang.yuepang.model.AreaInfo;

import java.util.List;

/**
 * Created by xugh on 2019/3/26.
 */

public class AreaAdapter extends YueBaseAdapter <AreaInfo> implements AdapterView.OnItemClickListener {


    private CutAreaInterFace cutAreaInterFace;

    public AreaAdapter(BaseActivity activity, List<AreaInfo> areaInfos, CutAreaInterFace cutAreaInterFace) {
        super(activity, areaInfos);
        this.cutAreaInterFace = cutAreaInterFace;
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

    private final class ViewHolder {
        @BindView(id = R.id.tv_name)
        TextView name;

        public ViewHolder(View view) {
            AnnotateUtil.initBindView(this, view);
        }
    }

}
