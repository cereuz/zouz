package com.zao.admin;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.zao.activity.AgentWebActivity;
import com.zao.zouz.MainActivity;
import com.zao.zouz.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/22 17:33
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected final int REQUEST_CODE_CHOOSE = 1;

    protected ProgressDialog progressDialog;
    protected Activity mContext;
    protected SwipeRefreshLayout swipeRefreshLayout;
    private InputMethodManager imm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        AppManager.getINSTANCE().addActivity(this);
        mContext = this;
        swipeRefreshLayout = getSwipeRefreshLayout();
        initViews();
        initEvents();
        initDatas();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected abstract SwipeRefreshLayout getSwipeRefreshLayout();

    protected abstract void initDatas();

    protected abstract void initViews();

    protected abstract void initEvents();


    @Override
    protected void onDestroy() {
        try {
            super.onDestroy();
            hideSoftKeyBoard();
            AppManager.getINSTANCE().removeActivity(this);
        } catch (Exception e) {
            AppManager.getINSTANCE().removeActivity(this);
        }
    }

    /**
     * 显示进度条
     */
    protected void showProgress(String text) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage(text);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        progressDialog.show();
    }

    /**
     * 隐藏进度条
     */
    protected void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    /**
     * 此方法必须要返回明确的值，不然会报异常。
     * @return
     */
    protected abstract int getLayoutId();

    protected void startAgentWeb(String title, String url) {
        Intent intent = new Intent(mContext, AgentWebActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    // SwipeRefreshLayout初始化
    protected void InitSwipeRefreshLayout() {
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,
                    R.color.colorAccent, R.color.colorAccent, R.color.colorAccent);
    }

    // http请求开始
    protected void httpStart() {
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(true);
                }
            });
        this.onRefresh();
    }

    // http请求结束
    protected void httpFinish() {
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
    }

    public void onRefresh() {
        // TODO Auto-generated method stub
        Log.i(getRunningActivityName(), "----REFRESH---->");
    }

    /**
     * 获取Activity名称
     */
    private String getRunningActivityName() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        String runningActivity = activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
        return runningActivity;
    }

    /**
     * 双击退出
     */
    private static Boolean isExit = false;

    protected void exitBy2Click() {
        Timer tExit;
        if (!isExit) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            AppManager.getINSTANCE().finishAllActivity();
        }
    }

    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }

}

