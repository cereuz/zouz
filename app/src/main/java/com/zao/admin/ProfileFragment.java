package com.zao.admin;

import android.os.Bundle;
import android.view.View;


import com.zao.zouz.DateUtil;
import com.zao.zouz.LogZ;
import com.zao.zouz.R;

import org.greenrobot.eventbus.EventBus;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/23 15:54
 */
public class ProfileFragment extends BaseFragment {
    @Override
    protected void doOnCreate(View baseView, Bundle savedInstanceState) {
        LogZ.e(DateUtil.getCurrentTime_Today());
    }

    @Override
    protected void doOnViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_onezao_profile0306;
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().post(new MessageEvent("1", "蜗牛","snail"));
        EventBus.getDefault().post(new MessageEvent("2", "葡萄","grape"));
        EventBus.getDefault().post(new MessageEvent("3", "天空","sky"));
        EventBus.getDefault().post(new MessageEvent("4", "大地","land"));
    }
}
