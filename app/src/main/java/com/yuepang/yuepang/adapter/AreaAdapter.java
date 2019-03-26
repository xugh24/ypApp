package com.yuepang.yuepang.adapter;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.AnnotateUtil;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.model.AreaInfo;

import java.util.List;

/**
 * Created by xugh on 2019/3/26.
 */

public class AreaAdapter extends YueBaseAdapter {

    private List<AreaInfo> areaInfos;

    public AreaAdapter(BaseActivity activity, List<AreaInfo> list) {
        super(activity, list);
        this.areaInfos = list;
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
        holder.name.setText(areaInfos.get(position).getName());
        return convertView;
    }

    private final class ViewHolder {
        @BindView(id = R.id.tv_name)
        TextView name;

        public ViewHolder(View view) {
            AnnotateUtil.initBindView(this, view);
        }
    }

}
