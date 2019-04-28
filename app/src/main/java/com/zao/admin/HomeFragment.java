package com.zao.admin;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.zao.zouz.DateUtil;
import com.zao.zouz.LogZ;
import com.zao.zouz.R;
import com.zao.zouz.ScrollingActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/23 15:43
 */
public class HomeFragment extends BaseFragment implements AppBarLayout.OnOffsetChangedListener{

    private AppBarLayout appBar;
    /**
     * 大布局背景，遮罩层
     */
    private View bgContent;
    /**
     * 展开状态下toolbar显示的内容
     */
    private View toolbarOpen;
    /**
     * 展开状态下toolbar的遮罩层
     */
    private View bgToolbarOpen;
    /**
     * 收缩状态下toolbar显示的内容
     */
    private View toolbarClose;
    /**
     * 收缩状态下toolbar的遮罩层
     */
    private View bgToolbarClose;

    @Override
    protected void doOnCreate(View baseView, Bundle savedInstanceState) {
        LogZ.e(DateUtil.getCurrentTime_Today());
    }

    @Override
    protected void doOnViewCreated(View view, Bundle savedInstanceState) {
        initUI(view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_onezao_home0306;
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //这个必须存在,不然程序会崩溃
    public void onEventZou(MessageEvent event) {
            String strings = event.toString();
            LogZ.e(strings);
            ToastUtil.showT(mContext,"接收到EventBus从其他控件post的消息：" + strings);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 注册EventBus
         * 需要先注册，然后其他控件发送消息，最后当前控件才能接收到消息。
         */
        EventBus.getDefault().register(this);
        ToastUtil.showT(mContext,"注册EventBus");
    }

    /**
     * 初始化界面
     * @param view
     */
    private void initUI(View view) {
        appBar = view.findViewById(R.id.app_bar);
        bgContent = view.findViewById(R.id.bg_content);
        toolbarOpen =  view.findViewById(R.id.include_toolbar_open);
        bgToolbarOpen =  view.findViewById(R.id.bg_toolbar_open);
        toolbarClose =  view.findViewById(R.id.include_toolbar_close);
        bgToolbarClose =  view.findViewById(R.id.bg_toolbar_close);

        appBar.addOnOffsetChangedListener(this);


        ImageView iv_plus = (ImageView) view.findViewById(R.id.iv_plus);
        iv_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ScrollingActivity.class));
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
        appBar.removeOnOffsetChangedListener(this);  //移除监听接口
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        //垂直方向偏移量
        int offset = Math.abs(verticalOffset);
        //最大偏移距离
        int scrollRange = appBarLayout.getTotalScrollRange();
        if (offset <= scrollRange / 2) {//当滑动没超过一半，展开状态下toolbar显示内容，根据收缩位置，改变透明值
            toolbarOpen.setVisibility(View.VISIBLE);
            toolbarClose.setVisibility(View.GONE);
            //根据偏移百分比 计算透明值
            float scale2 = (float) offset / (scrollRange / 2);
            int alpha2 = (int) (255 * scale2);
            bgToolbarOpen.setBackgroundColor(Color.argb(alpha2, 25, 131, 209));
        } else {//当滑动超过一半，收缩状态下toolbar显示内容，根据收缩位置，改变透明值
            toolbarClose.setVisibility(View.VISIBLE);
            toolbarOpen.setVisibility(View.GONE);
            float scale3 = (float) (scrollRange  - offset) / (scrollRange / 2);
            int alpha3 = (int) (255 * scale3);
            bgToolbarClose.setBackgroundColor(Color.argb(alpha3, 25, 131, 209));
        }
        //根据偏移百分比计算扫一扫布局的透明度值
        float scale = (float) offset / scrollRange;
        int alpha = (int) (255 * scale);
        bgContent.setBackgroundColor(Color.argb(alpha, 25, 131, 209));
    }
}
