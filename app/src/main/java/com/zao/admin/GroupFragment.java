package com.zao.admin;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zao.activity.BannerActivity;
import com.zao.activity.BannerTextActivity;
import com.zao.activity.BubbleActivity;
import com.zao.activity.RecyclerViewBannerNormal;
import com.zao.adapter.WebBannerAdapter;
import com.zao.base.BaseFragment;
import com.zao.base.MyApp;
import com.zao.httpdownload.ApiMethods;
import com.zao.httpdownload.DownloadIntentService;
import com.zao.httpdownload.MyObserver;
import com.zao.httpdownload.ObserverOnNextListener;
import com.zao.httpdownload.UrlDownloadIntentService;
import com.zao.httpdownload.Version;
import com.zao.pressure0306.PressureDiagramActivity;
import com.zao.utils.Constant;
import com.zao.utils.ServiceUtil;
import com.zao.utils.ZouUtil;
import com.zao.utils.DateUtil;
import com.zao.utils.LogZ;
import com.zao.zbanner.RecyclerViewBannerBase;
import com.zao.zouz.R;
import com.zao.zxing.MyScanActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    RecyclerViewBannerNormal banner, banner2;

    private Button btnMain;
    private RecyclerView mRecyclerView;
    EditText et_uber_url;
    private MyPicAdapter mMyAdapter;
    private Context context;
    View mView;
    String mUrl;

    public static final MediaType JSON= MediaType.parse("application/json; charset=utf-8");
    private static final int DOWNLOADAPK_ID = 10;
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
        initBanner();
    }

    private void initView(View view) {
        banner = (RecyclerViewBannerNormal) view.findViewById(R.id.banner);
        banner2 = (RecyclerViewBannerNormal) view.findViewById(R.id.banner2);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_main);
        et_uber_url = view.findViewById(R.id.et_uber_url);
        et_uber_url.setText(Constant.BASE_URL_JSON);
        et_uber_url.setCursorVisible(true);

        String[] data = {this.getResources().getString(R.string.toGrid),"Zou","Zsky","Scan","Zload","Uber","WeiXin","Bubble","Update","Banner","BannerText","Pressure","Zneo","Zsky",
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
        btnMain.setVisibility(View.GONE);
    }

    private void initBanner() {
        List<String> list = new ArrayList<>();
        list.add("https://img3.doubanio.com/view/photo/l/public/p2360494161.webp");
        list.add("https://s2.mogucdn.com/mlcdn/c45406/170420_1hcbb7h5b58ihilkdec43bd6c2ll6_750x500.jpg");
        list.add("https://img3.doubanio.com/view/photo/l/public/p2535136785.webp");
        list.add("http://s16.mogucdn.com/p2/170204/upload_56631h6616g4e2e45hc6hf6b7g08f_750x500.jpg");
        list.add("https://img1.doubanio.com/view/photo/l/public/p2454230998.webp");
        list.add("https://s2.mogucdn.com/mlcdn/c45406/170420_1hcbb7h5b58ihilkdec43bd6c2ll6_750x500.jpg");
        list.add("https://img1.doubanio.com/view/photo/l/public/p2320164299.webp");
        list.add("https://img3.doubanio.com/view/photo/l/public/p2535136785.webp");
        list.add("https://img1.doubanio.com/view/photo/l/public/p2031503598.webp");
        list.add("https://img3.doubanio.com/view/photo/l/public/p2535136771.webp");
        list.add("http://s16.mogucdn.com/p2/170204/upload_56631h6616g4e2e45hc6hf6b7g08f_750x500.jpg");
        list.add("https://img1.doubanio.com/view/photo/l/public/p2454230998.webp");
        list.add("https://img1.doubanio.com/view/photo/l/public/p2535136759.webp");

        banner.initBannerImageView(list, new RecyclerViewBannerBase.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(mContext, "clicked:" + position, Toast.LENGTH_SHORT).show();
            }
        });
        banner.setAutoPlaying(true);
//        banner.setShowIndicator(false);

        banner2.initBannerImageView(list, new RecyclerViewBannerBase.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(mContext, "clicked:" + position, Toast.LENGTH_SHORT).show();
            }
        });
        banner2.setAutoPlaying(true);
        banner2.setVisibility(View.GONE);
    }


    private void doItemClick(String text, int position) {
        Toast.makeText(context, text + "    " + position, Toast.LENGTH_SHORT).show();
        switch (text) {

            case "Zload" :
                startUrlDownloadService();
                break;
            case "Scan" :
                startActivity(new Intent(context, MyScanActivity.class));
                break;
            case "Uber" :
                initUber();
                break;
            case "WeiXin" :
//                initWeiXin();
                ZouUtil.initItem(context,"https://www.11visa.com/","com.pandavisa","com.pandavisa.ui.activity.appstart.AppStart");
                break;
            case "Bubble" :
                startActivity(new Intent(context, BubbleActivity.class));
                break;
            case "Update" :
                startCheckVersion();
                break;
            case "Banner" :
                startActivity(new Intent(context, BannerActivity.class));
                break;
            case "BannerText" :
                startActivity(new Intent(context, BannerTextActivity.class));
                break;
            case "Pressure" :
                startActivity(new Intent(context, PressureDiagramActivity.class));
                break;
        }
    }

    /**
     * 请求接口，查看是否有版本更新
     */
    private void startCheckVersion() {
        ObserverOnNextListener<Version> listener = new ObserverOnNextListener<Version>() {
            @Override
            public void onNext(Version version) {
                LogZ.e("VersionName: " + version.getVersionName());
                LogZ.e("VersionDes: " + version.getVersionDes());
                LogZ.e("VersionCode: " + version.getVersionCode());
                LogZ.e("DownloadUrl: " + version.getDownloadUrl());
                showUpdateDialog(version.toString());
            }
        };
        ApiMethods.getCheckVersion(new MyObserver<Version>(mContext,listener),"/api/share/download/ff61ea71-517e-41c4-b688-7b08b832002d",2);
    }

    /**
     * 显示是否下载更新选择框
     */
    private void showUpdateDialog(String message) {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        // 设置提示框的标题
        builder.setTitle("版本升级").
                setIcon(R.mipmap.ic_launcher). // 设置提示框的图标
                setMessage(message).// 设置要显示的信息
                setPositiveButton("确定", new DialogInterface.OnClickListener() {// 设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startDownloadService();
            }
        }).setNegativeButton("取消", null);//设置取消按钮,null是什么都不做，并关闭对话框
        AlertDialog alertDialog = builder.create();
        // 显示对话框
        alertDialog.show();
    }

    /**
     * 开启下载的服务，进行下载操作
     */
    private void startDownloadService() {
        if (ServiceUtil.isServiceRunning(mContext, DownloadIntentService.class.getName())) {
            Toast.makeText(mContext, "正在下载", Toast.LENGTH_SHORT).show();
            return;
        }
        // String downloadUrl = http://sqdd.myapp.com/myapp/qqteam/tim/down/tim.apk;
        // String downloadUrl = "/qqmi/aphone_p2p/TencentVideo_V6.0.0.14297_848.apk";
        // String downloadUrl = "/16891/52D9FF5B0E4F30719F913E09BCE9C3E9.apk?fsname=com.tencent.tmgp.sgame_1.43.1.27_43012701.apk&csr=1bbd";
        String downloadUrl = "/mobs/download/client/android/jtyh.apk";
        Intent intent = new Intent(mContext, DownloadIntentService.class);
        Bundle bundle = new Bundle();
        bundle.putString("download_url", downloadUrl);
        bundle.putInt("download_id", DOWNLOADAPK_ID);
        bundle.putString("download_file", downloadUrl.substring(downloadUrl.lastIndexOf('/') + 1));
        intent.putExtras(bundle);
        mContext.startService(intent);
    }

    /**
     * 开启下载的服务，进行下载操作
     */
    private void startUrlDownloadService() {
        if (ServiceUtil.isServiceRunning(mContext, UrlDownloadIntentService.class.getName())) {
            Toast.makeText(mContext, "正在下载", Toast.LENGTH_SHORT).show();
            return;
        }
        // String downloadUrl = http://sqdd.myapp.com/myapp/qqteam/tim/down/tim.apk;
        // String downloadUrl = "/qqmi/aphone_p2p/TencentVideo_V6.0.0.14297_848.apk";
        // String downloadUrl = "/16891/52D9FF5B0E4F30719F913E09BCE9C3E9.apk?fsname=com.tencent.tmgp.sgame_1.43.1.27_43012701.apk&csr=1bbd";
        String downloadUrl = et_uber_url.getText().toString().trim();
        Intent intent = new Intent(mContext, UrlDownloadIntentService.class);
        Bundle bundle = new Bundle();
        bundle.putString("download_url", downloadUrl);
        bundle.putInt("download_id", DOWNLOADAPK_ID + 1);
        bundle.putString("download_file", downloadUrl.substring(downloadUrl.lastIndexOf('/') + 1));
        intent.putExtras(bundle);
        mContext.startService(intent);
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
                .add("time", System.currentTimeMillis() + "")
                .add("platform", "android")
                .add("version", "23")
                .add("SDK", "24")
                .add("Tom", "Ztom")
                .add("Book", "少年维特之烦恼")
                .add("Poem", getResources().getString(R.string.large_text2))
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
