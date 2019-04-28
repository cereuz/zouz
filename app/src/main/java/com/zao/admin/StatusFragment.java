package com.zao.admin;

import android.os.Bundle;
import android.view.View;

import com.zao.zouz.DateUtil;
import com.zao.zouz.LogZ;
import com.zao.zouz.R;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/23 15:54
 */
public class StatusFragment extends BaseFragment {
    @Override
    protected void doOnCreate(View baseView, Bundle savedInstanceState) {
        LogZ.e(DateUtil.getCurrentTime_Today());
    }

    @Override
    protected void doOnViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_onezao_status0306;
    }
}
