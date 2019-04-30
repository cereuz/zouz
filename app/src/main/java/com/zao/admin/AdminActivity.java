package com.zao.admin;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zao.base.BaseActivity;
import com.zao.utils.LogZ;
import com.zao.zouz.R;
import com.zao.utils.StatusBarUtil;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/4/27 16:41
 */
public class AdminActivity extends BaseActivity implements View.OnClickListener {

    RelativeLayout rl_home;
    RelativeLayout rl_status;
    RelativeLayout rl_group;
    RelativeLayout rl_profile;

    //设置修改图片
    ImageView iv_home;
    ImageView iv_status;
    ImageView iv_group;
    ImageView iv_profile;

    //设置修改文字
    TextView  tv_onezao_home;
    TextView  tv_onezao_status;
    TextView  tv_onezao_group;
    TextView  tv_onezao_profile;

    //初始化四个Fragment
    private HomeFragment   homeFragment;
    private StatusFragment   statusFragment;
    private GroupFragment   groupFragment;
    private ProfileFragment   profileFragment;

    private boolean mIsEditStatus = false;
    private long firstTime ;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        StatusBarUtil.showStatusBar(this);
        StatusBarUtil.setStatusBarLightMode(this,true);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));//设置状态栏的背景色
        initTab();
    }

    /**
     * 在fragment所在的FragmentActivity中，重写onSaveInstanceState方法，但是不做实现，将super.onSaveInstanceState(outState)注释掉。
     *
     * 原理分析：
     *
     * 当前APP崩溃后首页重启或者从后台再次回到这个app的时候，通过onCreate中的参数savedInstanceState恢复了之前的fragment。
     * 此时的FragmentTransaction中的相当于又再次add了fragment进去的，之前保存的fragment也还在。
     * hide()和show()方法对之前保存的fragment已经失效了。所以出现了重叠的现象。
     */
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }


    /**
     * 双击推出当前界面
     */
    @Override
    public void onBackPressed() {

        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            Toast.makeText(mContext, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            firstTime = secondTime;
        } else{
            finish();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_admin;
    }

    /**
     * 初始化  底部的四个按钮的切换
     */
    private void initTab() {
        rl_home = (RelativeLayout) findViewById(R.id.rl_home);
        rl_home.setOnClickListener(this);
        rl_status = (RelativeLayout) findViewById(R.id.rl_status);
        rl_status.setOnClickListener(this);
        rl_group = (RelativeLayout) findViewById(R.id.rl_group);
        rl_group.setOnClickListener(this);
        rl_profile = (RelativeLayout) findViewById(R.id.rl_profile);
        rl_profile.setOnClickListener(this);

        iv_home = (ImageView) findViewById(R.id.iv_home);
        iv_status = (ImageView) findViewById(R.id.iv_status);
        iv_group = (ImageView) findViewById(R.id.iv_group);
        iv_profile = (ImageView) findViewById(R.id.iv_profile);

        tv_onezao_home = (TextView) findViewById(R.id.tv_onezao_home);
        tv_onezao_status = (TextView) findViewById(R.id.tv_onezao_status);
        tv_onezao_group = (TextView) findViewById(R.id.tv_onezao_group);
        tv_onezao_profile = (TextView) findViewById(R.id.tv_onezao_profile);

        initHomeFragment();
    }

    /**
     * 初始化第一个Fragment
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initHomeFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        hideFragment(transaction);
        clearIcon();
        //将四个的图片全部设置为未点击状态。
        iv_home.setImageResource(R.mipmap.ic_tab_home_active);
        tv_onezao_home.setTextColor(getResources().getColor(R.color.colorGreen));
        if(homeFragment  ==  null){
            homeFragment = new HomeFragment();
            transaction.add(R.id.fragment_onezao_0403, homeFragment);
            LogZ.e("首页壹");
        } else {
            transaction.show(homeFragment);
            LogZ.e("首页贰");
        }
        transaction.commit();
    }


    /**
     * 隐藏所有的fragment
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (statusFragment != null) {
            transaction.hide(statusFragment);
        }
        if (groupFragment != null) {
            transaction.hide(groupFragment);
        }
        if (profileFragment != null) {
            transaction.hide(profileFragment);
        }
    }


    /**
     * 将底部的图片和文字都归为未点击状态
     */
    private  void  clearIcon(){
        iv_home.setImageResource(R.mipmap.ic_tab_home_normal);
        iv_status.setImageResource(R.mipmap.ic_tab_status_normal);
        iv_group.setImageResource(R.mipmap.ic_tab_group_normal);
        iv_profile.setImageResource(R.mipmap.ic_tab_profile_normal);
        tv_onezao_home.setTextColor(getResources().getColor(R.color.colorText));
        tv_onezao_status.setTextColor(getResources().getColor(R.color.colorText));
        tv_onezao_group.setTextColor(getResources().getColor(R.color.colorText));
        tv_onezao_profile.setTextColor(getResources().getColor(R.color.colorText));
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        switch (view.getId()){
            case R.id.rl_home :
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));//设置状态栏的背景色
                /**
                 * 随着Fragment的切换，重新绘制Toolbar的menu
                 */
                mIsEditStatus = false;
                invalidateOptionsMenu();

                initHomeFragment();
                break;

            case R.id.rl_status :
                getWindow().setStatusBarColor(getResources().getColor(R.color.white));//设置状态栏的背景色
                //将四个的图片全部设置为未点击状态。
                hideFragment(transaction);
                clearIcon();
                iv_status.setImageResource(R.mipmap.ic_tab_status_active);
                tv_onezao_status.setTextColor(getResources().getColor(R.color.colorGreen));
                if(statusFragment  ==  null){
                    statusFragment = new  StatusFragment();
                    transaction.add(R.id.fragment_onezao_0403, statusFragment);
                    LogZ.e("Status壹");
                }   else {
                    transaction.show(statusFragment);
                    LogZ.e("Status贰");
                }
                break;

            case R.id.rl_group :
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));//设置状态栏的背景色
                hideFragment(transaction);
                clearIcon();
                //将四个的图片全部设置为未点击状态。
                iv_group.setImageResource(R.mipmap.ic_tab_group_active);
                tv_onezao_group.setTextColor(getResources().getColor(R.color.colorGreen));
                if(groupFragment  ==  null){
                    groupFragment = new  GroupFragment();
                    transaction.add(R.id.fragment_onezao_0403, groupFragment);
                    LogZ.e("Group壹");
                } else {
                    transaction.show(groupFragment);
                    LogZ.e("Group贰");
                }
                break;

            case R.id.rl_profile :
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));//设置状态栏的背景色
                /**
                 * 随着Fragment的切换，重新绘制Toolbar的menu
                 */
                mIsEditStatus = true;
                invalidateOptionsMenu();

                //将四个的图片全部设置为未点击状态。
                hideFragment(transaction);
                clearIcon();
                iv_profile.setImageResource(R.mipmap.ic_tab_profile_active);
                tv_onezao_profile.setTextColor(getResources().getColor(R.color.colorGreen));

                if(profileFragment  ==  null){
                    profileFragment = new  ProfileFragment();
                    transaction.add(R.id.fragment_onezao_0403, profileFragment);
                    LogZ.e("Profile壹");
                } else {
                    transaction.show(profileFragment);
                    LogZ.e("Profile贰");
                }
                break;
        }
        transaction.commit();
    }

    @Override
    protected SwipeRefreshLayout getSwipeRefreshLayout() {
        return null;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {

    }

}
