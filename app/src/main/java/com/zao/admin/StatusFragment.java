package com.zao.admin;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zao.base.BaseFragment;
import com.zao.utils.DateUtil;
import com.zao.utils.LogZ;
import com.zao.viewpager.HeadlineFragment;
import com.zao.viewpager.RecreationFragment;
import com.zao.viewpager.SportFragment;
import com.zao.viewpager.TabFragmentPagerAdapter;
import com.zao.viewpager.TechnologyFragment;
import com.zao.zouz.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/23 15:54
 */
public class StatusFragment extends BaseFragment {
    TabLayout mViewpagerTab;
    ViewPager mNewsViewpager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_onezao_status0306;
    }



    @Override
    protected void doOnCreate(View baseView, Bundle savedInstanceState) {
        LogZ.e(DateUtil.getCurrentTime_Today());

    }

    @Override
    protected void doOnViewCreated(View view, Bundle savedInstanceState) {
        initViews();
    }

    protected void initViews() {
        //找到控件
        mViewpagerTab = findViewById(R.id.home_viewpager_tab);
        mNewsViewpager = findViewById(R.id.home_viewpager);
        //fragment列表  
        List<Fragment> list_fragment = new ArrayList<>();
        //tab名的列表
        List<String> list_Title = new ArrayList<>();

        list_fragment.add(new HeadlineFragment());
        list_fragment.add(new RecreationFragment());
        list_fragment.add(new SportFragment());
        list_fragment.add(new TechnologyFragment());
        list_fragment.add(new HeadlineFragment());
        list_fragment.add(new RecreationFragment());
        list_fragment.add(new SportFragment());
        list_fragment.add(new TechnologyFragment());
        list_fragment.add(new HeadlineFragment());
        list_fragment.add(new RecreationFragment());
        list_fragment.add(new SportFragment());
        list_fragment.add(new TechnologyFragment());

        list_Title.add("头条");
        list_Title.add("娱乐");
        list_Title.add("体育");
        list_Title.add("科技");
        list_Title.add("头条");
        list_Title.add("娱乐");
        list_Title.add("体育");
        list_Title.add("科技");
        list_Title.add("头条");
        list_Title.add("娱乐");
        list_Title.add("体育");
        list_Title.add("科技");

        //设置名称
        for (int i = 0; i < list_Title.size(); i++) {
            mViewpagerTab.addTab(mViewpagerTab.newTab().setText(list_Title.get(i)));
        }
        TabFragmentPagerAdapter adapter = new TabFragmentPagerAdapter(
                getChildFragmentManager(), list_fragment, list_Title
//                getActivity().getSupportFragmentManager(), list_fragment, list_Title   // *** 使用这个，切换item会卡顿！！！***
        );
        //viewpager 加载adapter
        mNewsViewpager.setAdapter(adapter);
        //TableLayout加载viewpager
        mViewpagerTab.setupWithViewPager(mNewsViewpager);
    }
}
