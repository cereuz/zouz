package com.zao.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.zao.adapter.WebBannerAdapter;
import com.zao.zbanner.BannerLayout;
import com.zao.zouz.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/5/5 17:03
 */
public class BannerActivity extends AppCompatActivity implements BannerLayout.OnBannerItemClickListener {
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        BannerLayout  recyclerBanner =  findViewById(R.id.recycler);
        BannerLayout bannerVertical =  findViewById(R.id.recycler_ver);

        mContext = getApplicationContext();

        List<String> list = new ArrayList<>();
        list.add("https://img3.doubanio.com/view/photo/l/public/p2360494161.webp");
        list.add("https://s2.mogucdn.com/mlcdn/c45406/170420_1hcbb7h5b58ihilkdec43bd6c2ll6_750x500.jpg");
        list.add("https://img1.doubanio.com/view/photo/l/public/p2031503598.webp");
        list.add("https://img1.doubanio.com/view/photo/l/public/p2320164299.webp");
        list.add("https://img3.doubanio.com/view/photo/l/public/p2535136785.webp");
        list.add("https://img3.doubanio.com/view/photo/l/public/p2535136771.webp");
        list.add("http://s16.mogucdn.com/p2/170204/upload_56631h6616g4e2e45hc6hf6b7g08f_750x500.jpg");
        list.add("https://img1.doubanio.com/view/photo/l/public/p2454230998.webp");
        list.add("https://img1.doubanio.com/view/photo/l/public/p2535136759.webp");

        WebBannerAdapter webBannerAdapter=new WebBannerAdapter(this,list);
        webBannerAdapter.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(mContext, "点击了第  " + position+"  项", Toast.LENGTH_SHORT).show();
            }
        });

        WebBannerAdapter WebBannerAdapter2 =new WebBannerAdapter(this,list);
        WebBannerAdapter2.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(mContext, "点击了第  " + position+"  项", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerBanner.setAdapter(webBannerAdapter);
        bannerVertical.setAdapter(WebBannerAdapter2);
    }


    public void jump(View view) {
        startActivity(new Intent(mContext, NormalActivity.class));
    }
    public void jumpOverFlying(View view) {
        startActivity(new Intent(mContext, OverFlyingActivity.class));
    }

    @Override
    public void onItemClick(int position) {

    }
}
