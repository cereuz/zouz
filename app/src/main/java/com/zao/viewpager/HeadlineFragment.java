package com.zao.viewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zao.adapter.TagAdapter;
import com.zao.base.BaseFragment;
import com.zao.base.MyApp;
import com.zao.bean.TagBean;
import com.zao.utils.ToastUtil;
import com.zao.zouz.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/4/28 17:43
 */
public class HeadlineFragment extends BaseFragment {
    private RecyclerView tagRecyclerView;

    private static final int MAX = 9;
    private List<TagBean> tagBeanList = new ArrayList<>();

    private TagAdapter tagAdapter;
    private Context mContext;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tag;
    }

    @Override
    protected void doOnCreate(View baseView, Bundle savedInstanceState) {
        mContext = MyApp.getContext();
        initView();
        initData();
        setRecyclerView();
    }

    @Override
    protected void doOnViewCreated(View view, Bundle savedInstanceState) {

    }

    private void initData() {
        tagBeanList.removeAll(tagBeanList);
        tagBeanList.add(new TagBean("1","超准时"));
        tagBeanList.add(new TagBean("2","很绅士"));
        tagBeanList.add(new TagBean("3","有礼貌"));
        tagBeanList.add(new TagBean("4","顾女生"));
        tagBeanList.add(new TagBean("5","我的男神是个大暖男哦我的男神是个大暖男哦"));
        tagBeanList.add(new TagBean("6","谈吐优雅"));
        tagBeanList.add(new TagBean("7","送我到楼下"));
        tagBeanList.add(new TagBean("9","迟到"));
        tagBeanList.add(new TagBean("10","态度恶劣"));
        tagBeanList.add(new TagBean("11","有不礼貌行为"));
        tagBeanList.add(new TagBean("12","有侮辱性语言有暴力倾向"));
        tagBeanList.add(new TagBean("13","人身攻击"));
        tagBeanList.add(new TagBean("14","临时改变行程"));
        tagBeanList.add(new TagBean("15","客户"));
        tagBeanList.add(new TagBean("16","迟到"));
        tagBeanList.add(new TagBean("17","并"));
        tagBeanList.add(new TagBean("18","无理"));
        tagBeanList.add(new TagBean("19","要求"));
        tagBeanList.add(new TagBean("20","延长"));
        tagBeanList.add(new TagBean("21","约会"));
        tagBeanList.add(new TagBean("22","时间"));
    }

    private void setRecyclerView() {
        final GridLayoutManager layoutManage = new GridLayoutManager(mContext, 4);
        /**
         * 设置文字过长的时候，占用几个item
         */
        layoutManage.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (tagBeanList.get(position).getTag_name().length()>MAX)
                    return 2;
//                    return layoutManage.getSpanCount();
                return 1;
            }
        });
        tagRecyclerView.setLayoutManager(layoutManage);
        tagAdapter = new TagAdapter(tagBeanList);
        tagRecyclerView.setAdapter(tagAdapter);
        tagAdapter.setOnItemClickListener(new TagAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                ToastUtil.showT(mContext,"点击了条目==" + position);
            }

            @Override
            public void onLongClick(int position) {
                ToastUtil.showT(mContext,"长按点击了条目==" + position);
            }
        });
    }

    private void initView() {
        tagRecyclerView = (RecyclerView) findViewById(R.id.tag_rv);
    }

}
