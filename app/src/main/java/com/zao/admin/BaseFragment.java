package com.zao.admin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zao.activity.AgentWebActivity;
import com.zao.zouz.MainActivity;


/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/23 15:44
 */
public abstract class BaseFragment extends Fragment {

    private View baseView;
    protected Activity mContext;
    protected ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        baseView = inflater.inflate(getLayoutId(), container, false);
        doOnCreate(baseView, savedInstanceState);
        return baseView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doOnViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract void doOnCreate(View baseView, Bundle savedInstanceState);

    protected abstract void doOnViewCreated(View view, Bundle savedInstanceState);

    protected abstract int getLayoutId();

    protected void startAgentWeb(String title, String url) {
        Intent intent = new Intent(mContext, AgentWebActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }


    /**
     * 显示进度条
     */
    protected void showProgress(String text) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(mContext);
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
}

