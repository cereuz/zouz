package com.zao.admin;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zao.activity.BubbleActivity;
import com.zao.base.BaseFragment;
import com.zao.base.MyApp;
import com.zao.utils.Constant;
import com.zao.utils.ZouUtil;
import com.zao.utils.DateUtil;
import com.zao.utils.LogZ;
import com.zao.zouz.R;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/23 15:54
 */
public class GroupFragment extends BaseFragment {

    private Button btnMain;
    private RecyclerView mRecyclerView;
    EditText et_uber_url;
    private MyPicAdapter mMyAdapter;
    private Context context;
    View mView;
    String mUrl;

    public static final MediaType JSON= MediaType.parse("application/json; charset=utf-8");
    //"http://192.168.60.23:8080/zou0306/TestJsonUber"

    @Override
    protected void doOnCreate(View baseView, Bundle savedInstanceState) {
        LogZ.e(DateUtil.getCurrentTime_Today());
        context = MyApp.getContext();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_onezao_group0306;
    }


    @Override
    protected void doOnViewCreated(View view, Bundle savedInstanceState) {
        mView = view;
        initView(view);
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_main);
        et_uber_url = view.findViewById(R.id.et_uber_url);
        et_uber_url.setText(Constant.BASE_URL);

        String[] data = {this.getResources().getString(R.string.toGrid),"Zou","Zneo","Uber","WeiXin","Bubble","Zneo","Zsky","Zneo","Zsky","Zneo","Zsky",
                "Zneo","Zsky","Zneo","Zsky","Zneo","Zsky","Zneo","Zsky","Zneo","Zsky","Zneo","Zsky","Zneo","Zsky","Zneo","Zsky","Zneo","Zsky","Zneo","Zsky","Zneo","Zsky","Zneo","Zsky",
                "Zneo","Zsky","Zneo","Zsky","Zneo","Zsky","Zneo","Zsky","Zneo","Zsky","Zneo","Zsky","Zneo","Zsky","Zneo","Zsky","Zneo","Zsky","Zneo","Zsky","Zneo","Zsky","Zneo","Zsky",
                "Zneo","Zsky","Zneo","Zsky","Zneo","Zsky","Zneo","Zsky","Zneo","Zsky","Zneo","Zsky","Zneo","Zsky"};
        mMyAdapter = new MyPicAdapter(context, data);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setAdapter(mMyAdapter);

        mMyAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String tag =(String)view.getTag();
                doItemClick(tag,position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getActivity(),"TestItemOnLongClick : " + position, Toast.LENGTH_SHORT).show();
            }
        });


        btnMain = (Button) view.findViewById(R.id.btn_recycle);
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyAdapter.data[0] = "葡萄";
                mMyAdapter.data[2] = "蜗牛";
                mMyAdapter.notifyItemChanged(0);
                mMyAdapter.notifyItemChanged(2);
            }
        });
        //长按恢复初始化状态
        btnMain.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                initView(mView);
                return true;
            }
        });
    }


    private void doItemClick(String text, int position) {
        Toast.makeText(context, text + "    " + position, Toast.LENGTH_SHORT).show();
        switch (text) {
            case "Uber" :
                initUber();
                break;
            case "WeiXin" :
//                initWeiXin();
                ZouUtil.initItem(context,"https://www.11visa.com/","com.pandavisa","com.pandavisa.ui.activity.appstart.AppStart");
            case "Bubble" :
                startActivity(new Intent(context, BubbleActivity.class));
        }
    }

    /**
     * 打开微信
     */
    private void initWeiXin() {
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
//            ComponentName cmp = new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");
            ComponentName cmp = new ComponentName("com.pandavisa","com.pandavisa.ui.activity.appstart.AppStart");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            startActivity(intent);
        } catch (ActivityNotFoundException e){
            // TODO: handle exception
            Toast.makeText(mContext, "检查到您手机没有安装微信，正在跳转到官网", Toast.LENGTH_LONG).show();
//            startAgentWeb("熊猫签证","https://www.11visa.com/");
            Uri uri = Uri.parse("https://www.11visa.com/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    /**
     *  网络请求，并传递数据
     */
    private void initUber() {
        mUrl = et_uber_url.getText().toString().trim();
        //开启一个线程，做联网操作
        new Thread() {
            @Override
            public void run() {
                postParams();
            }
        }.start();
    }

    /**
     * 发送带参数的请求到服务器端
     */
    private void postParams() {
        //创建一个OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //构建一个请求体 add参数1 key 参数2 value 发送字段
        RequestBody requestBody = new FormBody.Builder()
                .add("time", DateUtil.getCurrentTime_Today_One())
                .add("platform", "android")
                .add("version", "23")
                .add("SDK", "24")
                .build();
        //构建一个请求对象
        Request request = new Request.Builder()
                .url(mUrl)
                .post(requestBody)
                .build();
        //发送请求获取响应
        Response response=null;
        try {
            response = okHttpClient.newCall(request).execute();
            //判断请求是否成功
            if(response.isSuccessful()){
                //打印服务端返回结果
                LogZ.e(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
