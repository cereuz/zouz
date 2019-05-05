package com.zao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.zao.zbanner.RecyclerViewBannerBase;
import com.zao.zouz.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/5/5 17:06
 */
public class NormalActivity extends AppCompatActivity {
    RecyclerViewBannerNormal banner, banner2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner2);
        banner = (RecyclerViewBannerNormal) findViewById(R.id.banner);
        banner2 = (RecyclerViewBannerNormal) findViewById(R.id.banner2);
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
                Toast.makeText(NormalActivity.this, "clicked:" + position, Toast.LENGTH_SHORT).show();
            }
        });
        banner2.initBannerImageView(list, new RecyclerViewBannerBase.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(NormalActivity.this, "clicked:" + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

}

