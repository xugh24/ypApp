package com.yuepang.yuepang.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.yuepang.yuepang.Util.AnnotateUtil;
import com.yuepang.yuepang.activity.BaseActivity;
import com.yuepang.yuepang.activity.MainActivity;
import com.yuepang.yuepang.widget.LoadingFrame;

/**
 * Fragment 基类处理方法
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    protected View contentView;

    protected LoadingFrame loadingFrame;

    public BaseFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (contentView == null) {// 创建View
            contentView = inflater.inflate(getLyId(), container, false); // 绑定基类View
            AnnotateUtil.initBindView(this, contentView);// 绑架组件
        }
        init(); // 初始化子类View
        loadingFrame = new LoadingFrame((BaseActivity) getActivity()) {
            @Override
            public boolean load(View loadingView) {
                return getData();
            }

            @Override
            public View createLoadedView() {
                refreshView();
                return contentView;
            }
        };
        loadingFrame.show();
        return loadingFrame;
    }


    /**
     * 刷新View
     */
    protected abstract void refreshView();

    /**
     * 获得服务端数据
     *
     * @return true 获得成功 false 获得失败
     */
    protected abstract boolean getData();

    /**
     * 初始化View
     */
    protected abstract void init();

    /**
     * 展示Frament 调用的的方法
     */
    public  void show(){

    }

    /**
     * 隐藏Frament 调用的的方法
     */
    public  void hide(){

    }

    public abstract int getLyId();


    @Override
    public void onClick(View v) {

    }

    public MainActivity getMainActivity(){
        return (MainActivity) getActivity();
    }
}
