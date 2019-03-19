package com.yuepang.yuepang.widget;

import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.activity.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RefreshListView extends ListView {

    private View headerView;// headerView
    private ImageView iv_arrow;
    private ProgressBar pb_rotate;
    private TextView tv_state, tv_time;
    private int headerViewHeight;// headerView高

    private int downY;// 按下时y坐标

    private final int PULL_REFRESH = 0;// 下拉刷新的状态
    private final int RELEASE_REFRESH = 1;// 松开刷新的状态
    private final int REFRESHING = 2;// 正在刷新的状态
    private int currentState = PULL_REFRESH;
    private long currentTime;
    private RotateAnimation upAnimation, downAnimation;

    private BaseActivity mActivity;

    public RefreshListView(BaseActivity activity) {
        super(activity);
        this.mActivity = activity;
        setFastScrollEnabled(false);
        setVerticalScrollBarEnabled(false);
        setDivider(new ColorDrawable(0xffffff));
        setDividerHeight(0);
        setCacheColorHint(0);
        setSelector(new ColorDrawable(0x00ffffff));
        init();
    }

    private void init() {
        initHeaderView();
        initRotateAnimation();
    }

    /**
     * 初始化headerView
     */
    @SuppressWarnings("deprecation")
    private void initHeaderView() {
        headerView = View.inflate(mActivity, R.layout.azuc_layout_header, null);
        iv_arrow = (ImageView) headerView.findViewWithTag("azplg_layout_header_iv_arrow");
        pb_rotate = (ProgressBar) headerView.findViewWithTag("azplg_layout_header_pb_rotate");
        tv_state = (TextView) headerView.findViewWithTag("azplg_layout_header_tv_state");
        tv_time = (TextView) headerView.findViewWithTag("azplg_layout_header_tv_time");
        headerView.measure(0, 0);// 主动通知系统去测量该view;
        headerViewHeight = headerView.getMeasuredHeight();
        headerView.setPadding(0, -headerViewHeight, 0, 0);
        addHeaderView(headerView);
    }

    /**
     * 旋转动画
     */
    private void initRotateAnimation() {
        upAnimation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        upAnimation.setDuration(300);
        upAnimation.setFillAfter(true);
        downAnimation = new RotateAnimation(-180, -360, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        downAnimation.setDuration(300);
        downAnimation.setFillAfter(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) ev.getY();
                LogUtils.e("RefreshListView  Y" + downY);
                break;
            case MotionEvent.ACTION_MOVE:
                if (currentState == REFRESHING) {
                    break;
                }

                int deltaY = (int) (ev.getY() - downY);
                LogUtils.e("RefreshListView  deltaY" + deltaY);
                int paddingTop = -headerViewHeight + deltaY;
                if (paddingTop > -headerViewHeight && getFirstVisiblePosition() == 0) {
                    headerView.setPadding(0, paddingTop, 0, 0);

                    if (paddingTop >= 0 && currentState == PULL_REFRESH) {
                        // 从下拉刷新进入松开刷新状态
                        currentState = RELEASE_REFRESH;
                        refreshHeaderView();
                    } else if (paddingTop < 0 && currentState == RELEASE_REFRESH) {
                        // 进入下拉刷新状态
                        currentState = PULL_REFRESH;
                        refreshHeaderView();
                    }
                    return true;// 拦截TouchMove，不让listview处理该次move事件,会造成listview无法滑动
                }

                break;
            case MotionEvent.ACTION_UP:
                if (currentState == PULL_REFRESH) {
                    // 隐藏headerView
                    headerView.setPadding(0, -headerViewHeight, 0, 0);
                } else if (currentState == RELEASE_REFRESH) {
                    headerView.setPadding(0, 0, 0, 0);
                    currentState = REFRESHING;
                    refreshHeaderView();
                    if (currentTime == 0) {
                        listener.onPullRefresh();
                        currentTime = System.currentTimeMillis();
                    } else if (listener != null && (System.currentTimeMillis() - currentTime > 4000)) {// 10秒之后才能刷新
                        currentTime = System.currentTimeMillis();
                        listener.onPullRefresh();
                    } else {
                        Toast.makeText(getContext(), "您刷新的速度太快了，请稍后再试", Toast.LENGTH_SHORT).show();
                        completeRefresh();
                    }
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            downY = (int) ev.getY();
            LogUtils.e("RefreshListView  Y" + downY);
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据currentState来更新headerView
     */
    private void refreshHeaderView() {
        switch (currentState) {
            case PULL_REFRESH:
                tv_state.setText("下拉刷新");
                iv_arrow.startAnimation(downAnimation);
                break;
            case RELEASE_REFRESH:
                tv_state.setText("松开刷新");
                iv_arrow.startAnimation(upAnimation);
                break;
            case REFRESHING:
                iv_arrow.clearAnimation();// 因为向上的旋转动画有可能没有执行完
                iv_arrow.setVisibility(View.INVISIBLE);
                pb_rotate.setVisibility(View.VISIBLE);
                tv_state.setText("正在刷新...");
                break;
        }
    }

    /**
     * 完成刷新操作，重置状态,在你获取完数据并更新完adater之后，去在UI线程中调用该方法
     */
    public void completeRefresh() {
        // 重置headerView状态
        headerView.setPadding(0, -headerViewHeight, 0, 0);
        currentState = PULL_REFRESH;
        pb_rotate.setVisibility(View.INVISIBLE);
        iv_arrow.setVisibility(View.VISIBLE);
        tv_state.setText("下拉刷新");
        tv_time.setText("最后刷新：" + getCurrentTime());

    }

    // 获取当前时间
    private String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    private OnRefreshListener listener;

    public void setOnRefreshListener(OnRefreshListener listener) {
        this.listener = listener;
    }

    public interface OnRefreshListener {
        void onPullRefresh();

    }

}
