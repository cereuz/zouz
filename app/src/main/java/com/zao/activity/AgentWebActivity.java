package com.zao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.zao.zouz.R;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/4/28 13:56
 */
public class AgentWebActivity extends AppCompatActivity {

    private String baidu = "https://www.11visa.com/";

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题

/*        ActionBar actionBar=getSupportActionBar();
//        ActionBar actionBar=getActionBar();   //根据版本设置上一行或者本行
        actionBar.hide();*/

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏

        setContentView(R.layout.activity_agentweb_view);

        webView= (WebView) findViewById(R.id.wb_agentweb_webview);

        String url= getIntent().getStringExtra("url");
        String title= getIntent().getStringExtra("title");

        if(url!=null&& !url.equals("")){
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAppCacheEnabled(true);

            //设置 缓存模式
            webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
            // 开启 DOM storage API 功能
            webView.getSettings().setDomStorageEnabled(true);

            webView.setWebViewClient(new WebViewClient() {
                //覆盖shouldOverrideUrlLoading 方法,防止利用系统自带的浏览器打开页面
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });

            webView.loadUrl(url);
        }else{

            Toast.makeText(this,"url不正确",Toast.LENGTH_SHORT).show();
        }
    }
}